package com.labforward.search.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class FrequencyServiceTest {

    @InjectMocks
    FrequencyService frequencyService = new FrequencyService();

    @Test
    public void getCount_whenOccurred() throws IOException {
        //Arrange
        String searchString = "Word";
        //Act
        long actual = frequencyService.getCount(searchString);
        long expected = 3;

        //Assert
        assertEquals(actual, expected);
    }

    @Test
    public void getCount_whenInputHasTrailingSpaces() throws IOException {
        //Arrange
        String searchString = " Word ";
        //Act
        long actual = frequencyService.getCount(searchString);
        long expected = 3;

        //Assert
        assertEquals(actual, expected);
    }

    @Test
    public void getCount_whenNoOccurrences() throws IOException {
        //Arrange
        String searchString = "FooBar";
        //Act
        long actual = frequencyService.getCount(searchString);
        long expected = 0;

        //Assert
        assertEquals(actual, expected);
    }

}