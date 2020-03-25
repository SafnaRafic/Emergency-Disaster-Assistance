package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Volunteer extends AbstractEntity{

    @ManyToMany
    private List<DaysAvailability> daysAvailability;

    @NotBlank(message = "Please mention volunteer Category")
    private String volunteerCategory ;

    @NotBlank(message = "Please mention time of availability")
    private String timeAvailability;

    //getter and setter

    public List<DaysAvailability> getDaysAvailability() {
        return daysAvailability;
    }

    public void setDaysAvailability(List<DaysAvailability> daysAvailability) {
        this.daysAvailability = daysAvailability;
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

    // No arg constructor
    public Volunteer() {
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "daysAvailability=" + daysAvailability +
                ", volunteerCategory='" + volunteerCategory + '\'' +
                ", timeAvailability='" + timeAvailability + '\'' +
                '}';
    }
}

