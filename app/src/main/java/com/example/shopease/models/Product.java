package com.example.shopease.models;

public class Product {
    private String name;
    private String price;
    private String imageUrl;
    private Vendor vendor;

    public Product(String name, String price, String imageUrl, Vendor vendor) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.vendor = vendor;
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

    public Vendor getVendor() {
        return vendor;
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

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
