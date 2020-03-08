package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodDonorRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodGroupRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.dto.BloodDonorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("search")
public class SearchController {
//    @Autowired
//    private BloodGroupRepository bloodGroupRepository;

    @Autowired
    private BloodDonorRepository bloodDonorRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public SearchController() {
        columnChoices.put("all", "All");
        columnChoices.put("zipcode", "Zipcode");
        columnChoices.put("bloodgroup", "BloodGroup");

    }

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        Iterable<BloodDonor> bloodDonors;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            bloodDonors = bloodDonorRepository.findAll();
        } else {
            bloodDonors= BloodDonorData.findByColumnAndValue(searchType, searchTerm,bloodDonorRepository.findAll());
        }
        model.addAttribute("columns",columnChoices);
        model.addAttribute("bloodDonors",bloodDonors);
        return "search";
    }




}
