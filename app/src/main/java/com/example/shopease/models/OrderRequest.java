/*
 * File: OrderRequest.java
 * Description: This class represents a request to place an order. It contains information about the items in the cart, the shipping address, and the selected payment method.
 * This class is used when submitting an order through the app's order placement functionality.
 * Author: Senula Nanayakkara
 * Date: 2024/10/08
 */

package com.example.shopease.models;

import java.util.List;

/**
 * Represents an order request, including items in the cart, the shipping address, and payment method.
 */
public class OrderRequest {

    // List of CartItem objects representing the items in the order.
    private List<CartItem> items;

    // Address object representing the shipping address.
    private Address shippingAddress;

    // String representing the selected payment method (e.g., CARD, CASH_ON_DELIVERY).
    private String paymentMethod;

    /**
     * Constructs an OrderRequest with the given items, shipping address, and payment method.
     *
     * @param items           The list of CartItem objects for the order.
     * @param shippingAddress The shipping address for the order.
     * @param paymentMethod   The selected payment method for the order.
     */
    public OrderRequest(List<CartItem> items, Address shippingAddress, String paymentMethod) {
        this.items = items;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }

    //Gets the list of items in the order.
    public List<CartItem> getItems() {
        return items;
    }

    //Gets the shipping address for the order.
    public Address getShippingAddress() {
        return shippingAddress;
    }

    //Gets the payment method for the order.
    public String getPaymentMethod() {
        return paymentMethod;
    }
}
