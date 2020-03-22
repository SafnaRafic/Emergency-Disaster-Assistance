package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.EmergencyTips;
import org.SafnaRafic.emergencydisasterhelpline.models.data.EmergencyTipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("emergencyTips")
public class EmergencyTipsController {

    @Autowired
    private EmergencyTipsRepository emergencyTipsRepository;

    @GetMapping("add")
    public String displayAddEmergencyTips(Model model){
        model.addAttribute(new EmergencyTips());
        return "emergencyTips/add";
    }

    @PostMapping("add")
    public String processEmergencyTipsForm(@ModelAttribute @Valid EmergencyTips newEmergencyTips, Errors errors,Model model){
        if(errors.hasErrors()){
            return "emergencyTips/add";
        }
        emergencyTipsRepository.save(newEmergencyTips);
        return "redirect:/emergencyTips/index";
    }

    @GetMapping("index")
    public String displayEmergencyIndexForm(Model model){
        model.addAttribute("tips",emergencyTipsRepository.findAll());
        return "emergencyTips/index";
    }
    @GetMapping("view")
    public String displayEmergencyTipsForm(Model model){
        model.addAttribute("tips",emergencyTipsRepository.findAll());
        return "emergencyTips/view";
    }

    @GetMapping("update/{tipsId}")
    public String displayUpdateTipsForm(Model model, @PathVariable int tipsId){
        Optional<EmergencyTips> optionalEmergencyTips=emergencyTipsRepository.findById(tipsId);
        if(optionalEmergencyTips.isPresent()){
            EmergencyTips emergencyTips=(EmergencyTips) optionalEmergencyTips.get();
            model.addAttribute("emergencyTips", emergencyTips);
            return "emergencyTips/update";
        }
        return "redirect:/";
    }
    @PostMapping("update")
    public String processUpdateTipsForm(String name,String description, @RequestParam int tipsId){
        Optional<EmergencyTips> optionalEmergencyTips=emergencyTipsRepository.findById(tipsId);
        if(optionalEmergencyTips.isPresent()){
            EmergencyTips emergencyTips=optionalEmergencyTips.get();
            emergencyTips.setName(name);
            emergencyTips.setDescription(description);
            emergencyTipsRepository.save(emergencyTips);
            return "redirect:/emergencyTips/index";
        }
        return "emergencyTips/update";
    }

    @GetMapping("delete/{tipsId}")
    public String displayDeleteTipsForm(Model model, @PathVariable int tipsId){
        Optional<EmergencyTips> optionalEmergencyTips=emergencyTipsRepository.findById(tipsId);
        if(optionalEmergencyTips.isPresent()){
            EmergencyTips emergencyTips=(EmergencyTips) optionalEmergencyTips.get();
            model.addAttribute("emergencyTips", emergencyTips);
            return "emergencyTips/delete";
        }
        return "redirect:/";
    }
    @PostMapping("delete")
    public String processDeleteTipsForm(String name,String description, @RequestParam int tipsId) {
        Optional<EmergencyTips> optionalEmergencyTips = emergencyTipsRepository.findById(tipsId);
        if (optionalEmergencyTips.isPresent()) {
            EmergencyTips emergencyTips = optionalEmergencyTips.get();
            emergencyTipsRepository.delete(emergencyTips);
            return "redirect:/emergencyTips/index";
        }
        return "emergencyTips/delete";
    }
}
