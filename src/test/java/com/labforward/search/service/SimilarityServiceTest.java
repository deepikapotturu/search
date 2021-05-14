package com.labforward.search.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class SimilarityServiceTest {

    @InjectMocks
    private final SimilarityService similarityService = new SimilarityService();

    @Test
    public void getSimilarWords_ifExist() throws IOException {
        //Arrange
        String similarityCheckString = "Word";
        //Act
        Set<String> actual = similarityService.getSimilarWords(similarityCheckString);
        Set<String> expected = new HashSet<>(Arrays.asList("Words", "Wor", "word"));
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void getSimilarWords_whenInputHasTrailingSpaces() throws IOException {
        //Arrange
        String similarityCheckString = " Word ";
        //Act
        Set<String> actual = similarityService.getSimilarWords(similarityCheckString);
        Set<String> expected = new HashSet<>(Arrays.asList("Words", "Wor", "word"));
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void getSimilarWords_whenNoSimilarWords() throws IOException {
        //Arrange
        String similarityCheckString = "FooBar";
        //Act
        Set<String> actual = similarityService.getSimilarWords(similarityCheckString);
        //Assert
        assert actual.isEmpty();
    }

}