package com.example.shopease.models;

public class CategoryRequest {
    private String name;
    private String parentId;
    private boolean isActive;
    private String imageUrl;

    public CategoryRequest(String name, String parentId, boolean isActive, String imageUrl) {
        this.name = name;
        this.parentId = parentId;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
