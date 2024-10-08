/*
File: VendorProfileResponse.java
Description: This class represents the response model for a vendor's profile data. It includes fields for vendor details such as name, contact information, address, ratings, and profile picture.
Author: Senula Nanayakkara
Date: 2024/10/08
*/

package com.example.shopease.models;

import java.io.Serializable;
import java.util.List;

public class VendorProfileResponse implements Serializable {

    private String vendorId;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String profilePic;
    private double averageRating;
    private int totalReviews;
    private List<String> ratings;

    // Get the vendor ID
    public String getVendorId() {
        return vendorId;
    }

    // Set the vendor ID
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    // Get the username
    public String getUserName() {
        return userName;
    }

    // Set the username
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Get the email address
    public String getEmail() {
        return email;
    }

    // Set the email address
    public void setEmail(String email) {
        this.email = email;
    }

    // Get the vendor's first name
    public String getFirstName() {
        return firstName;
    }

    // Set the vendor's first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Get the vendor's last name
    public String getLastName() {
        return lastName;
    }

    // Set the vendor's last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Get the vendor's address
    public Address getAddress() {
        return address;
    }

    // Set the vendor's address
    public void setAddress(Address address) {
        this.address = address;
    }

    // Get the phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Set the phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Get the profile picture URL
    public String getProfilePic() {
        return profilePic;
    }

    // Set the profile picture URL
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    // Get the average rating of the vendor
    public double getAverageRating() {
        return averageRating;
    }

    // Set the average rating of the vendor
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    // Get the total number of reviews
    public int getTotalReviews() {
        return totalReviews;
    }

    // Set the total number of reviews
    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    // Get the list of ratings
    public List<String> getRatings() {
        return ratings;
    }

    // Set the list of ratings
    public void setRatings(List<String> ratings) {
        this.ratings = ratings;
    }

    // Inner class representing the vendor's address
    public static class Address {
        private String street;
        private String city;
        private String state;
        private String postalCode;
        private String country;

        // Get the street address
        public String getStreet() {
            return street;
        }

        // Set the street address
        public void setStreet(String street) {
            this.street = street;
        }

        // Get the city
        public String getCity() {
            return city;
        }

        // Set the city
        public void setCity(String city) {
            this.city = city;
        }

        // Get the state
        public String getState() {
            return state;
        }

        // Set the state
        public void setState(String state) {
            this.state = state;
        }

        // Get the postal code
        public String getPostalCode() {
            return postalCode;
        }

        // Set the postal code
        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        // Get the country
        public String getCountry() {
            return country;
        }

        // Set the country
        public void setCountry(String country) {
            this.country = country;
        }
    }
}
