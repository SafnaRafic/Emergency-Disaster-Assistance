package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class BloodDonor extends AbstractEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bloodGroup")
    private BloodGroup bloodGroup;

    //getter and setter

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }


    // No arg constructor

    public BloodDonor() {
    }

    public BloodDonor(BloodGroup bloodGroup) {
        super();
        this.bloodGroup = bloodGroup;
    }
}
