package com.labforward.search.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class FrequencyService {

    public long getCount(String searchString, String[] findingsDoc) throws IOException {
        return Arrays.stream(findingsDoc).filter(s -> s.equals(searchString)).count();
    }
}
