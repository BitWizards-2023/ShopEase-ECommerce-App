package com.example.shopease.activities;

import android.os.Bundle;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopease.R;
import com.example.shopease.adapters.ProductAdapter;
import com.example.shopease.models.Product;
import com.example.shopease.models.Vendor;

import java.util.ArrayList;
import java.util.List;

public class CategoryProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);

        // Get the category name from the intent
        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("categoryName");

        // Set up the RecyclerView for product cards
        recyclerView = findViewById(R.id.recycler_view_category_products);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Initialize dummy vendor and product data
        Vendor vendor1 = new Vendor("Vendor 1", "Best in electronics", "9 AM - 9 PM");
        Vendor vendor2 = new Vendor("Vendor 2", "Best in cosmetics", "10 AM - 6 PM");

        // Initialize dummy product data based on category
        productList = new ArrayList<>();
        if (categoryName.equals("Electronics")) {
            productList.add(new Product("Laptop", "$1200", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRh-HnDONwh-dmt1eDx778YjtRGtQKjLdHOoQ&s", vendor1));
            productList.add(new Product("Headphones", "$200", "https://i5.walmartimages.com/seo/Beats-Studio3-Wireless-Noise-Cancelling-Headphones-with-Apple-W1-Headphone-Chip-Matte-Black_d0f19be2-e68f-4b82-b95c-c37db53518ba_1.868e67b856407714e2c5405a7e2f094a.jpeg", vendor2));
        } else if (categoryName.equals("Fashion")) {
            productList.add(new Product("Shirt", "$40", "https://www.3wisemen.co.nz/media/catalog/product/t/5/t50_236044_1.jpg?optimize=low&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700", vendor1));
            productList.add(new Product("Shoes", "$60", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMV14az0eixG3BHDTT64UlCappOQmNQjGREw&s", vendor2));
        } else if (categoryName.equals("Home & Kitchen")) {
            productList.add(new Product("Blender", "$80", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmPhlc3HZb51_dHmADPcGGKfV7y9_n6_fjHA&s", vendor1));
            productList.add(new Product("Toaster", "$30", "https://clearlineappliances.com/cdn/shop/files/1_171e4ab5-8939-4425-b2dd-ef79ae02be65_700x700.png?v=1684906993", vendor2));
        } else if (categoryName.equals("Beauty")) {
            productList.add(new Product("Lipstick", "$20", "https://i5.walmartimages.com/seo/Mac-Retro-Matte-Lipstick-Ruby-Woo-Very-Matte-Vivid-Blue-Red-0-1-oz_83bf9ab7-39bb-4587-b4bd-f39c0a833d69.bd6a97d163c418d201f54e24c2a0dc80.jpeg", vendor1));
            productList.add(new Product("Face Cream", "$25", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1_RJcoOWxQ4UhiTVGoeRSQKdPNjceTCZZgg&s", vendor2));
        }

        // Set up the product adapter with dummy products
        productAdapter = new ProductAdapter(this, productList, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                // Navigate to ProductDetailsActivity with product details
                Intent intent = new Intent(CategoryProductsActivity.this, ProductDetailsActivity.class);
                intent.putExtra("productName", product.getName());
                intent.putExtra("productPrice", product.getPrice());
                intent.putExtra("productImage", product.getImageUrl());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(productAdapter);
    }
}
