/*

File: Address.java
Description: This class represents an Address model containing fields for street, city, state, postal code, and country. It includes getter and setter methods to access and modify these fields.
Author: Senula Nanayakkara
Date: 2024/09/25

*/
package com.example.shopease.models;

public class Address {

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    /**
     * Constructor to initialize an Address object with all the address fields.
     *
     * @param street      The street name and number of the address.
     * @param city        The city of the address.
     * @param state       The state or province of the address.
     * @param postalCode  The postal or ZIP code of the address.
     * @param country     The country of the address.
     */
    public Address(String street, String city, String state, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    // Getter and setter methods for street
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    // Getter and setter methods for city
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter and setter methods for state
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Getter and setter methods for postal code
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    // Getter and setter methods for country
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
