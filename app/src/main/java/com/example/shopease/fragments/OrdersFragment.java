package com.example.shopease.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopease.R;
import com.example.shopease.models.CartItem;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {

    private TextView orderSummaryText, orderTrackingText;
    private RadioGroup paymentMethodsGroup;
    private Button payButton;
    private EditText cardNumberInput, expiryInput, cvvInput, deliveryAddressInput;

    private List<CartItem> cartItemList;
    private boolean isOrderPaid = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        // Initialize UI elements
        orderSummaryText = view.findViewById(R.id.order_summary_text);
        paymentMethodsGroup = view.findViewById(R.id.payment_methods_group);
        payButton = view.findViewById(R.id.pay_button);
        cardNumberInput = view.findViewById(R.id.card_number_input);
        expiryInput = view.findViewById(R.id.expiry_input);
        cvvInput = view.findViewById(R.id.cvv_input);
        deliveryAddressInput = view.findViewById(R.id.delivery_address_input);
        orderTrackingText = view.findViewById(R.id.order_tracking_info);

        // Get the passed cart items from the Bundle, handle the case when bundle is null
        if (getArguments() != null) {
            cartItemList = (List<CartItem>) getArguments().getSerializable("cartItems");
        }

        if (cartItemList == null) {
            cartItemList = new ArrayList<>(); // Provide an empty list or handle accordingly
        }

        // Display order summary
        displayOrderSummary();

        // Handle payment method selection
        paymentMethodsGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.payment_card) {
                // Show card input fields
                cardNumberInput.setVisibility(View.VISIBLE);
                expiryInput.setVisibility(View.VISIBLE);
                cvvInput.setVisibility(View.VISIBLE);
            } else {
                // Hide card input fields
                cardNumberInput.setVisibility(View.GONE);
                expiryInput.setVisibility(View.GONE);
                cvvInput.setVisibility(View.GONE);
            }
        });

        // Handle Pay button click
        payButton.setOnClickListener(v -> {
            if (!isOrderPaid) {
                // Perform payment processing logic here

                // After payment, show order tracking information
                showOrderTracking();
                isOrderPaid = true; // Mark the order as paid
            }
        });

        return view;
    }

    // Display the order summary with cart items and vendors
    private void displayOrderSummary() {
        StringBuilder summary = new StringBuilder();
        double total = 0;

        for (CartItem item : cartItemList) {
            summary.append(item.getProductName())
                    .append(" - Rs. ")
                    .append(item.getPrice())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append("\nVendor: ")
                    .append(item.getVendor().getName())
                    .append("\n\n");

            total += item.getPrice() * item.getQuantity();
        }

        summary.append("Total: Rs. ").append(total);
        orderSummaryText.setText(summary.toString());
    }

    // Show order tracking information after payment
    private void showOrderTracking() {
        orderSummaryText.setVisibility(View.GONE);
        deliveryAddressInput.setVisibility(View.GONE);
        paymentMethodsGroup.setVisibility(View.GONE);
        cardNumberInput.setVisibility(View.GONE);
        expiryInput.setVisibility(View.GONE);
        cvvInput.setVisibility(View.GONE);
        payButton.setVisibility(View.GONE);

        // Display order tracking information
        orderTrackingText.setVisibility(View.VISIBLE);
        orderTrackingText.setText("Your order has been placed and is on its way!\n\nTracking Status: \n- Dispatched\n- In Transit\n- Delivered");
    }
}
