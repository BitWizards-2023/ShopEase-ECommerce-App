package com.example.shopease.models;

import java.util.List;

public class CategoryResponse {
    private boolean success;
    private String message;
    private List<CategoryData> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<CategoryData> getData() {
        return data;
    }

    public static class CategoryData {
        private String id;
        private String name;
        private boolean isActive;
        private String parentId;
        private String imageUrl;

        // Getters and Setters
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
