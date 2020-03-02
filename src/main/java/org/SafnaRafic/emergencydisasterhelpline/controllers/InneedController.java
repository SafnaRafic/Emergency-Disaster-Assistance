package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.Inneed;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodDonorRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.InneedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@RequestMapping("inneeds")
@Controller
public class InneedController {
    @Autowired
    private InneedRepository inneedRepository;

    @GetMapping("add")
    public String displayAddInneedForm(Model model) {
        model.addAttribute(new Inneed());
        return "inneeds/add";
    }

    @PostMapping("add")
    public String processAddInneedForm(@ModelAttribute @Valid Inneed newInneed,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "inneeds/add";
        }
        inneedRepository.save(newInneed);
        return "redirect:/";
    }
}
