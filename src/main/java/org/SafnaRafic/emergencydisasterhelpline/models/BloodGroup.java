package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Entity
public class BloodGroup {
    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "BloodGroup is Required")
    private String bloodType;
    private String description;

    @OneToMany(mappedBy = "bloodGroup" ,cascade = CascadeType.ALL)

    private final List<BloodDonor> bloodDonors=new ArrayList<>();

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BloodDonor> getBloodDonors() {
        return bloodDonors;
    }

    public int getId() {
        return id;
    }

    public BloodGroup() {
    }

    @Override
    public String toString() {
        return bloodType;
    }
}
