package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodGroup;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodGroupRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("bloodgroups")
public class BloodGroupController {
    @Autowired
    private BloodGroupRepository bloodGroupRepository;

    @GetMapping("add")
    public String displayAddGroupForm(Model model){
        model.addAttribute(new BloodGroup());

        return "bloodgroups/add";
    }

    @PostMapping("add")
    public String processAddGroupForm(@ModelAttribute @Valid BloodGroup newBloodGroup, Errors errors, Model model){
        if(errors.hasErrors()){
            return "bloodgroups/add";
        }
        bloodGroupRepository.save(newBloodGroup);
        return "redirect:/";
    }
}
