package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.BloodGroup;
import org.SafnaRafic.emergencydisasterhelpline.models.Inneed;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodDonorRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("bloodDonors")
public class BloodDonorController {

    @Autowired
    private BloodDonorRepository bloodDonorRepository;

    @Autowired
    private BloodGroupRepository bloodGroupRepository;

    @GetMapping("add")
    public String displayAddDonorForm(Model model) {
        model.addAttribute(new BloodDonor());
        model.addAttribute("bloodGroups",bloodGroupRepository.findAll());
        return "bloodDonors/add";
    }

    @PostMapping("add")
    public String processAddDonorForm(@ModelAttribute @Valid BloodDonor newBloodDonor, @RequestParam int groupId,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "bloodDonors/add";
        }
        Optional<BloodGroup> optBloodGroup=bloodGroupRepository.findById(groupId);
        if(optBloodGroup.isPresent()){
            BloodGroup bloodGroup=(BloodGroup)optBloodGroup.get();
            newBloodDonor.setBloodGroup(bloodGroup);
            model.addAttribute("group",bloodGroup.getBloodType());
        }
        bloodDonorRepository.save(newBloodDonor);
        return "redirect:/";
    }
    @GetMapping("index")
    public String displayAllDonors(Model model) {
        model.addAttribute("title", "All Blood Donors");
        model.addAttribute("donors", bloodDonorRepository.findAll());
        model.addAttribute("bloodGroups",bloodGroupRepository.findAll());
        return "bloodDonors/index";
    }

//    @GetMapping("view/{id}")
//    public String displayViewBloodDonor(Model model,@PathVariable int id){
//        Optional<BloodDonor> optionalBloodDonor=bloodDonorRepository.findById(id);
//        if(optionalBloodDonor.isPresent()){
//            BloodDonor bloodDonor=(BloodDonor) optionalBloodDonor.get();
//            model.addAttribute("bloodDonor",bloodDonor);
//        }
//        return "bloodDonors/view";
//    }
    @GetMapping("view/{id}")
    public String displayViewInneed(Model model, @PathVariable int id){
        Optional<BloodDonor> optBloodDonor=bloodDonorRepository.findById(id);
        if(optBloodDonor.isPresent()){
            BloodDonor bloodDonor=(BloodDonor) optBloodDonor.get();
            model.addAttribute("bloodDonor",bloodDonor);
        }
        return "bloodDonors/view";
    }

    @GetMapping("delete/{donorId}")
    public String displayDeleteDonorForm(Model model,@PathVariable int donorId) {
        Optional donorToDelete = bloodDonorRepository.findById(donorId);
        if (donorToDelete.isPresent()) {
            BloodDonor bloodDonor = (BloodDonor) donorToDelete.get();
            model.addAttribute("bloodDonor", bloodDonor);
            return "bloodDonors/delete";
        } else {
            return "redirect:";
        }
    }

    @PostMapping("delete")
    public String processDeleteDonorForm(@RequestParam(required = false) int[] donorId) {

        if (donorId != null) {
            for (int id : donorId) {

                bloodDonorRepository.deleteById(id);
            }
        }

        return "redirect:/bloodDonors/index";
    }

    @GetMapping("update/{donorId}")
    public String displayUpdateDonorForm(Model model,@PathVariable int donorId) {
        model.addAttribute("bloodGroups",bloodGroupRepository.findAll());
        Optional donorToUpdate = bloodDonorRepository.findById(donorId);
        if (donorToUpdate.isPresent()) {
            BloodDonor bloodDonor = (BloodDonor) donorToUpdate.get();
            model.addAttribute("bloodDonor", bloodDonor);
            model.addAttribute("bloodGroups",bloodGroupRepository.findAll());
            return "bloodDonors/update";
        } else {
            return "redirect:";
        }

    }

    @PostMapping("update")
    public String processUpdateDonorForm(int donorId, String name, String address, String city,
                                         String state, String zipcode, String contactNo,@RequestParam BloodGroup bloodGroup) {

        Optional donorToUpdate = bloodDonorRepository.findById(donorId);
        if (donorToUpdate.isPresent()) {
            BloodDonor bloodDonor = (BloodDonor) donorToUpdate.get();
            bloodDonor.setName(name);
            bloodDonor.setAddress(address);
            bloodDonor.setCity(city);
            bloodDonor.setState(state);
            bloodDonor.setZipcode(zipcode);
            bloodDonor.setContactNo(contactNo);
            bloodDonor.setBloodGroup(bloodGroup);
            bloodDonorRepository.save(bloodDonor);
            return "redirect:/bloodDonors/index";
        }

        return "redirect:/";
    }


}
