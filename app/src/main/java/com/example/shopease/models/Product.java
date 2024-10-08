/*

File: Product.java
Description: This class represents a product, containing details such as the product name, price, image URL, vendor ID, description, and product ID.
Author: Senula Nanayakkara
Date: 2024/09/29

*/
package com.example.shopease.models;

public class Product {

    // Fields representing the product details
    private String name;        // Name of the product
    private String price;       // Price of the product
    private String imageUrl;    // URL of the product image
    private String vendorId;    // Unique identifier of the vendor associated with the product
    private String description; // Description of the product
    private String productId;   // Unique identifier of the product

    /**
     * Constructor to initialize a Product object with its details.
     *
     * @param productId    The unique identifier for the product.
     * @param name         The name of the product.
     * @param price        The price of the product.
     * @param imageUrl     The URL of the product image.
     * @param vendorId     The vendor's unique identifier.
     * @param description  A brief description of the product.
     */
    public Product(String productId, String name, String price, String imageUrl, String vendorId, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.vendorId = vendorId;
        this.description = description;
    }


    //Get the name of the product.
    public String getName() {
        return name;
    }


    //Get the price of the product.
    public String getPrice() {
        return price;
    }

    //Get the URL of the product's image.
    public String getImageUrl() {
        return imageUrl;
    }

    //Get the vendor ID associated with the product.
    public String getVendorId() {
        return vendorId;
    }

    //Get the description of the product.
    public String getDescription() {
        return description;
    }

    //Get the unique identifier of the product.
    public String getProductId() {
        return productId;
    }

   //Set the name of the product
    public void setName(String name) {
        this.name = name;
    }

    //Set the price of the product.
    public void setPrice(String price) {
        this.price = price;
    }

    //Set the URL of the product's image.
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //Set the vendor ID for the product.
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    //Set the description of the product.
    public void setDescription(String description) {
        this.description = description;
    }

    //Set the unique identifier of the product.
    public void setProductId(String productId) {
        this.productId = productId;
    }
}
