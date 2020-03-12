package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Inneed extends AbstractEntity {

    //Relationship of Inneed and Needed class

    @ManyToMany(mappedBy ="inneed",cascade = CascadeType.ALL)
    private List<Needed> needs;

    //Fields

    private String disasterType;

    @NotNull(message = "Please Specify the quantity")
    private int quantity;

    // getter and setter for fields

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //getter for inneed field

    public List<Needed> getNeeds() {
        return needs;
    }

    public void setNeeds(List<Needed> needs) {
        this.needs = needs;
    }
    // No arg constructor

    public Inneed() {
    }
}
