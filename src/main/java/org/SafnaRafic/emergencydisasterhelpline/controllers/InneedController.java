package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.Inneed;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodDonorRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.InneedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    @GetMapping("index")
    public String displayAllInneeds(Model model) {

        model.addAttribute("inneeds", inneedRepository.findAll());
        return "inneeds/index";
    }

    @GetMapping("delete/{inneedId}")
    public String displayDeleteInneedForm(Model model,@PathVariable int inneedId) {
        Optional inneedToDelete = inneedRepository.findById(inneedId);
        if (inneedToDelete.isPresent()) {
            Inneed inneed = (Inneed) inneedToDelete.get();
            model.addAttribute("inneed", inneed);
            return "inneeds/delete";
        } else {
            return "redirect:";
        }
    }

    @PostMapping("delete")
    public String processDeleteInneedForm(@RequestParam(required = false) int[] inneedId) {

        if (inneedId != null) {
            for (int id : inneedId) {

                inneedRepository.deleteById(id);
            }
        }

        return "redirect:/inneeds/index";
    }

    @GetMapping("update/{inneedId}")
    public String displayUpdateInneedForm(Model model,@PathVariable int inneedId) {
        Optional inneedToUpdate = inneedRepository.findById(inneedId);
        if (inneedToUpdate.isPresent()) {
            Inneed inneed = (Inneed) inneedToUpdate.get();
            model.addAttribute("inneed", inneed);
            return "inneeds/update";
        } else {
            return "redirect:";
        }

    }

    @PostMapping("update")
    public String processUpdateInneedForm(int inneedId, String name, String in_need,int quantity) {

        Optional inneedToUpdate = inneedRepository.findById(inneedId);
            if (inneedToUpdate.isPresent()) {
            Inneed inneed = (Inneed) inneedToUpdate.get();
            inneed.setName(name);
           inneed.setIn_need(in_need);
           inneed.setQuantity(quantity);
            inneedRepository.save(inneed);
            return "redirect:/inneeds/index";
        }

        return "redirect:/";
    }


}
