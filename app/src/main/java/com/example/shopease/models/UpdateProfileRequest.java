/*
 * File: UpdateProfileRequest.java
 * Description: This class represents the request model for updating a user's profile, including fields for personal details like name, email, phone number, and address.
 * Author: Senula Nanayakkara
 * Date: 2024/10/01
 */

package com.example.shopease.models;

public class UpdateProfileRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;

    // Getters and Setters
    // Get the user's first name
    public String getFirstName() {
        return firstName;
    }

    // Set the user's first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Get the user's last name
    public String getLastName() {
        return lastName;
    }

    // Set the user's last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Get the user's email
    public String getEmail() {
        return email;
    }

    // Set the user's email
    public void setEmail(String email) {
        this.email = email;
    }

    // Get the user's phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Set the user's phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Get the user's address
    public Address getAddress() {
        return address;
    }

    // Set the user's address
    public void setAddress(Address address) {
        this.address = address;
    }
}
