/*

File: CartFragment.java
Description: Fragment for displaying items in the cart and managing the checkout process. It handles updating the subtotal when item quantities change and navigates to the OrderActivity for placing an order.
Author: Senula Nanayakkara
Date: 2024/10/05

*/
package com.example.shopease.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopease.R;
import com.example.shopease.activities.OrderActivity;
import com.example.shopease.adapters.CartAdapter;
import com.example.shopease.models.CartItem;
import com.example.shopease.models.Vendor;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;
    private TextView subtotalText;
    private Button checkoutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize UI elements
        recyclerView = view.findViewById(R.id.recycler_view_cart_items);
        subtotalText = view.findViewById(R.id.subtotal_text);
        checkoutButton = view.findViewById(R.id.checkout_button);

        // Set up the RecyclerView with a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartItemList = new ArrayList<>();

        // Add dummy cart items for demonstration purposes
        Vendor vendor1 = new Vendor("Vendor 1", "Best in electronics", "9 AM - 9 PM");
        Vendor vendor2 = new Vendor("Vendor 2", "Best in cosmetics", "10 AM - 6 PM");

        cartItemList.add(new CartItem("Product 1", 1, 750.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT84Fy08oMXKt99j3kD-x7c4s3YMMnWA5fbFA&s", vendor1));
        cartItemList.add(new CartItem("Product 2", 2, 745.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7cLezQqG3X2fKKOAWH2A6ns1UaBZfAoI60A&s", vendor2));

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

        // Handle checkout button click to navigate to the OrderActivity
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToOrderActivity(); // Navigate to order activity
            }
        });

        return view;
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
