package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodDonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("bloodDonors")
public class BloodDonorController {

    @Autowired
    private BloodDonorRepository bloodDonorRepository;

    @GetMapping("add")
    public String displayAddDonorForm(Model model) {
        model.addAttribute(new BloodDonor());
        return "bloodDonors/add";
    }

    @PostMapping("add")
    public String processAddDonorForm(@ModelAttribute @Valid BloodDonor newBloodDonor,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "bloodDonors/add";
        }
        bloodDonorRepository.save(newBloodDonor);
        return "redirect:/";
    }


}
