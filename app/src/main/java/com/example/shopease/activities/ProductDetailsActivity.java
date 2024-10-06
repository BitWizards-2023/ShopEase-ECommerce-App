package com.example.shopease.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.shopease.R;
import com.example.shopease.models.Vendor;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice, productDescription, vendorName, vendorDetails, vendorOpeningHours;

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

        // Get the passed product details
        String name = getIntent().getStringExtra("productName");
        String price = getIntent().getStringExtra("productPrice");
        String imageUrl = getIntent().getStringExtra("productImage");
        String description = getIntent().getStringExtra("productDescription");
        Log.d("ProductDetails", "Product Description: " + description);

        // Get the passed vendor details
        Vendor vendor = (Vendor) getIntent().getSerializableExtra("vendor");

        // Set product details
        productName.setText(name);
        productPrice.setText(price);
        productDescription.setText(description);
        Glide.with(this).load(imageUrl).into(productImage);

        // Set vendor details
        if (vendor != null) {
            vendorName.setText(vendor.getName());
            vendorDetails.setText(vendor.getDetails());
            vendorOpeningHours.setText("Opening Hours: " + vendor.getOpeningHours());
        }
    }
}
