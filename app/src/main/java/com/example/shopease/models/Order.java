package com.example.shopease.models;

import java.io.Serializable;

public class Order implements Serializable {
    private String orderId;
    private String productDetails;
    private boolean isPaid;
    private String orderStatus;

    public Order(String orderId, String productDetails, boolean isPaid, String orderStatus) {
        this.orderId = orderId;
        this.productDetails = productDetails;
        this.isPaid = isPaid;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
