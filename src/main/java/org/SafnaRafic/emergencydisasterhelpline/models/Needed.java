package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Needed extends AbstractEntityAdmin{

    //fields

    @NotBlank(message = "Specify your Need")
    private String need;

    //relationship of Needed and Inneed class
    @ManyToMany(mappedBy ="needs",cascade = CascadeType.ALL)
    private List<Inneed> inneed=new ArrayList<>();


    // No arg constructor
    public Needed() {
    }

    //getter and setter
    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }
    //getter for inneed

    public List<Inneed> getInneed() {
        return inneed;
    }

    public void setInneed(List<Inneed> inneed) {
        this.inneed = inneed;
    }


//override toString

    @Override
    public String toString() {
        return need;
    }
}
