package com.example.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public
class User {

    private @Id
    @GeneratedValue
    Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    public User() {
        firstName = "";
        lastName = "";
        email = "";
        phoneNumber = "";
        address = "";
    }

    public User(String firstName, String lastName, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.firstName, user.firstName)
                && Objects.equals(this.lastName, user.lastName) && Objects.equals(this.email, user.email)
                && Objects.equals(this.phoneNumber, user.phoneNumber) && Objects.equals(this.address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.email, this.phoneNumber, this.address);
    }

    @Override
    public String toString() {
        return "id: "+ id +
                " || firstName: " + firstName +
                " || lastName: " + lastName +
                " || email: " + email +
                " || phoneNumber: " + phoneNumber +
                " || address: " + address;
    }
}
