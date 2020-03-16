package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DaysAvailability extends AbstractEntityAdmin {
    @NotBlank(message = "Days of Availability is required")
    private String days;

    @ManyToMany(mappedBy = "daysAvailability", cascade = CascadeType.ALL)
    private List<Volunteer> volunteer=new ArrayList<>();

    //No arg constructor
    public DaysAvailability() {
    }

    //getter and setter

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public List<Volunteer> getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(List<Volunteer> volunteer) {
        this.volunteer = volunteer;
    }

    @Override
    public String toString() {
        return "DaysAvailability{" +
                "days='" + days + '\'' +
                ", volunteer=" + volunteer +
                '}';
    }
}


