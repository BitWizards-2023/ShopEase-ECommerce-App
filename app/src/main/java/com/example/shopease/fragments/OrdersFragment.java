/*

File: OrdersFragment.java
Description: Fragment for displaying the list of customer orders. It initializes a RecyclerView and uses dummy data to populate the order list.
Author: Senula Nanayakkara
Date: 2024/10/06

*/
package com.example.shopease.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopease.R;
import com.example.shopease.adapters.OrderAdapter;
import com.example.shopease.models.Order;
import com.example.shopease.models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); // Set the layout manager for the RecyclerView

        // Generate dummy order data
        generateDummyOrders();

        // Set up the adapter with the order list
        orderAdapter = new OrderAdapter(getActivity(), orderList);
        recyclerView.setAdapter(orderAdapter); // Attach the adapter to the RecyclerView

        return view;
    }

    /**
     * Generates dummy order data for demonstration purposes.
     * This method creates a list of dummy orders with product information and status.
     */
    private void generateDummyOrders() {
        orderList = new ArrayList<>();

        // Create sample products for the orders
        Product product1 = new Product("Sm-002", "Smartphone", "$500", "https://files.refurbed.com/ii/iphone-15-pro-1694586795.jpg", "V001", "Vendor 1");
        Product product2 = new Product("Hm-001", "Headphone", "$500", "https://media.croma.com/image/upload/v1674045672/Croma%20Assets/Communication/Headphones%20and%20Earphones/Images/239033_0_aq6dfy.png", "V002", "Vendor 2");

        // Add sample orders to the order list
        orderList.add(new Order("216358502434489", "LK-DM-DEX-013025048", product1, "Delivered", "02/10/2024"));
        orderList.add(new Order("216358509634489", "LK-DM-DEX-012997513", product2, "In Transit", "01/10/2024"));
        orderList.add(new Order("216358509634488", "LK-DM-DEX-012997514", product1, "Pending", "30/09/2024"));
    }
}
