package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.Inneed;
import org.SafnaRafic.emergencydisasterhelpline.models.data.InneedRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.NeededRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.dto.InneedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("searchInneeds")
public class searchInneedController {
    @Autowired
    private InneedRepository inneedRepository;
    @Autowired
    private NeededRepository neededRepository;

    static HashMap<String, String> columnChoices=new HashMap<>();

    public searchInneedController(){
        columnChoices.put("all","All");
        columnChoices.put("needs","Needs");
        columnChoices.put("zipcode","Zipcode");
        columnChoices.put("city","City");
        columnChoices.put("state","State");
    }

    @RequestMapping("")
    public String search(Model model){
        model.addAttribute("columns",columnChoices);
        return "searchInneeds";
    }

    @PostMapping("results")
    public String processSearch(Model model, @RequestParam String searchTerm, @RequestParam String searchType){
        Iterable<Inneed> inneeds;
        if(searchTerm.toLowerCase().equals("all")|| searchTerm.equals("")){
            inneeds=inneedRepository.findAll();
        }else{
            inneeds= InneedData.findByColumnAndValue(searchType,searchTerm,inneedRepository.findAll());
        }
        int counter = 0;
        for (Object i : inneeds) {
            counter++;
        }
        if(counter == 0) {
            model.addAttribute("empty", "No results found !");
        }
        model.addAttribute("inneeds",inneeds);
        model.addAttribute("needs",neededRepository.findAll());
        model.addAttribute("columns",columnChoices);
        return "searchInneeds";
    }




}
