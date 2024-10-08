/*

File: CategoryRequest.java
Description: This class represents a request to create or update a product category. It includes the category's name, parent ID, active status, and image URL.
Author: Senula Nanayakkara
Date: 2024/09/28

*/
package com.example.shopease.models;

public class CategoryRequest {

    // Fields representing the category request details
    private String name;
    private String parentId;
    private boolean isActive;
    private String imageUrl;

    /**
     * Constructor to initialize a CategoryRequest object with the given parameters.
     *
     * @param name      The name of the category.
     * @param parentId  The ID of the parent category.
     * @param isActive  The active status of the category.
     * @param imageUrl  The URL of the image associated with the category.
     */
    public CategoryRequest(String name, String parentId, boolean isActive, String imageUrl) {
        this.name = name;
        this.parentId = parentId;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
    }

    // Getter and setter methods for each field

    // Get the name of the category
    public String getName() {
        return name;
    }

    // Set the name of the category
    public void setName(String name) {
        this.name = name;
    }

    // Get the parent category ID
    public String getParentId() {
        return parentId;
    }

    // Set the parent category ID
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    // Check if the category is active
    public boolean isActive() {
        return isActive;
    }

    // Set the active status of the category
    public void setActive(boolean active) {
        isActive = active;
    }

    // Get the image URL of the category
    public String getImageUrl() {
        return imageUrl;
    }

    // Set the image URL of the category
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
