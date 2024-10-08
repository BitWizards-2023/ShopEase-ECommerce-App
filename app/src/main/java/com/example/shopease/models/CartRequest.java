/*
File: CartRequest.java
Description: Model class representing the request body for creating or updating a cart item.This class is used in the API request to add items to the cart.
Author: Senula Nanayakkara
Date: 2024/09/30
*/

package com.example.shopease.models;

import java.util.Map;

public class CartRequest {
    private String productId;
    private int quantity;
    private Map<String, String> selectedOptions;
    private String notes;

    public CartRequest(String productId, int quantity, Map<String, String> selectedOptions, String notes) {
        this.productId = productId;
        this.quantity = quantity;
        this.selectedOptions = selectedOptions;
        this.notes = notes;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter for selected options (additional properties)
    public Map<String, String> getSelectedOptions() {
        return selectedOptions;
    }

    // Setter for selected options
    public void setSelectedOptions(Map<String, String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }
}
