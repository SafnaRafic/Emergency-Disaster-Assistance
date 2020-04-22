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

import java.util.Collection;
import java.util.HashMap;

@Controller
@RequestMapping("search")
public class SearchDonorController {

    @Autowired
    private BloodDonorRepository bloodDonorRepository;
    @Autowired
    private BloodGroupRepository bloodGroupRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public SearchDonorController() {
        columnChoices.put("all", "All");
        columnChoices.put("bloodgroup", "BloodGroup");
        columnChoices.put("city","City");
        columnChoices.put("state","State");
        columnChoices.put("zipcode", "Zipcode");

    }

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("bloodGroups",bloodGroupRepository.findAll());
        model.addAttribute("title", "Blood Donor");
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm, @RequestParam String searchTerm1) {
        Iterable<BloodDonor> bloodDonors;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            bloodDonors = bloodDonorRepository.findAll();
        } else {
            bloodDonors= BloodDonorData.findByColumnAndValue(searchType, searchTerm,searchTerm1, bloodDonorRepository.findAll());
        }
        model.addAttribute("columns",columnChoices);
        model.addAttribute("bloodDonors",bloodDonors);
        model.addAttribute("bloodGroups",bloodGroupRepository.findAll());
        int counter = 0;
        for (Object i : bloodDonors) {
            counter++;
        }
        if(counter == 0){
            model.addAttribute("empty","No results found !");
        }
        return "search";
    }




}
