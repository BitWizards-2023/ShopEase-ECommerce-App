package com.example.shopease.models;

import java.util.List;

public class OrderRequest {

    private List<CartItem> items;
    private Address shippingAddress;
    private String paymentMethod;

    public OrderRequest(List<CartItem> items, Address shippingAddress, String paymentMethod) {
        this.items = items;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
