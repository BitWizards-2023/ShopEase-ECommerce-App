/*
 * File: UpdateProfileResponse.java
 * Description: This class represents the response model for the update profile request, containing a message and a success status.
 * Author: Senula Nanayakkara
 * Date: 2024/10/01
 */

package com.example.shopease.models;

public class UpdateProfileResponse {

    private String message;
    private boolean success;

    // Getters and Setters
    // Get the response message
    public String getMessage() {
        return message;
    }

    // Set the response message
    public void setMessage(String message) {
        this.message = message;
    }

    // Check if the update was successful
    public boolean isSuccess() {
        return success;
    }

    // Set the success status
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
