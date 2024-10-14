/*
File: ProductDetailsActivity.java
Description: This activity displays the product details, handles cart actions, and allows users to rate the product through a dialog with a star rating system. It also handles the creation of the cart item using the CartRequest model and the PATCH API call.
Author: Senula Nanayakkara
Date: 2024/09/30
*/

package com.example.shopease.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.shopease.R;
import com.example.shopease.models.CartItem;
import com.example.shopease.models.CartRequest;
import com.example.shopease.models.Vendor;
import com.example.shopease.models.VendorProfileResponse;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductDetailsActivity extends AppCompatActivity {

    // UI elements
    private ImageView productImage, ratingStar;
    private TextView productName, productPrice, productDescription, vendorName, vendorDetails, vendorOpeningHours;
    private Button buyNowButton, addToCartButton;

    // API service for network requests
    private ApiService apiService;

    // Cart item list
    private ArrayList<CartItem> cartItemList = new ArrayList<>();

    // Star rating variables
    private int currentRating = 0; // Stores the user's current rating

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Initialize UI elements
        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);
        vendorName = findViewById(R.id.vendor_name);
        vendorDetails = findViewById(R.id.vendor_details);
        vendorOpeningHours = findViewById(R.id.vendor_opening_hours);
        ratingStar = findViewById(R.id.rating_star);
        buyNowButton = findViewById(R.id.btn_buy_now);
        addToCartButton = findViewById(R.id.btn_add_to_cart);

        // Initialize the API service
        apiService = RetrofitClient.getInstance().create(ApiService.class);

        // Retrieve product details passed from the previous activity
        String name = getIntent().getStringExtra("productName");
        String price = getIntent().getStringExtra("productPrice");
        String imageUrl = getIntent().getStringExtra("productImage");
        String description = getIntent().getStringExtra("productDescription");
        String productId = getIntent().getStringExtra("productId");
        String vendorId = getIntent().getStringExtra("vendorId");

        // Retrieve vendor details passed from the previous activity
        Vendor vendor = (Vendor) getIntent().getSerializableExtra("vendor");

        // Set product details in the UI
        productName.setText(name);
        productPrice.setText(price);
        productDescription.setText(description);
        Glide.with(this).load(imageUrl).into(productImage);  // Load the product image using Glide

        // Fetch and display the vendor details using the vendorId
        fetchVendorDetails(vendorId);

        // Set vendor details in the UI
        if (vendor != null) {
            vendorName.setText(vendor.getName());
            vendorDetails.setText(vendor.getDetails());
            vendorOpeningHours.setText("Opening Hours: " + vendor.getOpeningHours());
        }

        // Handle "Buy Now" button click to navigate to the OrderActivity
        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new CartItem object based on the current product details
                CartItem newItem = new CartItem(productId, name, 1, Double.parseDouble(productPrice.getText().toString()), imageUrl, vendorId);
                // Add the new CartItem to the cartItemList
                cartItemList.add(newItem);

                // Log the CartItem to verify it has been added
                Log.d("ProductDetailsActivity", "Added to Cart: " + newItem.getProductName() + ", Quantity: " + newItem.getQuantity());

                // Now navigate to OrderActivity
                navigateToOrderActivity();
            }
        });


        // Handle "Add to Cart" button click to add the item to the cart
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(productId);
            }
        });

        // Handle "Rate" button click to show rating dialog
        ratingStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog(vendorId);
            }
        });
    }

    /**
     * Navigates to the OrderActivity and passes the list of cart items.
     */
    private void navigateToOrderActivity() {
        if (cartItemList != null && !cartItemList.isEmpty()) {
            for (CartItem item : cartItemList) {
                Log.d("ProductDetailsActivity", "Item: " + item.getProductName() + ", Quantity: " + item.getQuantity());
            }
        } else {
            Log.e("ProductDetailsActivity", "Cart is empty!");
        }

        Intent intent = new Intent(ProductDetailsActivity.this, OrderActivity.class);
        intent.putExtra("cartItems", cartItemList); // Make sure to pass the cartItems as Serializable
        startActivity(intent);
    }



    /**
     * Fetches vendor details from the backend and displays them in the UI.
     *
     * @param vendorId The ID of the vendor to fetch details for.
     */
    private void fetchVendorDetails(String vendorId) {
        // Retrieve the JWT token from SharedPreferences for authorization
        String token = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .getString("jwt_token", null);

        if (token != null) {
            String authHeader = "Bearer " + token; // Prepare token for Authorization header

            // Call the API to fetch vendor details
            Call<VendorProfileResponse> call = apiService.getVendorProfile(vendorId, authHeader);
            call.enqueue(new Callback<VendorProfileResponse>() {
                @Override
                public void onResponse(Call<VendorProfileResponse> call, Response<VendorProfileResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Get the vendor data from the response and update UI
                        VendorProfileResponse vendorProfile = response.body();

                        // Set vendor details in the UI
                        vendorName.setText(vendorProfile.getUserName());
                        vendorDetails.setText(vendorProfile.getAddress().getStreet() + ", " + vendorProfile.getAddress().getCity() + ", " + vendorProfile.getAddress().getState() + ", " + vendorProfile.getAddress().getCountry());


                    } else {
                        // Handle failure in fetching vendor details
                        Toast.makeText(ProductDetailsActivity.this, "Failed to load vendor details", Toast.LENGTH_SHORT).show();
                        Log.e("VendorProfile", "Error fetching vendor details: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<VendorProfileResponse> call, Throwable t) {
                    // Handle failure in making the API call
                    Log.e("VendorProfile", "Error fetching vendor details: " + t.getMessage());
                    Toast.makeText(ProductDetailsActivity.this, "Error loading vendor details", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle case when user is not authenticated
            Toast.makeText(ProductDetailsActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Adds the current product to the cart by making an API call to update the cart.
     *
     * @param productId The ID of the product to be added to the cart.
     */
    private void addToCart(String productId) {
        // Create selectedOptions map with empty strings as per Swagger example
        Map<String, String> selectedOptions = new HashMap<>();
        selectedOptions.put("additionalProp1", "");
        selectedOptions.put("additionalProp2", "");
        selectedOptions.put("additionalProp3", "");

        // Create a CartRequest object with the necessary fields for the PUT request
        CartRequest cartRequest = new CartRequest(productId, 1, selectedOptions, ""); // Notes field left empty for now

        // Retrieve JWT token from SharedPreferences
        String token = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .getString("jwt_token", null);

        if (token != null) {
            String authHeader = "Bearer " + token; // Prepare token for Authorization header

            // Make the PUT request to update the cart item with the token
            Call<Void> call = apiService.updateCartItem(cartRequest, authHeader);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Successfully added to cart
                        Toast.makeText(ProductDetailsActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle error
                        Toast.makeText(ProductDetailsActivity.this, "Failed to add item to cart", Toast.LENGTH_SHORT).show();
                        Log.e("Cart", "Error: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle failure
                    Toast.makeText(ProductDetailsActivity.this, "Error adding item to cart", Toast.LENGTH_SHORT).show();
                    Log.e("Cart", "Failure: " + t.getMessage());
                }
            });
        } else {
            // Handle missing token case
            Toast.makeText(ProductDetailsActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Show the rating dialog where users can give a rating and submit a comment.
     */
    private void showRatingDialog(String vendorId) {
        // Create and show the rating dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.rating_dialog, null);
        builder.setView(dialogView);

        // Initialize rating dialog components
        ImageView star1 = dialogView.findViewById(R.id.star_1);
        ImageView star2 = dialogView.findViewById(R.id.star_2);
        ImageView star3 = dialogView.findViewById(R.id.star_3);
        ImageView star4 = dialogView.findViewById(R.id.star_4);
        ImageView star5 = dialogView.findViewById(R.id.star_5);
        EditText commentBox = dialogView.findViewById(R.id.comment_box);
        Button submitButton = dialogView.findViewById(R.id.submit_rating_button);

        // Set tags to star ImageViews to represent their rating value
        star1.setTag(1);
        star2.setTag(2);
        star3.setTag(3);
        star4.setTag(4);
        star5.setTag(5);

        // Handle star rating clicks
        View.OnClickListener starClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRating = (int) v.getTag(); // Get star number from tag
                updateStarIcons(star1, star2, star3, star4, star5, currentRating); // Update stars based on rating
            }
        };

        star1.setOnClickListener(starClickListener);
        star2.setOnClickListener(starClickListener);
        star3.setOnClickListener(starClickListener);
        star4.setOnClickListener(starClickListener);
        star5.setOnClickListener(starClickListener);

        // Create the dialog
        AlertDialog dialog = builder.create();

        // Submit button click event
        submitButton.setOnClickListener(v -> {
            String comment = commentBox.getText().toString().trim();
            if (currentRating == 0) {
                Toast.makeText(ProductDetailsActivity.this, "Please select a rating", Toast.LENGTH_SHORT).show();
                return;
            }
            submitVendorRating(vendorId, currentRating, comment);
            dialog.dismiss(); // Close dialog after submission
        });

        // Show the dialog
        dialog.show();
    }

    //Submit vendor rating
    private void submitVendorRating(String vendorId, int rating, String comment) {
        // Retrieve JWT token from SharedPreferences
        String token = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .getString("jwt_token", null);

        if (token != null) {
            String authHeader = "Bearer " + token;  // Prepare token for Authorization header

            // Create the request body
            Map<String, Object> ratingRequest = new HashMap<>();
            ratingRequest.put("rating", rating);
            ratingRequest.put("comment", comment);

            // Call the API to submit the rating
            Call<Void> call = apiService.submitVendorRating(vendorId, authHeader, ratingRequest);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ProductDetailsActivity.this, "Rating submitted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProductDetailsActivity.this, "Failed to submit rating", Toast.LENGTH_SHORT).show();
                        Log.e("VendorRating", "Error: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle network failure
                    Toast.makeText(ProductDetailsActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("VendorRating", "Error: " + t.getMessage());
                }
            });
        } else {
            // Handle missing token case
            Toast.makeText(ProductDetailsActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Updates the star icons based on the current rating value.
     * It fills in the stars up to the selected rating and leaves the others empty.
     *
     * @param star1      The first star ImageView.
     * @param star2      The second star ImageView.
     * @param star3      The third star ImageView.
     * @param star4      The fourth star ImageView.
     * @param star5      The fifth star ImageView.
     * @param rating     The current rating value (1 to 5).
     */
    private void updateStarIcons(ImageView star1, ImageView star2, ImageView star3, ImageView star4, ImageView star5, int rating) {
        // Reset all stars to default (empty)
        star1.setImageResource(R.drawable.ic_star_empty);
        star2.setImageResource(R.drawable.ic_star_empty);
        star3.setImageResource(R.drawable.ic_star_empty);
        star4.setImageResource(R.drawable.ic_star_empty);
        star5.setImageResource(R.drawable.ic_star_empty);

        // Fill stars based on the rating
        if (rating >= 1) star1.setImageResource(R.drawable.ic_star_filled);
        if (rating >= 2) star2.setImageResource(R.drawable.ic_star_filled);
        if (rating >= 3) star3.setImageResource(R.drawable.ic_star_filled);
        if (rating >= 4) star4.setImageResource(R.drawable.ic_star_filled);
        if (rating >= 5) star5.setImageResource(R.drawable.ic_star_filled);
    }

}
