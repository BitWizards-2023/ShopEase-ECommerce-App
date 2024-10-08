/*

File: OrderActivity.java
Description: Handles the order placement process including collecting user address details, selecting payment methods, and submitting the order to the backend API. It also displays order summary and order tracking information.
Author: Senula Nanayakkara
Date: 2024/10/03

*/
package com.example.shopease.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopease.R;
import com.example.shopease.models.Address;
import com.example.shopease.models.CartItem;
import com.example.shopease.models.OrderRequest;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    // Declare UI components
    private TextView orderSummaryText, orderTrackingText;
    private RadioGroup paymentMethodsGroup;
    private Button payButton;
    private EditText streetInput, cityInput, stateInput, postalCodeInput, countryInput;
    private EditText cardNumberInput, expiryInput, cvvInput;
    private List<CartItem> cartItemList;
    private boolean isOrderPaid = false;
    private String selectedPaymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize UI elements
        orderSummaryText = findViewById(R.id.order_summary_text);
        paymentMethodsGroup = findViewById(R.id.payment_methods_group);
        payButton = findViewById(R.id.pay_button);
        cardNumberInput = findViewById(R.id.card_number_input);
        expiryInput = findViewById(R.id.expiry_input);
        cvvInput = findViewById(R.id.cvv_input);

        // Initialize address input fields
        streetInput = findViewById(R.id.street_input);
        cityInput = findViewById(R.id.city_input);
        stateInput = findViewById(R.id.state_input);
        postalCodeInput = findViewById(R.id.postal_code_input);
        countryInput = findViewById(R.id.country_input);

        orderTrackingText = findViewById(R.id.order_tracking_info);

        // Get cart items passed through the intent
        cartItemList = (List<CartItem>) getIntent().getSerializableExtra("cartItems");
        if (cartItemList == null) {
            cartItemList = new ArrayList<>();
        }

        // Display order summary
        displayOrderSummary();

        // Handle payment method selection
        paymentMethodsGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.payment_card) {
                selectedPaymentMethod = "CARD";
                cardNumberInput.setVisibility(View.VISIBLE);
                expiryInput.setVisibility(View.VISIBLE);
                cvvInput.setVisibility(View.VISIBLE);
            } else {
                selectedPaymentMethod = "CASH_ON_DELIVERY";
                cardNumberInput.setVisibility(View.GONE);
                expiryInput.setVisibility(View.GONE);
                cvvInput.setVisibility(View.GONE);
            }
        });

        // Handle Pay button click
        payButton.setOnClickListener(v -> {
            if (!isOrderPaid) {
                // Submit the order if not already paid
                submitOrder();
            }
        });
    }

    /**
     * Submits the order to the backend API with collected details.
     */
    private void submitOrder() {
        // Collect address details from input fields
        String street = streetInput.getText().toString();
        String city = cityInput.getText().toString();
        String state = stateInput.getText().toString();
        String postalCode = postalCodeInput.getText().toString();
        String country = countryInput.getText().toString();

        // Create Address object
        Address shippingAddress = new Address(street, city, state, postalCode, country);

        // Create the order request body with the cart items, address, and selected payment method
        OrderRequest orderRequest = new OrderRequest(cartItemList, shippingAddress, selectedPaymentMethod);

        // Retrieve JWT token from SharedPreferences
        String token = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .getString("jwt_token", null);

        if (token != null) {
            String authHeader = "Bearer " + token;

            Log.e("OrderActivity", "JWT token found: " + token);

            // Make the API call to place the order
            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            Call<Void> call = apiService.placeOrder(authHeader, orderRequest);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Show order tracking information after successful order placement
                        showOrderTracking();
                        isOrderPaid = true;
                    } else {
                        Log.e("OrderActivity", "Failed to place order: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle API call failure
                    Log.e("OrderActivity", "API call failed: " + t.getMessage());
                }
            });
        } else {
            Log.e("OrderActivity", "JWT token not found. Cannot place order.");
        }
    }

    /**
     * Displays the order summary with cart item details and total cost.
     */
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

            // Calculate the total cost of items in the cart
            total += item.getPrice() * item.getQuantity();
        }

        // Display the total cost at the end of the summary
        summary.append("Total: Rs. ").append(total);
        orderSummaryText.setText(summary.toString());
    }

    /**
     * Displays the order tracking information after successful payment.
     */
    private void showOrderTracking() {
        // Hide order summary and payment-related UI elements
        orderSummaryText.setVisibility(View.GONE);
        streetInput.setVisibility(View.GONE);
        cityInput.setVisibility(View.GONE);
        stateInput.setVisibility(View.GONE);
        postalCodeInput.setVisibility(View.GONE);
        countryInput.setVisibility(View.GONE);
        cardNumberInput.setVisibility(View.GONE);
        expiryInput.setVisibility(View.GONE);
        cvvInput.setVisibility(View.GONE);
        paymentMethodsGroup.setVisibility(View.GONE);
        payButton.setVisibility(View.GONE);

        // Display the order tracking information
        orderTrackingText.setVisibility(View.VISIBLE);
        orderTrackingText.setText("Your order has been placed and is on its way!\n\nTracking Status: \n- Dispatched\n- In Transit\n- Delivered");
    }
}
