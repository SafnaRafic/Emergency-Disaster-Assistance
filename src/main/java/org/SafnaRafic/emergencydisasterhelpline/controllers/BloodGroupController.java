package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodGroup;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodGroupRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
        return "redirect:/bloodgroups/index";
    }

    @GetMapping("index")
    public String displayBloodGroupIndex(Model model, @RequestParam(defaultValue = "0") int page){
        model.addAttribute("bloodGroups",bloodGroupRepository.findAll(PageRequest.of(page,5)));
        return "bloodgroups/index";
    }

    @GetMapping("delete/{bloodGroupId}")
    public String displayDeleteBloodGroup(Model model, @PathVariable int bloodGroupId ){
        Optional<BloodGroup> bloodGroupOptional=bloodGroupRepository.findById(bloodGroupId);
        if(bloodGroupOptional.isPresent()){
            BloodGroup bloodGroup=bloodGroupOptional.get();
            model.addAttribute("bloodGroup",bloodGroup);
        }
        return "bloodgroups/delete";
    }

    @PostMapping("delete")
    public String processDeleteBloodGroup(Model model,@RequestParam int bloodGroupId) {
        Optional<BloodGroup> bloodGroupOptional = bloodGroupRepository.findById(bloodGroupId);
        if (bloodGroupOptional.isPresent()) {
            BloodGroup bloodGroup = bloodGroupOptional.get();
            bloodGroupRepository.delete(bloodGroup);
            return "redirect:/bloodgroups/index";
        }
        return "bloodgroups/delete";
    }

    @GetMapping("update/{bloodGroupId}")
    public String displayupdateDeleteBloodGroup(Model model, @PathVariable int bloodGroupId){
        Optional<BloodGroup> bloodGroupOptional=bloodGroupRepository.findById(bloodGroupId);
        if(bloodGroupOptional.isPresent()){
            BloodGroup bloodGroup=bloodGroupOptional.get();
            model.addAttribute("bloodGroup",bloodGroup);
            return "bloodgroups/update";
        }else {
            return "redirect:";
        }
    }

    @PostMapping("update")
    public String processUpdateBloodGroup(Model model,@RequestParam int bloodGroupId,@RequestParam String bloodType) {
        Optional<BloodGroup> bloodGroupOptional = bloodGroupRepository.findById(bloodGroupId);
        if (bloodGroupOptional.isPresent()) {
            BloodGroup bloodGroup = bloodGroupOptional.get();
            bloodGroup.setBloodType(bloodType);
            bloodGroupRepository.save(bloodGroup);
            return "redirect:/bloodgroups/index";
        }
        return "bloodgroups/delete";
    }

}
