package org.SafnaRafic.emergencydisasterhelpline.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name is required")
    @Size(max=50)
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is Required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotNull(message = "Zipcode is required")
    @Size(min = 5,max = 5, message = "The US zip code must contain 5 digits")
    private String zipcode;

    @NotNull(message = "Please supply a phone number so we can call if there are any problems using this address")
    @Size(min = 10,max = 10,message = "This entry can only contain numbers")
    private String contactNo;

    @Email(message = "Invalid email. Try again.")
    private String emailId;

    //Getter and Setter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    //Override toString

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", contactNo=" + contactNo +
                ", emailId='" + emailId + '\'' ;
    }

    //Override equal and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return getId() == that.getId() &&
                getZipcode() == that.getZipcode() &&
                getContactNo() == that.getContactNo() &&
                getName().equals(that.getName()) &&
                getAddress().equals(that.getAddress()) &&
                getCity().equals(that.getCity()) &&
                getState().equals(that.getState()) &&
                getEmailId().equals(that.getEmailId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getCity(), getState(), getZipcode(), getContactNo(), getEmailId());
    }
}
