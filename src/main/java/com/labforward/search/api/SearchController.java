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
    static final String REGEX = "[^a-zA-Z0-9 \\s]";

    public FrequencyService frequencyService;
    public SimilarityService similarityService;

    public SearchController(FrequencyService frequencyService, SimilarityService similarityService) {
        this.frequencyService = frequencyService;
        this.similarityService = similarityService;
    }

    @PostMapping(value = "/search")
    public String search(@RequestParam(value = "searchInput", required = false) String searchInput,
                         @RequestParam(value = "findingsDoc", required = false) String findingsDoc,
                         RedirectAttributes redirectAttributes) throws IOException {
        if (searchInput != null && !searchInput.isEmpty() && findingsDoc != null && !findingsDoc.isEmpty()) {
            String trimmedSearchString = searchInput.trim();
            String[] formattedString = findingsDoc.replaceAll(REGEX, "").split("\\s");
            long count = frequencyService.getCount(trimmedSearchString, formattedString);
            Set<String> similarWords = similarityService.getSimilarWords(trimmedSearchString, formattedString);

            redirectAttributes.addFlashAttribute(FLASH_ATTRIBUTE_COUNT, "The word \"" + trimmedSearchString + "\" appears " + count + " times in the text.");
            redirectAttributes.addFlashAttribute("findingsDoc", findingsDoc);

            if (similarWords.size() == 0) {
                redirectAttributes.addFlashAttribute(FLASH_ATTRIBUTE_SIMILAR, "Sorry! No similar words found.");
            } else {
                redirectAttributes.addFlashAttribute(FLASH_ATTRIBUTE_SIMILAR, "Similar words for \"" + trimmedSearchString + "\" are: " + similarWords);
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
