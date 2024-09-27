package com.example.shopease.models;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String productName;
    private int quantity;
    private double price;
    private String imageUrl;
    private Vendor vendor; // Vendor must also implement Serializable

    public CartItem(String productName, int quantity, double price, String imageUrl, Vendor vendor) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.imageUrl = imageUrl;
        this.vendor = vendor;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
