/*
 * File: RegisterRequest.java
 * Description: This class represents the request model for registering a new user, including user details like email, username, password, and address.
 * Author: Senula Nanayakkara
 * Date: 2024/09/29
 */

package com.example.shopease.models;

public class RegisterRequest {

    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String role = "Customer";
    private Address address;
    private String phoneNumber;
    private String profile_pic;

    // Constructor to initialize all fields
    public RegisterRequest(String email, String password, String username, String firstName, String lastName, String role, Address address, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Get the user's email
    public String getEmail() {
        return email;
    }

    // Set the user's email
    public void setEmail(String email) {
        this.email = email;
    }

    // Get the user's username
    public String getUsername() {
        return username;
    }

    // Set the user's username
    public void setUsername(String username) {
        this.username = username;
    }

    // Get the user's password
    public String getPassword() {
        return password;
    }

    // Set the user's password
    public void setPassword(String password) {
        this.password = password;
    }

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

    // Get the user's role
    public String getRole() {
        return role;
    }

    // Set the user's role
    public void setRole(String role) {
        this.role = role;
    }

    // Get the user's address
    public Address getAddress() {
        return address;
    }

    // Set the user's address
    public void setAddress(Address address) {
        this.address = address;
    }

    // Get the user's phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Set the user's phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Get the user's profile picture URL
    public String getProfile_pic() {
        return profile_pic;
    }

    // Set the user's profile picture URL
    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
