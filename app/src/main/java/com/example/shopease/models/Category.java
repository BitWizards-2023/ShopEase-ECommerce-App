/*

File: Category.java
Description: This class represents a product category, including the category name, image URL, and a unique identifier (ID).
Author: Senula Nanayakkara
Date: 2024/09/28

*/
package com.example.shopease.models;

public class Category {

    // Fields representing the category details
    private String name;
    private String imageUrl;
    private String id;

    /**
     * Constructor to initialize a Category object with the given ID, name, and image URL.
     *
     * @param id        The unique identifier of the category.
     * @param name      The name of the category.
     * @param imageUrl  The URL of the image representing the category.
     */
    public Category(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    // Getter methods to retrieve the values of the fields
    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getId() {
        return id;
    }

    // Setter method to update the category ID
    public void setId(String id) {
        this.id = id;
    }
}
