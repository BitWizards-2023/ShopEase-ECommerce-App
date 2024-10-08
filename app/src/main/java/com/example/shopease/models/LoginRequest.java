/*

File: LoginRequest.java
Description: This class represents the data model for a login request, containing the user's email and password.
Author: Senula Nanayakkara
Date: 2024/09/28

*/

package com.example.shopease.models;

public class LoginRequest {

    private String email;
    private String password;

    // Constructor to initialize a LoginRequest object with the user's email and password.
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Get the user's email address.
    public String getEmail() {
        return email;
    }

    // Get the user's password.
    public String getPassword() {
        return password;
    }
}
