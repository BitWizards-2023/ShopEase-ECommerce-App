package com.example.shopease.models;

public class Product {
    private String name;
    private String price;
    private String imageUrl;
    private String vendorId;

    public Product(String name, String price, String imageUrl, String vendor) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.vendorId = vendorId;
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
}
