package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Inneed extends AbstractEntity {

    private String disasterType;

    @NotBlank(message = "Please Specify your Needs")
    private String in_need;

    @NotNull(message = "Please Specify the quantity")
    private int quantity;

    // getter and setter


    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getIn_need() {
        return in_need;
    }

    public void setIn_need(String in_need) {
        this.in_need = in_need;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // No arg constructor

    public Inneed() {
    }
}
