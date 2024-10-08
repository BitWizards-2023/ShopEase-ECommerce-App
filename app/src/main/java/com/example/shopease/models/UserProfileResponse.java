/*
 * File: UserProfileResponse.java
 * Description: This class represents the response model for fetching the user's profile, containing success status, a message, and user data.
 * Author: Senula Nanayakkara
 * Date: 2024/09/29
 */

package com.example.shopease.models;

public class UserProfileResponse {

    private boolean success;  // Status indicating if the request was successful
    private String message;   // Response message
    private Data data;        // User profile data

    /**
     * Inner class representing user data in the profile response.
     */
    public class Data {
        private String id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private Address address;
        private String profile_pic;

        // Getters
        // Get the username
        public String getUsername() {
            return username;
        }

        // Get the email
        public String getEmail() {
            return email;
        }

        // Get the user ID
        public String getId() {
            return id;
        }

        // Set the user ID
        public void setId(String id) {
            this.id = id;
        }

        // Get the first name
        public String getFirstName() {
            return firstName;
        }

        // Get the last name
        public String getLastName() {
            return lastName;
        }

        // Get the phone number
        public String getPhoneNumber() {
            return phoneNumber;
        }

        // Get the address
        public Address getAddress() {
            return address;
        }

        // Get the profile picture URL
        public String getProfilePic() {
            return profile_pic;
        }
    }

    /**
     * Inner class representing the address details in the user profile.
     */
    public class Address {
        private String street;
        private String city;
        private String state;
        private String postalCode;
        private String country;

        // Getters
        // Get the street address
        public String getStreet() {
            return street;
        }

        // Get the city
        public String getCity() {
            return city;
        }

        // Get the state
        public String getState() {
            return state;
        }

        // Get the postal code
        public String getPostalCode() {
            return postalCode;
        }

        // Get the country
        public String getCountry() {
            return country;
        }
    }

    // Get the profile data
    public Data getData() {
        return data;
    }

    // Check if the request was successful
    public boolean isSuccess() {
        return success;
    }

    // Get the response message
    public String getMessage() {
        return message;
    }
}
