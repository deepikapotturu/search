package com.labforward.search.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SearchControllerTest {

    public static final String END_POINT = "/search";
    public static final String PARAMETER_NAME = "searchInput";
    public static final String FLASH_ATTRIBUTE_COUNT = "count";
    public static final String FLASH_ATTRIBUTE_SIMILAR = "similarWords";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void search_getRequest() throws Exception {
        this.mockMvc.perform(get(END_POINT))
                    .andExpect(status().isOk());
    }

    @Test
    public void search_postRequestHasFlashAttributes() throws Exception {
        this.mockMvc.perform(post(END_POINT).param(PARAMETER_NAME, "Foo"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(END_POINT))
                .andExpect(flash().attribute(FLASH_ATTRIBUTE_COUNT, "The word \"Foo" + "\" appears 0" + " times in the text."))
                .andExpect(flash().attribute(FLASH_ATTRIBUTE_SIMILAR, "Sorry! No similar words found."));
    }

    @Test
    public void search_postRequestHasFlashAttributesFoundWords() throws Exception {
        this.mockMvc.perform(post(END_POINT).param("searchInput", "Lorem"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(END_POINT))
                .andExpect(flash().attribute(FLASH_ATTRIBUTE_COUNT, "The word \"Lorem" + "\" appears 2" + " times in the text."))
                .andExpect(flash().attribute(FLASH_ATTRIBUTE_SIMILAR, "Similar words for \"Lorem" + "\" are: " + "[lorem]"));
    }

    @Test
    public void search_postRequest() throws Exception {
        this.mockMvc.perform(post(END_POINT).param(PARAMETER_NAME, ""))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void search_getRequestShowsSearchBox() throws Exception {
        this.mockMvc.perform(get(END_POINT))
                .andExpect(status().isOk())
                .andExpect(xpath("//form/div/input[@name='searchInput']").exists());
    }

}