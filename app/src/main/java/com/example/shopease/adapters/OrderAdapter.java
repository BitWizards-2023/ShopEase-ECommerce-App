/*

File: OrderAdapter.java
Description: Adapter for managing and displaying order details in a RecyclerView. It binds order data, including status, date, and product information, and dynamically sets the delivery status icon.
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
import com.example.shopease.models.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context; // Context to access resources and load data
    private List<Order> orderList; // List of orders to display

    // Constructor to initialize the context and the order list
    public OrderAdapter(Context context, List<Order> orderList) {
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
        Order order = orderList.get(position);

        // Set order status and date
        holder.orderStatus.setText(order.getStatus());
        holder.orderDate.setText(order.getDate());

        // Set order ID and tracking ID
        holder.orderId.setText("Order #" + order.getOrderId());
        holder.trackingId.setText("Tracking #" + order.getTrackingId());

        // Set product name and load product image dynamically
        holder.productName.setText(order.getProduct().getName());
        Glide.with(context)
                .load(order.getProduct().getImageUrl())  // Load product image from URL
                .into(holder.productImage);

        // Dynamically set the status icon based on order status
        switch (order.getStatus().toLowerCase()) {
            case "delivered":
                holder.deliveryStatusIcon.setImageResource(R.drawable.ic_delivered);  // Icon for delivered status
                break;
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
                holder.deliveryStatusIcon.setImageResource(R.drawable.ic_pending);  // Default status icon
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
