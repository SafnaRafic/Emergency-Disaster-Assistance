package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.Volunteer;
import org.SafnaRafic.emergencydisasterhelpline.models.data.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("volunteers")
@Controller
public class VolunteerController {
    @Autowired
    private VolunteerRepository volunteerRepository;

    @GetMapping("add")
    public String displayAddVolunteerForm(Model model) {
        model.addAttribute(new Volunteer());
        return "volunteers/add";
    }

    @PostMapping("add")
    public String processAddVolunteerForm(@ModelAttribute @Valid Volunteer newVolunteer,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "volunteers/add";
        }
        volunteerRepository.save(newVolunteer);
        return "redirect:/";
    }
}
