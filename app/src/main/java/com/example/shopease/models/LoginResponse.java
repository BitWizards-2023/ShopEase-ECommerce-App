// LoginResponse.java (Model)
package com.example.shopease.models;

public class LoginResponse {
    private String token;
    private String message; // In case of an error, a message may be returned

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
