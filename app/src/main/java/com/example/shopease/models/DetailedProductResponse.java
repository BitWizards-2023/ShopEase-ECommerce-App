/*
File: DetailedProductResponse.java
Description: This class represents the detailed response model for product data, including attributes, ratings, and timestamps.
Author: Senula Nanayakkara
Date: 2024/10/08
*/

package com.example.shopease.models;

import java.util.List;

public class DetailedProductResponse {

    private boolean success;
    private String message;
    private ProductData data;

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

    public ProductData getData() {
        return data;
    }

    public void setData(ProductData data) {
        this.data = data;
    }

    /**
     * Inner class representing detailed product data.
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
        private List<Attribute> attributes;
        private int stockLevel;
        private int lowStockThreshold;
        private boolean isFeatured;
        private boolean isActive;
        private String createdAt;
        private String updatedAt;
        private int averageRating;
        private int ratingsCount;

        // Getters and setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public List<String> getCategoryIds() {
            return categoryIds;
        }

        public void setCategoryIds(List<String> categoryIds) {
            this.categoryIds = categoryIds;
        }

        public String getVendorId() {
            return vendorId;
        }

        public void setVendorId(String vendorId) {
            this.vendorId = vendorId;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<Attribute> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<Attribute> attributes) {
            this.attributes = attributes;
        }

        public int getStockLevel() {
            return stockLevel;
        }

        public void setStockLevel(int stockLevel) {
            this.stockLevel = stockLevel;
        }

        public int getLowStockThreshold() {
            return lowStockThreshold;
        }

        public void setLowStockThreshold(int lowStockThreshold) {
            this.lowStockThreshold = lowStockThreshold;
        }

        public boolean isFeatured() {
            return isFeatured;
        }

        public void setFeatured(boolean featured) {
            isFeatured = featured;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(int averageRating) {
            this.averageRating = averageRating;
        }

        public int getRatingsCount() {
            return ratingsCount;
        }

        public void setRatingsCount(int ratingsCount) {
            this.ratingsCount = ratingsCount;
        }

        // Inner class for attributes
        public static class Attribute {
            private String name;
            private String value;

            // Getters and setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
