package com.labforward.search.service;


import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class FrequencyService {

    public long getCount(String searchString) throws IOException {
        String trimmedSearchString = searchString.trim();
        FileInputStream fis = new FileInputStream(SimilarityService.FILEPATH);
        String data = IOUtils.toString(fis, StandardCharsets.UTF_8);
        return Arrays.stream(data.replaceAll(SimilarityService.REGEX, "").split("\\s"))
                            .filter(s -> s.equals(trimmedSearchString)).count();
    }
}
