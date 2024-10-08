/*

File: CategoryProductsActivity.java
Description: Displays products based on the selected category in a grid layout. Fetches product data from the backend and allows navigation to the ProductDetailsActivity when a product is clicked.
Author: Senula Nanayakkara
Date: 2024/09/28

*/
package com.example.shopease.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopease.R;
import com.example.shopease.adapters.ProductAdapter;
import com.example.shopease.models.Product;
import com.example.shopease.models.ProductSearchResponse;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private String categoryId;
    private String categoryName;
    private TextView categoryTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);

        // Get the categoryId and categoryName from the intent
        Intent intent = getIntent();
        categoryTitle = findViewById(R.id.category_name_text);

        // Set up the back button functionality
        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());

        // Get category details from intent extras
        categoryId = intent.getStringExtra("categoryId");
        categoryName = getIntent().getStringExtra("categoryName");
        categoryTitle.setText(categoryName);

        // Set up the RecyclerView for product cards with a grid layout
        recyclerView = findViewById(R.id.recycler_view_category_products);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Fetch products from the backend based on the selected category
        fetchProductsByCategory(categoryId);
    }

    /**
     * Fetches products from the backend API based on the selected category ID.
     * Sets up the RecyclerView with the fetched products.
     */
    private void fetchProductsByCategory(String categoryId) {
        // Initialize API service for fetching products
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<ProductSearchResponse> call = apiService.searchProducts(categoryId, 1, 10); // Assuming pageNumber=1, pageSize=10

        call.enqueue(new Callback<ProductSearchResponse>() {
            @Override
            public void onResponse(Call<ProductSearchResponse> call, Response<ProductSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Initialize the product list and populate it with the fetched product data
                    productList = new ArrayList<>();
                    for (ProductSearchResponse.ProductData productData : response.body().getData()) {
                        productList.add(new Product(
                                productData.getId(),
                                productData.getName(),
                                String.valueOf(productData.getPrice()),
                                productData.getImages().get(0),
                                productData.getVendorId(),
                                productData.getDescription()
                        ));
                    }

                    // Set up the product adapter with the product list
                    productAdapter = new ProductAdapter(CategoryProductsActivity.this, productList, new ProductAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Product product) {
                            // Navigate to ProductDetailsActivity with selected product details
                            Intent intent = new Intent(CategoryProductsActivity.this, ProductDetailsActivity.class);
                            intent.putExtra("productName", product.getName());
                            intent.putExtra("productPrice", product.getPrice());
                            intent.putExtra("productImage", product.getImageUrl());
                            intent.putExtra("productDescription", product.getDescription());
                            Log.d("ProductDetails", "Product Description: " + product.getDescription());
                            intent.putExtra("vendorId", product.getVendorId());
                            startActivity(intent);
                        }
                    });
                    // Attach the adapter to the RecyclerView
                    recyclerView.setAdapter(productAdapter);
                } else {
                    // Handle failure to fetch products
                    Toast.makeText(CategoryProductsActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductSearchResponse> call, Throwable t) {
                // Handle network failure or other errors
                Toast.makeText(CategoryProductsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

