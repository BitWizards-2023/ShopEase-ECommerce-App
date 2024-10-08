/*

File: CategoryResponse.java
Description: This class represents the response from an API call to retrieve category information. It contains a success flag, a message, and a list of category data.
Author: Senula Nanayakkara
Date: 2024/09/28

*/

package com.example.shopease.models;

import java.util.List;

public class CategoryResponse {

    // Fields representing the response from the API
    private boolean success;        // Indicates if the API call was successful
    private String message;         // Message returned from the API (e.g., error message or success message)
    private List<CategoryData> data; // List of category data returned from the API

    // Getter methods to access the fields
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<CategoryData> getData() {
        return data;
    }

    /**
     * Inner static class representing the data for each category.
     */
    public static class CategoryData {

        // Fields representing the category data
        private String id;
        private String name;
        private boolean isActive;
        private String parentId;
        private String imageUrl;

        // Getter methods for the category data fields
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public boolean isActive() {
            return isActive;
        }

        public String getParentId() {
            return parentId;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
