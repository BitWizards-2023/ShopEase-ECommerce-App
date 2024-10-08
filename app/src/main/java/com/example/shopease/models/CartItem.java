/*

File: CartItem.java
Description: This class represents an item in the shopping cart, including product details, quantity, price, and vendor information. It implements Serializable to allow object serialization for data transfer.
Author: Senula Nanayakkara
Date: 2024/10/04

*/
package com.example.shopease.models;

import java.io.Serializable;

public class CartItem implements Serializable {
    // Fields representing the product details in the cart
    private String productName;
    private int quantity;
    private double price;
    private String imageUrl;
    private String productId; // Optional field to store the product ID
    private Vendor vendor; // Vendor associated with the product; must implement Serializable

    /**
     * Constructor to initialize a CartItem with all the details including the product ID.
     *
     * @param productId    The unique identifier of the product.
     * @param productName  The name of the product.
     * @param quantity     The quantity of the product added to the cart.
     * @param price        The price of a single unit of the product.
     * @param imageUrl     The URL of the product's image.
     * @param vendor       The vendor associated with the product.
     */
    public CartItem(String productId, String productName, int quantity, double price, String imageUrl, Vendor vendor) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.imageUrl = imageUrl;
        this.vendor = vendor;
    }

    /**
     * Constructor to initialize a CartItem without the product ID (used when product ID is not necessary).
     *
     * @param productName  The name of the product.
     * @param quantity     The quantity of the product added to the cart.
     * @param price        The price of a single unit of the product.
     * @param imageUrl     The URL of the product's image.
     * @param vendor       The vendor associated with the product.
     */
    public CartItem(String productName, int quantity, double price, String imageUrl, Vendor vendor) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.imageUrl = imageUrl;
        this.vendor = vendor;
    }

    // Getter methods to retrieve the values of the fields
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

    // Setter method to update the quantity of the product
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
