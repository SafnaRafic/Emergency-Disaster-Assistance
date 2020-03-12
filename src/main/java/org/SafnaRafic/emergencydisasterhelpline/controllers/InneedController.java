package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.Inneed;
import org.SafnaRafic.emergencydisasterhelpline.models.Needed;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodDonorRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.InneedRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.NeededRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("inneeds")
@Controller
public class InneedController {
    @Autowired
    private InneedRepository inneedRepository;

    @Autowired
    private NeededRepository neededRepository;

    @GetMapping("add")
    public String displayAddInneedForm(Model model) {
        model.addAttribute(new Inneed());
        model.addAttribute("needs",neededRepository.findAll());
        return "inneeds/add";
    }

    @PostMapping("add")
    public String processAddInneedForm(@ModelAttribute @Valid Inneed newInneed, @RequestParam List<Integer> needs,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "inneeds/add";
        }
        List<Needed> needObj = (List<Needed>) neededRepository.findAllById(needs);
        newInneed.setNeeds(needObj);
        inneedRepository.save(newInneed);
        return "redirect:/";
    }
    @GetMapping("index")
    public String displayAllInneeds(Model model) {

        model.addAttribute("inneeds", inneedRepository.findAll());
        model.addAttribute("needs",neededRepository.findAll());
        return "inneeds/index";
    }

    @GetMapping("view/{id}")
    public String displayViewInneed(Model model, @PathVariable int id){
        Optional<Inneed> optInneed=inneedRepository.findById(id);
        if(optInneed.isPresent()){
            Inneed inneed=(Inneed)optInneed.get();
            model.addAttribute("inneed",inneed);
        }
        return "inneeds/view";
    }
    @GetMapping("sponsor")
    public String displaySponsorNeeds(Model model){
        model.addAttribute("inneeds",inneedRepository.findAll());
        model.addAttribute("needs",neededRepository.findAll());
        return "inneeds/sponsor";
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
            model.addAttribute("needs",neededRepository.findAll());

            return "inneeds/update";
        } else {
            return "redirect:";
        }

    }

    @PostMapping("update")
    public String processUpdateInneedForm(int inneedId, String name, List<Integer> needId,int quantity) {

        Optional inneedToUpdate = inneedRepository.findById(inneedId);
            if (inneedToUpdate.isPresent()) {
            Inneed inneed = (Inneed) inneedToUpdate.get();
            inneed.setName(name);
            List<Needed> needObj = (List<Needed>) neededRepository.findAllById(needId);
            inneed.setNeeds(needObj);
            inneed.setQuantity(quantity);
            inneedRepository.save(inneed);
            return "redirect:/inneeds/index";
        }

        return "redirect:/";
    }


}
