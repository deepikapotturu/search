package com.labforward.search.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FrequencyServiceTest {

    public static String[] FINDINGS_DOC = {"Word", "word", "Wor", "Words"};

    @InjectMocks
    FrequencyService frequencyService = new FrequencyService();

    @Test
    public void getCount_whenOccurred() throws IOException {
        //Arrange
        String searchString = "Word";

        //Act
        long actual = frequencyService.getCount(searchString, FINDINGS_DOC);
        long expected = 1;

        //Assert
        assertEquals(actual, expected);
    }

    @Test
    public void getCount_whenNoOccurrences() throws IOException {
        //Arrange
        String searchString = "FooBar";
        //Act
        long actual = frequencyService.getCount(searchString, FINDINGS_DOC);
        long expected = 0;

        //Assert
        assertEquals(actual, expected);
    }

}