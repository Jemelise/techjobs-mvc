package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        ArrayList<HashMap<String, String>> searchResults;

        if (searchType.equals("all")) {
            searchResults = JobData.findByValue(searchTerm);
        }
        else {

            searchResults = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("results", searchResults);
        model.addAttribute("columns", ListController.columnChoices);

        return "search";
    }
}
