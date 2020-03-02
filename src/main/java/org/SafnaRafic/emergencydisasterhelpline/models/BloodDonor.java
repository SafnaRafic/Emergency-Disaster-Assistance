package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class BloodDonor extends AbstractEntity {
    @NotBlank(message = "Blood group os required")
    private String bloodGroup;

    //getter and setter
    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    // No arg constructor

    public BloodDonor() {
    }
}
