package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.Volunteer;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodDonorRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodGroupRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.VolunteerRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.dto.BloodDonorData;
import org.SafnaRafic.emergencydisasterhelpline.models.dto.VolunteerData;
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
@RequestMapping("searchVolunteer")
public class SearchVolunteerController {

    @Autowired
    private VolunteerRepository volunteerRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public SearchVolunteerController() {
        columnChoices.put("all", "All");
        columnChoices.put("days", "DaysAvailability");
        columnChoices.put("city","City");
        columnChoices.put("state","State");
        columnChoices.put("zipcode", "Zipcode");

    }

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "searchVolunteer";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        Iterable<Volunteer> volunteers;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            volunteers = volunteerRepository.findAll();
        } else {
            volunteers= VolunteerData.findByColumnAndValue(searchType, searchTerm,volunteerRepository.findAll());
        }
        int counter = 0;
        for (Object i : volunteers) {
            counter++;
        }
        if(counter == 0){
            model.addAttribute("empty","No results found !");
        }
        model.addAttribute("columns",columnChoices);
        model.addAttribute("volunteers",volunteers);

        return "searchVolunteer";
    }




}
