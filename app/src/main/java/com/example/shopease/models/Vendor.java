package com.example.shopease.models;

import java.io.Serializable;

/**
 * File: Vendor.java
 * Description: This class represents a Vendor entity, holding the vendor's details,
 * such as name, contact information, and business hours. It implements Serializable
 * to allow vendor information to be passed between activities via intents in the Android app.
 * Author: Senula Nanayakkara
 * Date: 2024/09/30
 */

public class Vendor implements Serializable {
    private String name;          // Name of the vendor
    private String details;       // Details of the vendor (e.g., address or description)
    private String openingHours;  // Vendor's business operating hours

    //Constructor to initialize Vendor object with name, details, and opening hours.
    public Vendor(String name, String details, String openingHours) {
        this.name = name;
        this.details = details;
        this.openingHours = openingHours;
    }

    //Gets the name of the vendor
    public String getName() {
        return name;
    }

    //Gets the details of the vendor.
    public String getDetails() {
        return details;
    }


    //Gets the vendor's business hours.
    public String getOpeningHours() {
        return openingHours;
    }
}
