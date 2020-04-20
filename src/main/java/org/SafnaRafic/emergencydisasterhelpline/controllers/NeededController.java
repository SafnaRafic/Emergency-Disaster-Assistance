package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodGroup;
import org.SafnaRafic.emergencydisasterhelpline.models.Needed;
import org.SafnaRafic.emergencydisasterhelpline.models.data.NeededRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
        return "redirect:/needs/index";
    }
    @GetMapping("index")
    public String displayNeedsIndex(Model model,@RequestParam(defaultValue = "0") int page){
        model.addAttribute("needs",neededRepository.findAll(PageRequest.of(page,5)));
        return "needs/index";
    }

    @GetMapping("delete/{needId}")
    public String displayDeleteBloodGroup(Model model, @PathVariable int needId ){
        Optional<Needed> optionalNeeded=neededRepository.findById(needId);
        if(optionalNeeded.isPresent()){
            Needed needed=(Needed) optionalNeeded.get();
            model.addAttribute("needed",needed );
        }
        return "needs/delete";
    }

    @PostMapping("delete")
    public String processDeleteNeeded(Model model,@RequestParam int needId) {
        Optional<Needed> optionalNeeded = neededRepository.findById(needId);
        if (optionalNeeded.isPresent()) {
            Needed needed = optionalNeeded.get();
            neededRepository.delete(needed);
            return "redirect:/needs/index";
        } else {
            return "needs/delete";
        }
    }

    @GetMapping("update/{needId}")
    public String displayupdateNeeded(Model model, @PathVariable int needId){
        Optional<Needed> optionalNeeded=neededRepository.findById(needId);
        if(optionalNeeded.isPresent()){
            Needed needed=optionalNeeded.get();
            model.addAttribute("needed",needed);
            return "needs/update";
        }else {
            return "redirect:";
        }
    }

    @PostMapping("update")
    public String processUpdateNeeded(Model model,@RequestParam int needId,@RequestParam String need) {
        Optional<Needed> optionalNeeded = neededRepository.findById(needId);
        if (optionalNeeded.isPresent()) {
            Needed needed = optionalNeeded.get();
            needed.setNeed(need);
            neededRepository.save(needed);
            return "redirect:/needs/index";
        }
        return "needs/delete";
    }

}
