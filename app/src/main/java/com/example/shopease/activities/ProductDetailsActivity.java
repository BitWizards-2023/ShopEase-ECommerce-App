/*

File: ProductDetailsActivity.java
Description: Displays product details, vendor details, and handles adding the product to the cart. Allows users to proceed to the order activity.
Author: Senula Nanayakkara
Date: 2024/09/30

*/
package com.example.shopease.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.shopease.R;
import com.example.shopease.models.CartItem;
import com.example.shopease.models.Vendor;
import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice, productDescription, vendorName, vendorDetails, vendorOpeningHours;
    private Button buyNowButton;
    private ArrayList<CartItem> cartItemList = new ArrayList<>();

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
        buyNowButton = findViewById(R.id.btn_buy_now); // Correct initialization

        // Retrieve product details passed from the previous activity
        String name = getIntent().getStringExtra("productName");
        String price = getIntent().getStringExtra("productPrice");
        String imageUrl = getIntent().getStringExtra("productImage");
        String description = getIntent().getStringExtra("productDescription");
        String productId = getIntent().getStringExtra("productId");
        String vendorId = getIntent().getStringExtra("vendorId");

        Log.d("ProductDetails", "Product Description: " + description);

        // Retrieve vendor details passed from the previous activity
        Vendor vendor = (Vendor) getIntent().getSerializableExtra("vendor");

        // Set product details in the UI
        productName.setText(name);
        productPrice.setText(price);
        productDescription.setText(description);
        Glide.with(this).load(imageUrl).into(productImage);  // Load the product image using Glide

        // Set vendor details in the UI
        if (vendor != null) {
            vendorName.setText(vendor.getName());
            vendorDetails.setText(vendor.getDetails());
            vendorOpeningHours.setText("Opening Hours: " + vendor.getOpeningHours());
        }

        // For demonstration purposes, create a sample vendor object and cart item
        Vendor vendor1 = new Vendor("Vendor 1", "Best in Electronics", "9-10");
        CartItem cartItem = new CartItem(productId, name, 1, Double.parseDouble(price), imageUrl, vendor1);

        // Add the current product to the cart item list
        cartItemList.add(cartItem);

        // Handle "Buy Now" button click to navigate to the OrderActivity
        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to navigate to the OrderActivity and pass the cart items
                navigateToOrderActivity();
            }
        });
    }

    /**
     * Navigates to the OrderActivity and passes the list of cart items.
     */
    private void navigateToOrderActivity() {
        Intent intent = new Intent(ProductDetailsActivity.this, OrderActivity.class);
        intent.putExtra("cartItems", cartItemList); // Pass cart items
        startActivity(intent); // Start the OrderActivity
    }
}
