/*

File: CategoryAdapter.java
Description: Adapter for displaying categories in a RecyclerView. It handles binding category details and navigates to the CategoryProductsActivity when a category is clicked.
Author: Senula Nanayakkara
Date: 2024/10/02

*/
package com.example.shopease.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopease.R;
import com.example.shopease.activities.CategoryProductsActivity;
import com.example.shopease.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categoryList;

    // Constructor to initialize the context and the category list
    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder to display a category item.
     * Inflates the layout for each category item.
     */
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    /**
     * Binds the data (category) to the ViewHolder's views.
     * Handles displaying category name and image, and handles click events for navigation.
     */
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.categoryName.setText(category.getName());

        // Load the category image using Glide
        Glide.with(context)
                .load(category.getImageUrl())
                .into(holder.categoryImage);

        // Handle item click to navigate to CategoryProductsActivity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to CategoryProductsActivity and pass the category ID and name
                Intent intent = new Intent(context, CategoryProductsActivity.class);
                intent.putExtra("categoryId", category.getId());  // Pass the category ID
                intent.putExtra("categoryName", category.getName());
                context.startActivity(intent);
            }
        });
    }

    /**
     * Returns the total number of category items in the list.
     */
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    // ViewHolder class to hold references to the views in each category item layout
    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryName;

        // Constructor to bind views from the layout
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.category_image);
            categoryName = itemView.findViewById(R.id.category_name);
        }
    }
}
