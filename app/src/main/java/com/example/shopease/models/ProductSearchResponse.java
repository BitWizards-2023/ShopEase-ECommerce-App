/*
 * File: ProductSearchResponse.java
 * Description: This class represents the response model for product search results, containing the success status, message, and a list of product details.
 * Author: Senula Nanayakkara
 * Date: 2024/09/29
 */

package com.example.shopease.models;

import java.util.List;

public class ProductSearchResponse {

    private boolean success;    // Indicates whether the request was successful
    private String message;     // Message returned from the API
    private List<ProductData> data; // List of product data returned from the API

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductData> getData() {
        return data;
    }

    public void setData(List<ProductData> data) {
        this.data = data;
    }

    /**
     * Inner class representing individual product data.
     */
    public static class ProductData {

        private String id;
        private String productCode;
        private String name;
        private String description;
        private int price;
        private List<String> images;
        private String vendorId;
        private int stockLevel;

        // Get the product ID
        public String getId() {
            return id;
        }

        // Set the product ID
        public void setId(String id) {
            this.id = id;
        }

        // Get the product code
        public String getProductCode() {
            return productCode;
        }

        // Set the product code
        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        // Get the product name
        public String getName() {
            return name;
        }

        // Set the product name
        public void setName(String name) {
            this.name = name;
        }

        // Get the product description
        public String getDescription() {
            return description;
        }

        // Set the product description
        public void setDescription(String description) {
            this.description = description;
        }

        // Get the product price
        public int getPrice() {
            return price;
        }

        // Set the product price
        public void setPrice(int price) {
            this.price = price;
        }

        // Get the list of image URLs
        public List<String> getImages() {
            return images;
        }

        // Set the list of image URLs
        public void setImages(List<String> images) {
            this.images = images;
        }

        // Get the vendor ID
        public String getVendorId() {
            return vendorId;
        }

        // Set the vendor ID
        public void setVendorId(String vendorId) {
            this.vendorId = vendorId;
        }

        // Get the current stock level
        public int getStockLevel() {
            return stockLevel;
        }

        // Set the current stock level
        public void setStockLevel(int stockLevel) {
            this.stockLevel = stockLevel;
        }
    }
}
