package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.SafnaRafic.emergencydisasterhelpline.models.DaysAvailability;
import org.SafnaRafic.emergencydisasterhelpline.models.Needed;
import org.SafnaRafic.emergencydisasterhelpline.models.Volunteer;
import org.SafnaRafic.emergencydisasterhelpline.models.data.DaysAvailabilityRepository;
import org.SafnaRafic.emergencydisasterhelpline.models.data.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("volunteers")
@Controller
public class VolunteerController {
    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private DaysAvailabilityRepository daysAvailabilityRepository;

    @GetMapping("add")
    public String displayAddVolunteerForm(Model model) {
        model.addAttribute(new Volunteer());
        model.addAttribute("daysAvailabilities",daysAvailabilityRepository.findAll());
        return "volunteers/add";
    }

    @PostMapping("add")
    public String processAddVolunteerForm(@ModelAttribute @Valid Volunteer newVolunteer, @RequestParam List<Integer> days,
                                          Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "volunteers/add";
        }
        List<DaysAvailability> daysAvailabilities = (List<DaysAvailability>) daysAvailabilityRepository.findAllById(days);
        newVolunteer.setDaysAvailability(daysAvailabilities);
        volunteerRepository.save(newVolunteer);
        return "redirect:/";
    }

    @GetMapping("index")
    public String displayAllVolunteers(Model model) {

        model.addAttribute("volunteers", volunteerRepository.findAll());
        model.addAttribute("daysAvailabilities",daysAvailabilityRepository.findAll());
        return "volunteers/index";
    }

    @GetMapping("view/{id}")
    public String displayViewVolunteer(Model model,@PathVariable int id){
        Optional<Volunteer> optionalVolunteer=volunteerRepository.findById(id);
        if(optionalVolunteer.isPresent()){
            Volunteer volunteer=optionalVolunteer.get();
            model.addAttribute("volunteer",volunteer);
            return "volunteers/view";
        }else{
            return "volunteers/index";
        }

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
            model.addAttribute("daysAvailabilities",daysAvailabilityRepository.findAll());
            return "volunteers/update";
        } else {
            return "redirect:";
        }

    }


    @PostMapping("update")
    public String processUpdateVolunteerForm(int volunteerId, String name, String address, String city, String state, String zipcode,
                                             String contactNo,@RequestParam List<Integer> daysAvailability, String timeAvailability,
                                             String volunteerCategory) {

        Optional volunteerToUpdate = volunteerRepository.findById(volunteerId);
        if (volunteerToUpdate.isPresent()) {
            Volunteer volunteer = (Volunteer) volunteerToUpdate.get();
            volunteer.setName(name);
            volunteer.setAddress(address);
            volunteer.setCity(city);
            volunteer.setState(state);
            volunteer.setZipcode(zipcode);
            volunteer.setContactNo(contactNo);

            List<DaysAvailability> daysAvailabilities = (List<DaysAvailability>) daysAvailabilityRepository.findAllById(daysAvailability);
            volunteer.setDaysAvailability(daysAvailabilities);

            volunteer.setTimeAvailability(timeAvailability);
            volunteer.setVolunteerCategory(volunteerCategory);
            volunteerRepository.save(volunteer);
            return "redirect:/volunteers/index";
        }

        return "redirect:/";
    }

}
