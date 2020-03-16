package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.DaysAvailability;
import org.SafnaRafic.emergencydisasterhelpline.models.data.DaysAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("daysAvailability")
public class DaysAvailabilityController {
    @Autowired
    private DaysAvailabilityRepository daysAvailabilityRepository;

    @GetMapping("add")
    public String displayAddDaysForm(Model model){
        model.addAttribute(new DaysAvailability());
        return "daysAvailability/add";
    }

    @PostMapping("add")
    public String processAddDaysForm(@ModelAttribute @Valid DaysAvailability newDaysAvailability, Errors errors,Model model){
        if(errors.hasErrors()){
            return "daysAvailability/add";
        }
        daysAvailabilityRepository.save(newDaysAvailability);
        return "redirect:/daysAvailability/index";
    }

    @GetMapping("index")
    public String displayIndexDaysForm(Model model){
        model.addAttribute("days",daysAvailabilityRepository.findAll());
        return "daysAvailability/index";
    }

    @GetMapping("delete/{dayId}")
    public String displayDeleteDayForm(Model model, @PathVariable int dayId){
        Optional<DaysAvailability> optionalDaysAvailability=daysAvailabilityRepository.findById(dayId);
        if(optionalDaysAvailability.isPresent()){
            DaysAvailability daysAvailability=(DaysAvailability)optionalDaysAvailability.get();
            model.addAttribute("daysAvailability",daysAvailability);
            return "daysAvailability/delete";
        }else {
            return "daysAvailability/index";
        }
    }

    @PostMapping("delete")
    public String processDeleteDayForm(@RequestParam(required = false) int[] dayId) {

        if (dayId != null) {
            for(int id : dayId) {
                daysAvailabilityRepository.deleteById(id);

            }
        }
        return "redirect:/daysAvailability/index";
    }

    @GetMapping("update/{daysAvailabilityId}")
    public String displayUpdateDayForm(Model model, @PathVariable int daysAvailabilityId){
        Optional<DaysAvailability> optionalDaysAvailability=daysAvailabilityRepository.findById(daysAvailabilityId);
        if(optionalDaysAvailability.isPresent()){
            DaysAvailability daysAvailability=(DaysAvailability)optionalDaysAvailability.get();
            model.addAttribute("daysAvailability",daysAvailability);
            return "daysAvailability/update";
        }else {
            return "daysAvailability/index";
        }
    }

    @PostMapping("update")
    public String processUpdateDaysForm(@RequestParam String days,@RequestParam int daysAvailabilityId){
        Optional<DaysAvailability> optionalDaysAvailability=daysAvailabilityRepository.findById(daysAvailabilityId);
        if(optionalDaysAvailability.isPresent()) {
            DaysAvailability daysAvailability = (DaysAvailability) optionalDaysAvailability.get();
            daysAvailability.setDays(days);
            daysAvailabilityRepository.save(daysAvailability);
            return "redirect:/daysAvailability/index";
        }
        return "redirect:/";

    }
}
