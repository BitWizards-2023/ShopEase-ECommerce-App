package com.example.shopease.activities;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);

        // Get the categoryId from the intent
        Intent intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");

        // Set up the RecyclerView for product cards
        recyclerView = findViewById(R.id.recycler_view_category_products);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Fetch products based on category from backend
        fetchProductsByCategory(categoryId);
    }

    // Fetch products from the backend based on the selected category
    private void fetchProductsByCategory(String categoryId) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<ProductSearchResponse> call = apiService.searchProducts(categoryId, 1, 10); // Assuming default pageNumber=1 and pageSize=10

        call.enqueue(new Callback<ProductSearchResponse>() {
            @Override
            public void onResponse(Call<ProductSearchResponse> call, Response<ProductSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList = new ArrayList<>();
                    for (ProductSearchResponse.ProductData productData : response.body().getData()) {
                        productList.add(new Product(
                                productData.getName(),
                                String.valueOf(productData.getPrice()),  // Convert price to String
                                productData.getImages().get(0),  // Assuming there's at least one image
                                productData.getVendorId()  // Store vendorId
                        ));
                    }

                    // Set up the product adapter with fetched products
                    productAdapter = new ProductAdapter(CategoryProductsActivity.this, productList, new ProductAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Product product) {
                            // Navigate to ProductDetailsActivity with product details
                            Intent intent = new Intent(CategoryProductsActivity.this, ProductDetailsActivity.class);
                            intent.putExtra("productName", product.getName());
                            intent.putExtra("productPrice", product.getPrice());
                            intent.putExtra("productImage", product.getImageUrl());
                            intent.putExtra("vendorId", product.getVendorId());  // Pass only vendorId
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(productAdapter);
                } else {
                    Toast.makeText(CategoryProductsActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductSearchResponse> call, Throwable t) {
                Toast.makeText(CategoryProductsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
