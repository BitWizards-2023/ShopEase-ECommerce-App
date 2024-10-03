package com.example.shopease.models;

import java.util.List;

public class ProductResponse {

    private boolean success;
    private String message;
    private List<ProductData> data;

    // Getters and Setters
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

        // Getters and Setters for the fields
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
    }
}
