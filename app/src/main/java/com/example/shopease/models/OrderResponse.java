// OrderResponse.java
package com.example.shopease.models;

import java.util.List;

public class OrderResponse {
    private boolean success;
    private String message;
    private List<OrderData> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<OrderData> getData() {
        return data;
    }

    public static class OrderData {
        private String id;
        private String orderNumber;
        private int totalAmount;
        private String status;
        private ShippingAddress shippingAddress;
        private String paymentStatus;
        private String createdAt;
        private List<OrderItem> items;

        // Getters and other nested classes
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

        public static class ShippingAddress {
            private String street;
            private String city;
            private String state;
            private String postalCode;
            private String country;

            // Getters
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

        public static class OrderItem {
            private String productId;
            private String vendorId;
            private int quantity;
            private String status;
            private ProductDetails productDetails;

            // Getters
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

            public static class ProductDetails {
                private String name;
                private String description;
                private int price;
                private List<String> images;

                // Getters
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
