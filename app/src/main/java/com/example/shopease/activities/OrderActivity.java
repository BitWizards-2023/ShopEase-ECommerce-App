package com.example.shopease.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopease.R;
import com.example.shopease.fragments.OrdersFragment;
import com.example.shopease.models.CartItem;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order); // Contains fragment_container

        // Get the passed cart items
        Intent intent = getIntent();
        ArrayList<CartItem> cartItems = (ArrayList<CartItem>) intent.getSerializableExtra("cartItems");

        // Load OrdersFragment and pass cart items
        if (savedInstanceState == null) {
            OrdersFragment ordersFragment = new OrdersFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("cartItems", cartItems);
            ordersFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ordersFragment)
                    .commit();
        }
    }
}
