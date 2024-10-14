/*
 * File: OrderResponse.java
 * Description: This class represents the response from the backend API for retrieving order details. It includes details about the order status, shipping address, items in the order, and more.
 * The response contains nested classes to represent the structure of the order, including product details and shipping information.
 *
 * Author: Senula Nanayakkara
 * Date: 2024/10/08
 */

package com.example.shopease.models;

import java.util.List;

/**
 * Represents the response of an order query, including the success status, message, and order data.
 */
public class OrderResponse {
    // Boolean flag indicating the success status of the order operation.
    private boolean success;

    // Message provided by the backend, such as success or error messages.
    private String message;

    // List of OrderData objects representing the order details.
    private List<OrderData> data;

    // Getters for retrieving the success flag, message, and order data.
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<OrderData> getData() {
        return data;
    }

    /**
     * Represents the data associated with an individual order, including order number, status, and items.
     */
    public static class OrderData {
        private String id;
        private String orderNumber;
        private int totalAmount;
        private String status;
        private ShippingAddress shippingAddress;
        private String paymentStatus;
        private String createdAt;
        private List<OrderItem> items;

        // Getters for retrieving order details such as ID, order number, and items.
        public String getId() {
            return id;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public int getTotalAmount() {
            return totalAmount;
        }

        public String getStatus() {
            return status;
        }

        public ShippingAddress getShippingAddress() {
            return shippingAddress;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public List<OrderItem> getItems() {
            return items;
        }

        /**
         * Represents the shipping address for an order.
         */
        public static class ShippingAddress {
            private String street;
            private String city;
            private String state;
            private String postalCode;
            private String country;

            // Getters for retrieving shipping address details.
            public String getStreet() {
                return street;
            }

            public String getCity() {
                return city;
            }

            public String getState() {
                return state;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public String getCountry() {
                return country;
            }
        }

        /**
         * Represents an individual item in the order, including its product details, quantity, and vendor information.
         */
        public static class OrderItem {
            private String productId;
            private String vendorId;
            private int quantity;
            private String status;
            private ProductDetails productDetails;

            // Getters for retrieving details about the order item such as product ID, vendor ID, and quantity.
            public String getProductId() {
                return productId;
            }

            public String getVendorId() {
                return vendorId;
            }

            public int getQuantity() {
                return quantity;
            }

            public String getStatus() {
                return status;
            }

            public ProductDetails getProductDetails() {
                return productDetails;
            }

            /**
             * Represents the details of the product in an order item, including name, description, price, and images.
             */
            public static class ProductDetails {
                private String name;
                private String description;
                private int price;
                private List<String> images;

                // Getters for retrieving product details such as name, description, and price.
                public String getName() {
                    return name;
                }

                public String getDescription() {
                    return description;
                }

                public int getPrice() {
                    return price;
                }

                public List<String> getImages() {
                    return images;
                }
            }
        }
    }
}
