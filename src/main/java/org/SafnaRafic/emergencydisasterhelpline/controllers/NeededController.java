package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.Needed;
import org.SafnaRafic.emergencydisasterhelpline.models.data.NeededRepository;
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
@RequestMapping("needs")
public class NeededController {
    @Autowired
    private NeededRepository neededRepository;

    @GetMapping("add")
    public String displayAddNeedsForm(Model model){
        model.addAttribute(new Needed());
        return "needs/add";
    }

    @PostMapping("add")
    public String processAddNeedsForm(@ModelAttribute @Valid Needed newNeeded, Errors errors,Model model){
       if(errors.hasErrors()){
           return "needs/add";
       }
       neededRepository.save(newNeeded);
        return "redirect:/";
    }
}
