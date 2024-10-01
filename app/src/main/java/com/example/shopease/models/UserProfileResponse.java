package com.example.shopease.models;

public class UserProfileResponse {
    private boolean success;
    private String message;
    private Data data;

    public class Data {
        private String id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private Address address;
        private String profile_pic;

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public Address getAddress() {
            return address;
        }

        public String getProfilePic() {
            return profile_pic;
        }
    }

    public class Address {
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

    public Data getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
