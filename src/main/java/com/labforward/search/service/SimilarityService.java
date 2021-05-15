package com.labforward.search.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class SimilarityService {
    private static final int DISTANCE_THRESHOLD = 1;

    public Set<String> getSimilarWords(String similarityCheckString, String[] findingsDoc) throws IOException {
        Set<String> similarWords = new HashSet<>();
        for (String word: findingsDoc) {
           int levenshteinDistance = calculate(similarityCheckString, word);
           if (levenshteinDistance <= DISTANCE_THRESHOLD && !similarityCheckString.equals(word)) {
               similarWords.add(word);
           }
        }
        return similarWords;
    }

    private int calculate(String similarityCheckString, String word) {
        int[][] dp = new int[similarityCheckString.length() + 1][word.length() + 1];

        for (int i = 0; i <= similarityCheckString.length(); i++) {
            for (int j = 0; j <= word.length(); j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                                    + costOfSubstitution(similarityCheckString.charAt(i - 1), word.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }
        return dp[similarityCheckString.length()][word.length()];
    }

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }
}
