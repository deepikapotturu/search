package com.labforward.search.api;

import com.labforward.search.service.FrequencyService;
import com.labforward.search.service.SimilarityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Set;

@Controller
public class SearchController {

    public static final String TEMPLATE = "notebook";
    public static final String FLASH_ATTRIBUTE_SIMILAR = "similarWords";
    public static final String FLASH_ATTRIBUTE_COUNT = "count";


    public FrequencyService frequencyService;
    public SimilarityService similarityService;

    public SearchController(FrequencyService frequencyService, SimilarityService similarityService) {
        this.frequencyService = frequencyService;
        this.similarityService = similarityService;
    }

    @RequestMapping(value = "/search")
    public String search(@RequestParam(value = "searchInput", required = false) String searchInput,
                         RedirectAttributes redirectAttributes) throws IOException {
        if (!searchInput.isEmpty()) {
            long count = frequencyService.getCount(searchInput);
            Set<String> similarWords = similarityService.getSimilarWords(searchInput);
            redirectAttributes.addFlashAttribute(FLASH_ATTRIBUTE_COUNT, "The word \"" + searchInput + "\" appears " + count + " times in the text.");
            if (similarWords.size() == 0) {
                redirectAttributes.addFlashAttribute(FLASH_ATTRIBUTE_SIMILAR, "Sorry! No similar words found.");
            } else {
                redirectAttributes.addFlashAttribute(FLASH_ATTRIBUTE_SIMILAR, "Similar words for \"" + searchInput + "\" are: " + similarWords);
            }
            return "redirect:/search";
        }

        return TEMPLATE;
    }

    @GetMapping(value = "/search")
    public String search() {
        return TEMPLATE;
    }
}
