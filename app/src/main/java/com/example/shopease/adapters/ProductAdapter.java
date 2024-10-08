/*

File: ProductAdapter.java
Description: Adapter for managing and displaying product cards in a RecyclerView. It binds product data to the views and handles item click events for product selection.
Author: Senula Nanayakkara
Date: 2024/09/28

*/
package com.example.shopease.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopease.R;
import com.example.shopease.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private OnItemClickListener onItemClickListener;

    // Constructor to initialize the context, product list, and item click listener
    public ProductAdapter(Context context, List<Product> productList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.productList = productList;
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder to represent a product item.
     * Inflates the layout for each product card.
     */
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_card, parent, false);
        return new ProductViewHolder(view);
    }

    /**
     * Binds the data (product) to the ViewHolder's views.
     * Handles displaying the product name, price, and image.
     * Also sets up the click listener for each product card.
     */
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());

        // Load the product image using Glide
        Glide.with(context)
                .load(product.getImageUrl())
                .into(holder.productImage);

        // Handle item click events
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(product); // Trigger the click listener when a product is clicked
                }
            }
        });
    }

    /**
     * Returns the total number of product items in the list.
     */
    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder class to hold references to the views in each product card layout
    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;

        // Constructor to bind views from the layout
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
        }
    }

    // Interface to define a click listener for product items
    public interface OnItemClickListener {
        void onItemClick(Product product); // Triggered when a product is clicked
    }
}
