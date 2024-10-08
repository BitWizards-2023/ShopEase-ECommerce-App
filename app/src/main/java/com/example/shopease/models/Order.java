/*

File: Order.java
Description: This class represents an order, containing details such as the order ID, tracking ID, product, status, and order date.
Author: Senula Nanayakkara
Date: 2024/10/05

*/

package com.example.shopease.models;

import java.io.Serializable;

public class Order {

    private String orderId;
    private String trackingId;
    private Product product;
    private String status;
    private String date;

    // Constructor to initialize an Order object with all its details.
    public Order(String orderId, String trackingId, Product product, String status, String date) {
        this.orderId = orderId;
        this.trackingId = trackingId;
        this.product = product;
        this.status = status;
        this.date = date;
    }

    // Get the unique identifier for the order.
    public String getOrderId() {
        return orderId;
    }

    // Get the tracking ID for the order.
    public String getTrackingId() {
        return trackingId;
    }

    // Get the product associated with the order.
    public Product getProduct() {
        return product;
    }

    // Get the current status of the order.
    public String getStatus() {
        return status;
    }

    // Get the date when the order was placed.
    public String getDate() {
        return date;
    }
}
