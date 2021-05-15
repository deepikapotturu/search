package com.labforward.search.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SimilarityServiceTest {
    public static String[] FINDINGS_DOC = {"Word", "word", "Wor", "Words"};

    @InjectMocks
    private final SimilarityService similarityService = new SimilarityService();

    @Test
    public void getSimilarWords_ifExist() throws IOException {
        //Arrange
        String similarityCheckString = "Word";
        //Act
        Set<String> actual = similarityService.getSimilarWords(similarityCheckString, FINDINGS_DOC);
        Set<String> expected = new HashSet<>(Arrays.asList("Words", "Wor", "word"));
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void getSimilarWords_whenNoSimilarWords() throws IOException {
        //Arrange
        String similarityCheckString = "FooBar";
        //Act
        Set<String> actual = similarityService.getSimilarWords(similarityCheckString, FINDINGS_DOC);
        //Assert
        assert actual.isEmpty();
    }

}