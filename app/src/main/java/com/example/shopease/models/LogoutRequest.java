/*
File: LogoutRequest.java
Description: This class represents the model for handling user logout requests. It includes the user's email to identify which user is requesting to log out.
Author: Senula Nanayakkara
Date: 2024/10/08
*/

package com.example.shopease.models;

public class LogoutRequest {

    // User's email to identify the logout request
    private String email;

    // Constructor to initialize the LogoutRequest with the provided email
    public LogoutRequest(String email) {
        this.email = email;
    }

    // Get the user's email address
    public String getEmail() {
        return email;
    }

    // Set the user's email address
    public void setEmail(String email) {
        this.email = email;
    }
}
