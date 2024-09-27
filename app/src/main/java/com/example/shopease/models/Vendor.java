package com.example.shopease.models;

import java.io.Serializable;

public class Vendor implements Serializable {
    private String name;
    private String details;
    private String openingHours;

    public Vendor(String name, String details, String openingHours) {
        this.name = name;
        this.details = details;
        this.openingHours = openingHours;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getOpeningHours() {
        return openingHours;
    }
}
