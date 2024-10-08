/*

File: ProductResponse.java
Description: This class represents the response model for product data, containing the success status, message, and a list of product details.
Author: Senula Nanayakkara
Date: 2024/09/29

*/
package com.example.shopease.models;

import java.util.List;

public class ProductResponse {

    private boolean success;
    private String message;
    private List<ProductData> data;

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
        private List<String> categoryIds;
        private String vendorId;
        private List<String> images;
        private int stockLevel;
        private int lowStockThreshold;
        private boolean isFeatured;
        private boolean isActive;

        // Get the product ID
        public String getId() {
            return id; // Get the product ID
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

        // Get the list of category IDs
        public List<String> getCategoryIds() {
            return categoryIds;
        }

        // Set the list of category IDs
        public void setCategoryIds(List<String> categoryIds) {
            this.categoryIds = categoryIds;
        }

        // Get the vendor ID
        public String getVendorId() {
            return vendorId;
        }

        // Set the vendor ID
        public void setVendorId(String vendorId) {
            this.vendorId = vendorId;
        }

        // Get the list of image URLs
        public List<String> getImages() {
            return images;
        }

        // Set the list of image URLs
        public void setImages(List<String> images) {
            this.images = images;
        }

        // Get the current stock level
        public int getStockLevel() {
            return stockLevel;
        }

        // Set the current stock level
        public void setStockLevel(int stockLevel) {
            this.stockLevel = stockLevel;
        }

        // Get the low stock threshold
        public int getLowStockThreshold() {
            return lowStockThreshold;
        }

        // Set the low stock threshold
        public void setLowStockThreshold(int lowStockThreshold) {
            this.lowStockThreshold = lowStockThreshold;
        }

        // Check if the product is featured
        public boolean isFeatured() {
            return isFeatured;
        }

        // Set the product as featured
        public void setFeatured(boolean featured) {
            isFeatured = featured;
        }

        // Check if the product is active
        public boolean isActive() {
            return isActive;
        }

        // Set the product as active
        public void setActive(boolean active) {
            isActive = active;
        }
    }
}
