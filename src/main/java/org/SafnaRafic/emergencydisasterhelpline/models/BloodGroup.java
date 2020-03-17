package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Entity
public class BloodGroup extends AbstractEntityAdmin {

    // fields
    @NotNull(message = "BloodGroup is Required")
    private String bloodType;



    //relationship of BloodDonor and BloodGroup class
    @OneToMany(mappedBy = "bloodGroup",cascade = CascadeType.ALL)
    private final List<BloodDonor> bloodDonors=new ArrayList<>();

    //getter and setter
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }



    //getter for bloodDoonors
    public List<BloodDonor> getBloodDonors() {
        return bloodDonors;
    }

    //No arg constructor
    public BloodGroup() {
    }

    //Override toString
    @Override
    public String toString() {
        return bloodType;
    }
}
