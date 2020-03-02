package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Volunteer extends AbstractEntity{
    @NotBlank(message = "Please mention days of availability")
    private String daysOfAvailability;

    @NotBlank(message = "Please mention volunteer Category")
    private String volunteerCategory ;

    @NotBlank(message = "Please mention time of availability")
    private String timeAvailability;

//    private String areaOfExpertise;

    //getter and setter

    public String getDaysOfAvailability() {
        return daysOfAvailability;
    }

    public void setDaysOfAvailability(String daysOfAvailability) {
        this.daysOfAvailability = daysOfAvailability;
    }

    public String getVolunteerCategory() {
        return volunteerCategory;
    }

    public void setVolunteerCategory(String volunteerCategory) {
        this.volunteerCategory = volunteerCategory;
    }

    public String getTimeAvailability() {
        return timeAvailability;
    }

    public void setTimeAvailability(String timeAvailability) {
        this.timeAvailability = timeAvailability;
    }

//    public String getAreaOfExpertise() {
//        return areaOfExpertise;
//    }
//
//    public void setAreaOfExpertise(String areaOfExpertise) {
//        this.areaOfExpertise = areaOfExpertise;
//    }

    // No arg constructor
    public Volunteer() {
    }
}

