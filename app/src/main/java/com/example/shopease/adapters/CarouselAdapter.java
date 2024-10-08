/*

File: CarouselAdapter.java
Description: Adapter for managing a carousel of images in a RecyclerView. It uses Glide to load images from URLs into an ImageView.
Author: Senula Nanayakkara
Date: 2024/09/28

*/
package com.example.shopease.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopease.R;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {

    private Context context;
    private List<String> imageUrls;

    // Constructor to initialize the context and the image URL list
    public CarouselAdapter(Context context, List<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    /**
     * Called when the RecyclerView needs a new ViewHolder.
     * Inflates the layout for each item in the carousel.
     */
    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_item, parent, false);
        return new CarouselViewHolder(view);
    }

    /**
     * Binds the data (image URL) to the ViewHolder's ImageView.
     * Uses Glide to load the image from the URL.
     */
    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        String imageUrl = imageUrls.get(position);

        // Load the image using Glide library
        Glide.with(context)
                .load(imageUrl)
                .into(holder.carouselImageView);
    }

    /**
     * Returns the total number of items (images) in the carousel.
     */
    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    // ViewHolder class to hold references to the views in each item of the carousel
    public static class CarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView carouselImageView;

        // Constructor to bind the ImageView from the layout
        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            carouselImageView = itemView.findViewById(R.id.carousel_image);
        }
    }
}
