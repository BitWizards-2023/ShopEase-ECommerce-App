/*
File: CartFragment.java
Description: Fragment for displaying items in the cart and managing the checkout process. It fetches the cart data from the backend, retrieves product details for each cart item, and displays the product's name and image.
Author: Senula Nanayakkara
Date: 2024/10/05
*/

package com.example.shopease.fragments;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopease.R;
import com.example.shopease.activities.OrderActivity;
import com.example.shopease.adapters.CartAdapter;
import com.example.shopease.models.CartItem;
import com.example.shopease.models.CartResponse;
import com.example.shopease.models.DetailedProductResponse;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList = new ArrayList<>();
    private TextView subtotalText;
    private Button checkoutButton;
    private ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize UI elements
        recyclerView = view.findViewById(R.id.recycler_view_cart_items);
        subtotalText = view.findViewById(R.id.subtotal_text);
        checkoutButton = view.findViewById(R.id.checkout_button);

        // Initialize API service
        apiService = RetrofitClient.getInstance().create(ApiService.class);

        // Set up the RecyclerView with a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Fetch current cart items from the backend
        fetchCartItems();

        // Handle checkout button click to navigate to the OrderActivity
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCheckoutAPI(); // Call the checkout API
            }
        });

        return view;
    }

    /**
     * Call the checout cart items from the backend and then navigate for order confirm page.
     */
    private void callCheckoutAPI() {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

        // Retrieve JWT token from SharedPreferences
        String token = getContext().getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .getString("jwt_token", null);

        if (token != null) {
            String authHeader = "Bearer " + token; // Prepare the Authorization header

            // Make the API call
            Call<Void> call = apiService.checkoutCart(authHeader);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Navigate to the OrderActivity if checkout is successful
                        navigateToOrderActivity();
                    } else {
                        // Handle failure response
                        Toast.makeText(getContext(), "Checkout failed. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle network failure
                    Toast.makeText(getContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle missing token case
            Toast.makeText(getContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Fetches the cart items from the backend and then fetches the product details for each item.
     */
    private void fetchCartItems() {
        // Retrieve JWT token from SharedPreferences
        String token = getContext().getSharedPreferences("auth_prefs", getContext().MODE_PRIVATE)
                .getString("jwt_token", null);

        if (token != null) {
            String authHeader = "Bearer " + token;

            // Make API call to get cart items
            Call<CartResponse> call = apiService.getCartItems(authHeader);
            call.enqueue(new Callback<CartResponse>() {
                @Override
                public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Populate the cart item list with data from the response
                        cartItemList = response.body().getItems();

                        // Fetch product details for each item in the cart
                        fetchProductDetailsForCartItems();
                    } else {
                        Log.e("CartFragment", "Error fetching cart items: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<CartResponse> call, Throwable t) {
                    Log.e("CartFragment", "API call failed: " + t.getMessage());
                }
            });
        } else {
            Log.e("CartFragment", "User not authenticated");
        }
    }

    /**
     * Fetches product details for each cart item and updates the UI.
     */
    private void fetchProductDetailsForCartItems() {
        for (CartItem cartItem : cartItemList) {
            Call<DetailedProductResponse> productCall = apiService.getProductDetails(cartItem.getProductId());
            productCall.enqueue(new Callback<DetailedProductResponse>() {
                @Override
                public void onResponse(Call<DetailedProductResponse> call, Response<DetailedProductResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Update cart item with product details
                        DetailedProductResponse product = response.body();
                        cartItem.setProductName(product.getData().getName());
                        cartItem.setImageUrl(product.getData().getImages().get(0)); // Assuming the first image is used

                        // Refresh UI with product details
                        updateCartUI();
                    } else {
                        Log.e("CartFragment", "Error fetching product details: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<DetailedProductResponse> call, Throwable t) {
                    Log.e("CartFragment", "Product API call failed: " + t.getMessage());
                }
            });
        }
    }

    /**
     * Updates the cart UI, including setting up the adapter and calculating the subtotal.
     */
    private void updateCartUI() {
        // Set up CartAdapter with quantity change listener
        cartAdapter = new CartAdapter(getContext(), cartItemList, new CartAdapter.OnQuantityChangeListener() {
            @Override
            public void onQuantityChanged() {
                updateSubtotal(); // Update subtotal when quantity changes
            }
        });
        recyclerView.setAdapter(cartAdapter);

        // Initial subtotal calculation
        updateSubtotal();
    }

    /**
     * Updates the subtotal whenever the quantity of items in the cart changes.
     */
    private void updateSubtotal() {
        double subtotal = 0.0;
        for (CartItem item : cartItemList) {
            subtotal += item.getPrice() * item.getQuantity(); // Calculate subtotal based on item quantity
        }
        subtotalText.setText("Subtotal: Rs. " + subtotal); // Display subtotal
    }

    /**
     * Navigates to the OrderActivity, passing the cart items for order processing.
     */
    private void navigateToOrderActivity() {
        Intent intent = new Intent(getActivity(), OrderActivity.class);
        intent.putExtra("cartItems", (ArrayList<CartItem>) cartItemList); // Pass cart items as an ArrayList
        startActivity(intent); // Start the order activity
    }
}
