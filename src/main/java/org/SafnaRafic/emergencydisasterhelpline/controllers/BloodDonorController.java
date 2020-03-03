package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.data.BloodDonorRepository;
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
    @GetMapping("index")
    public String displayAllDonors(Model model) {
        model.addAttribute("title", "All Blood Donors");
        model.addAttribute("donors", bloodDonorRepository.findAll());
        return "bloodDonors/index";
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
        Optional donorToUpdate = bloodDonorRepository.findById(donorId);
        if (donorToUpdate.isPresent()) {
            BloodDonor bloodDonor = (BloodDonor) donorToUpdate.get();
            model.addAttribute("bloodDonor", bloodDonor);
            return "bloodDonors/update";
        } else {
            return "redirect:";
        }

    }

    @PostMapping("update")
    public String processUpdateDonorForm(int donorId, String name, String address,String city, String state, String zipcode, String contactNo,String bloodGroup) {

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
