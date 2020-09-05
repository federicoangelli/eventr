package com.example.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public
class Organizer {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Organizer() {
        name = "";
        email = "";
        phoneNumber = "";
        address = "";
    }

    public Organizer(String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Organizer))
            return false;
        Organizer organizer = (Organizer) o;
        return Objects.equals(this.id, organizer.id) && Objects.equals(this.name, organizer.name)
                && Objects.equals(this.email, organizer.email) && Objects.equals(this.phoneNumber, organizer.phoneNumber)
                && Objects.equals(this.address, organizer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email, this.phoneNumber, this.address);
    }

    @Override
    public String toString() {
        return "organizer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}