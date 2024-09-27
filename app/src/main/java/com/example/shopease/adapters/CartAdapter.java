package com.example.shopease.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopease.R;
import com.example.shopease.models.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItemList;
    private OnQuantityChangeListener onQuantityChangeListener;

    public CartAdapter(Context context, List<CartItem> cartItemList, OnQuantityChangeListener onQuantityChangeListener) {
        this.context = context;
        this.cartItemList = cartItemList;
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_product, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItemList.get(position);
        holder.productName.setText(item.getProductName());
        holder.productTotalPrice.setText("Rs. " + (item.getPrice() * item.getQuantity()));

        // Load product image using Glide
        Glide.with(context)
                .load(item.getImageUrl())
                .into(holder.productImage);

        holder.productQuantity.setText(String.valueOf(item.getQuantity()));

        // Increase quantity button
        holder.increaseQuantityButton.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
            onQuantityChangeListener.onQuantityChanged();
        });

        // Decrease quantity button
        holder.decreaseQuantityButton.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
                onQuantityChangeListener.onQuantityChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        CheckBox productCheckbox;
        ImageView productImage;
        TextView productName, productQuantity, productTotalPrice;
        Button increaseQuantityButton, decreaseQuantityButton;

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

    // Interface to notify quantity change
    public interface OnQuantityChangeListener {
        void onQuantityChanged();
    }
}
