/*

File: OrderAdapter.java
Description: Adapter for managing and displaying order details in a RecyclerView. It binds order data from the backend, including status, date, and product information, and dynamically sets the delivery status icon.
Author: Senula Nanayakkara
Date: 2024/10/05

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
import com.example.shopease.models.OrderResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context; // Context to access resources and load data
    private List<OrderResponse.OrderData> orderList; // List of orders from the backend

    // Constructor to initialize the context and the order list
    public OrderAdapter(Context context, List<OrderResponse.OrderData> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    /**
     * Called when the RecyclerView needs a new ViewHolder to represent an order item.
     * Inflates the layout for each order item.
     */
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    /**
     * Binds the data (order) to the ViewHolder's views.
     * Handles displaying order status, date, order ID, product name, and product image.
     * Also sets the appropriate delivery status icon dynamically based on the order status.
     */
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderResponse.OrderData order = orderList.get(position);

        // Set order status, date, and other details
        holder.orderStatus.setText(order.getStatus());

        // Format and display the date in a user-friendly format
        String formattedDate = formatTimestamp(order.getCreatedAt());
        holder.orderDate.setText(formattedDate);

        holder.orderId.setText("Order #" + order.getOrderNumber());
        holder.trackingId.setText("Tracking #" + order.getId());

        // Assuming each order contains at least one item
        if (!order.getItems().isEmpty()) {
            OrderResponse.OrderData.OrderItem orderItem = order.getItems().get(0);
            OrderResponse.OrderData.OrderItem.ProductDetails productDetails = orderItem.getProductDetails();

            // Set product name
            holder.productName.setText(productDetails.getName());

            // Load product image (assuming there's at least one image)
            if (!productDetails.getImages().isEmpty()) {
                Glide.with(context)
                        .load(productDetails.getImages().get(0))
                        .into(holder.productImage);
            }
        }

        // Dynamically set the status icon based on the order status
        switch (order.getStatus().toLowerCase()) {
            case "delivered":
                holder.deliveryStatusIcon.setImageResource(R.drawable.ic_delivered);  // Icon for delivered status
                break;
            case "processing":
            case "in transit":
                holder.deliveryStatusIcon.setImageResource(R.drawable.ic_in_transit);  // Icon for in transit status
                break;
            case "pending":
                holder.deliveryStatusIcon.setImageResource(R.drawable.ic_pending);  // Icon for pending status
                break;
            case "cancelled":
                holder.deliveryStatusIcon.setImageResource(R.drawable.ic_cancelled);  // Icon for cancelled status
                break;
            default:
                holder.deliveryStatusIcon.setImageResource(R.drawable.ic_pending);  // Default pending icon
                break;
        }
    }

    /**
     * Returns the total number of orders in the list.
     */
    @Override
    public int getItemCount() {
        return orderList.size();
    }

    /**
     * Formats a timestamp string to a user-friendly format like "07 Oct 2024, 18:52 PM".
     *
     * @param timestamp The original timestamp string (e.g., 2024-10-07T18:52:59.883+00:00).
     * @return The formatted date string.
     */
    private String formatTimestamp(String timestamp) {
        // Define the original format and target format
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm a", Locale.getDefault());

        try {
            Date date = originalFormat.parse(timestamp);
            return targetFormat.format(date);  // Return the formatted date
        } catch (ParseException e) {
            e.printStackTrace();
            return timestamp;  // In case of an error, return the original timestamp
        }
    }

    // ViewHolder class to hold references to the views in each order item layout
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView deliveryStatusIcon, productImage;
        TextView orderStatus, orderDate, productName, orderId, trackingId;

        // Constructor to bind views from the layout
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            deliveryStatusIcon = itemView.findViewById(R.id.img_delivery_status);
            productImage = itemView.findViewById(R.id.img_product);
            orderStatus = itemView.findViewById(R.id.tv_delivery_status);
            orderDate = itemView.findViewById(R.id.tv_order_date);
            productName = itemView.findViewById(R.id.tv_product_name);
            orderId = itemView.findViewById(R.id.tv_order_info);
            trackingId = itemView.findViewById(R.id.tv_tracking_info);
        }
    }
}
