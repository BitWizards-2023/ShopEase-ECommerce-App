/*

File: CartAdapter.java
Description: Adapter for managing cart items in a RecyclerView. It handles displaying product details, quantity changes, and notifies when the product quantity is updated.
Author: Senula Nanayakkara
Date: 2024/10/01

*/
package com.example.shopease.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopease.R;
import com.example.shopease.activities.ProductDetailsActivity;
import com.example.shopease.models.CartItem;
import com.example.shopease.models.UpdateCartRequest;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItemList;
    private OnQuantityChangeListener onQuantityChangeListener;

    // Constructor to initialize the context, cart items, and quantity change listener
    public CartAdapter(Context context, List<CartItem> cartItemList, OnQuantityChangeListener onQuantityChangeListener) {
        this.context = context;
        this.cartItemList = cartItemList;
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    /**
     * Called when the RecyclerView needs a new ViewHolder to represent an item.
     * Inflates the layout for each cart item.
     */
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_product, parent, false);
        return new CartViewHolder(view);
    }

    /**
     * Binds the data (cart item) to the ViewHolder's views.
     * Handles displaying product name, image, quantity, and price.
     */
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItemList.get(position);
        holder.productName.setText(item.getProductName());
        holder.productTotalPrice.setText("Rs. " + (item.getPrice() * item.getQuantity()));

        // Load the product image using Glide
        Glide.with(context)
                .load(item.getImageUrl())
                .into(holder.productImage);

        // Set the product quantity
        holder.productQuantity.setText(String.valueOf(item.getQuantity()));

        // Handle increase quantity button click
        holder.increaseQuantityButton.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1); // Increase the quantity
            notifyItemChanged(position); // Notify that item has changed
            onQuantityChangeListener.onQuantityChanged(); // Notify the listener that quantity has changed
            updateCartItem(item);  // Call to update cart in backend
        });

        // Handle decrease quantity button click
        holder.decreaseQuantityButton.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1); // Decrease the quantity
                notifyItemChanged(position); // Notify that item has changed
                onQuantityChangeListener.onQuantityChanged(); // Notify the listener that quantity has changed
                updateCartItem(item);  // Call to update cart in backend
            }
        });
    }

    /**
     * Returns the total number of items in the cart.
     */
    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    // Method to call API and update cart item
    private void updateCartItem(CartItem item) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        String cartItemId = item.getCartItemId(); // Assuming cartItemId is the same as productId
        // Create selectedOptions map with empty strings as per Swagger example
        Map<String, String> selectedOptions = new HashMap<>();
        selectedOptions.put("additionalProp1", "");
        selectedOptions.put("additionalProp2", "");
        selectedOptions.put("additionalProp3", "");

        UpdateCartRequest updateCartRequest = new UpdateCartRequest(item.getQuantity(), selectedOptions);  // Create request body with quantity and empty additional props
        // Retrieve JWT token from SharedPreferences
        String token = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .getString("jwt_token", null);

        if (token != null) {
            String authHeader = "Bearer " + token; // Prepare token for Authorization header
            Call<Void> call = apiService.updateCartItem(cartItemId, authHeader, updateCartRequest);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Cart updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Failed to update cart", Toast.LENGTH_SHORT).show();
                        Log.e("CartAdapter", "Error: " + response.toString());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(context, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle missing token case
            Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show();
        }


    }


    // ViewHolder class to hold references to the views in each cart item layout
    public class CartViewHolder extends RecyclerView.ViewHolder {
        CheckBox productCheckbox;
        ImageView productImage;
        TextView productName, productQuantity, productTotalPrice;
        Button increaseQuantityButton, decreaseQuantityButton;

        // Constructor to bind views from the layout
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productCheckbox = itemView.findViewById(R.id.product_checkbox);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            productTotalPrice = itemView.findViewById(R.id.product_total_price);
            increaseQuantityButton = itemView.findViewById(R.id.increase_quantity_button);
            decreaseQuantityButton = itemView.findViewById(R.id.decrease_quantity_button);
        }
    }

    // Interface to notify when the quantity of a product in the cart has changed
    public interface OnQuantityChangeListener {
        void onQuantityChanged();
    }
}
