/*
 * File: UpdateCartRequest.java
 * Description: Model class for sending updates to a cart item (e.g., updating quantity).
 * Author: Senula Nanayakkara
 * Date: 2024/10/01
 */

package com.example.shopease.models;

import java.util.Map;

public class UpdateCartRequest {
    private int quantity;
    private Map<String, String> selectedOptions;

    // Constructor
    public UpdateCartRequest(int quantity, Map<String, String> selectedOptions) {
        this.quantity = quantity;
        this.selectedOptions = selectedOptions;
    }

    // Getters and Setters
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Map<String, String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(Map<String, String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }
}
