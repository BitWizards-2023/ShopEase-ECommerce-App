/*

File: LoginResponse.java
Description: This class represents the response model for a login operation, including the success status, message, and data (such as tokens).
Author: Senula Nanayakkara
Date: 2024/09/28

*/
package com.example.shopease.models;

public class LoginResponse {

    // Fields representing the response status and message
    private boolean success;
    private String message;
    private Data data;

    // Inner class representing the data section of the login response, which includes tokens.
    public static class Data {
        private String token;
        private String refreshToken;

        // Get the JWT token.
        public String getToken() {
            return token;
        }

        // Set the JWT token.
        public void setToken(String token) {
            this.token = token;
        }

        // Get the refresh token.
        public String getRefreshToken() {
            return refreshToken;
        }

        // Set the refresh token.
        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }

    // Check if the login operation was successful.
    public boolean isSuccess() {
        return success;
    }

    // Set the success status of the login operation.
    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Get the message returned by the API.
    public String getMessage() {
        return message;
    }

    // Set the message returned by the API.
    public void setMessage(String message) {
        this.message = message;
    }

    // Get the data (token and refresh token) from the login response.
    public Data getData() {
        return data;
    }

    // Set the data (token and refresh token) for the login response.
    public void setData(Data data) {
        this.data = data;
    }
}
