package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.Volunteer;
import org.SafnaRafic.emergencydisasterhelpline.models.data.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("volunteers")
@Controller
public class VolunteerController {
    @Autowired
    private VolunteerRepository volunteerRepository;

    @GetMapping("add")
    public String displayAddVolunteerForm(Model model) {
        model.addAttribute(new Volunteer());
        return "volunteers/add";
    }

    @PostMapping("add")
    public String processAddVolunteerForm(@ModelAttribute @Valid Volunteer newVolunteer,
                                          Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "volunteers/add";
        }
        volunteerRepository.save(newVolunteer);
        return "redirect:/";
    }

    @GetMapping("index")
    public String displayAllVolunteers(Model model) {

        model.addAttribute("volunteers", volunteerRepository.findAll());
        return "volunteers/index";
    }

    @GetMapping("delete/{volunteerId}")
    public String displayDeleteVolunteerForm(Model model, @PathVariable int volunteerId) {
        Optional volunteerToDelete = volunteerRepository.findById(volunteerId);
        if (volunteerToDelete.isPresent()) {
            Volunteer volunteer = (Volunteer) volunteerToDelete.get();
            model.addAttribute("volunteer", volunteer);
            return "volunteers/delete";
        } else {
            return "redirect:";
        }
    }

    @PostMapping("delete")
    public String processDeleteVolunteerForm(@RequestParam(required = false) int[] volunteerId) {

        if (volunteerId != null) {
            for (int id : volunteerId) {

                volunteerRepository.deleteById(id);
            }
        }

        return "redirect:/volunteers/index";
    }

    @GetMapping("update/{volunteerId}")
    public String displayUpdateVolunteerForm(Model model, @PathVariable int volunteerId) {
        Optional volunteerToUpdate = volunteerRepository.findById(volunteerId);
        if (volunteerToUpdate.isPresent()) {
            Volunteer volunteer = (Volunteer) volunteerToUpdate.get();
            model.addAttribute("volunteer", volunteer);
            return "volunteers/update";
        } else {
            return "redirect:";
        }

    }


    @PostMapping("update")
    public String processUpdateVolunteerForm(int volunteerId, String name, String address, String city, String state, String zipcode,
                                             String contactNo,String daysOfAvailability,String timeAvailability,String volunteerCategory) {

        Optional volunteerToUpdate = volunteerRepository.findById(volunteerId);
        if (volunteerToUpdate.isPresent()) {
            Volunteer volunteer = (Volunteer) volunteerToUpdate.get();
            volunteer.setName(name);
            volunteer.setAddress(address);
            volunteer.setCity(city);
            volunteer.setState(state);
            volunteer.setZipcode(zipcode);
            volunteer.setContactNo(contactNo);
            volunteer.setDaysOfAvailability(daysOfAvailability);
            volunteer.setTimeAvailability(timeAvailability);
            volunteer.setVolunteerCategory(volunteerCategory);
            volunteerRepository.save(volunteer);
            return "redirect:/volunteers/index";
        }

        return "redirect:/";
    }

}
