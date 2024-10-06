package com.example.shopease.models;

import java.util.List;

public class Product {
    private String name;
    private String price;
    private String imageUrl;
    private String vendorId;
    private String description;


    public Product(String name, String price, String imageUrl, String vendor, String description) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.vendorId = vendorId;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVendorId() {
        return vendorId;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setVendor(String vendorId) {
        this.vendorId = vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
