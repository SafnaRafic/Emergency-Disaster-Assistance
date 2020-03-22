package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
@Entity
public class EmergencyTips extends AbstractEntityAdmin{
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    //NO arg constructor
    public EmergencyTips() {
    }

    //getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //toString


    @Override
    public String toString() {
        return "Emergencytips{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
