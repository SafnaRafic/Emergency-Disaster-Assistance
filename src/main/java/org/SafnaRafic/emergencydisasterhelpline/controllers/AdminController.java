package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.BloodGroup;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodDonorRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("admin")
@Controller
public class AdminController {
    @Autowired
    private BloodDonorRepository bloodDonorRepository;

    @Autowired
    private BloodGroupRepository bloodGroupRepository;

    @GetMapping("index")
    public String adminIndex(){
        return "admin/index";
    }

    //Blood Donors Update and Delete
    @GetMapping("bloodDonors/delete/{donorId}")
    public String displayDeleteDonorForm(Model model, @PathVariable int donorId) {
        Optional donorToDelete = bloodDonorRepository.findById(donorId);
        if (donorToDelete.isPresent()) {
            BloodDonor bloodDonor = (BloodDonor) donorToDelete.get();
            model.addAttribute("bloodDonor", bloodDonor);
            return "admin/bloodDonors/delete";
        } else {
            return "redirect:";
        }
    }

    @PostMapping("bloodDonors/delete")
    public String processDeleteDonorForm(@RequestParam(required = false) int[] donorId) {

        if (donorId != null) {
            for (int id : donorId) {

                bloodDonorRepository.deleteById(id);
            }
        }

        return "redirect:/admin/index";
    }

    @GetMapping("bloodDonors/update/{donorId}")
    public String displayUpdateDonorForm(Model model,@PathVariable int donorId) {
        model.addAttribute("bloodGroups",bloodGroupRepository.findAll());
        Optional donorToUpdate = bloodDonorRepository.findById(donorId);
        if (donorToUpdate.isPresent()) {
            BloodDonor bloodDonor = (BloodDonor) donorToUpdate.get();
            model.addAttribute("bloodDonor", bloodDonor);
            model.addAttribute("bloodGroups",bloodGroupRepository.findAll());
            return "admin/bloodDonors/update";
        } else {
            return "redirect:";
        }

    }

    @PostMapping("bloodDonors/update")
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
            return "redirect:/admin/bloodDonors/index";
        }

        return "redirect:/";
    }

}
