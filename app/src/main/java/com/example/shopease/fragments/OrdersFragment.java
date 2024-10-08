/*
 * File: OrdersFragment.java
 * Description: Fragment for displaying the list of customer orders. It retrieves order data from the backend and displays it in a RecyclerView.
 * Author: Senula Nanayakkara
 * Date: 2024/10/06
 */
package com.example.shopease.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopease.R;
import com.example.shopease.adapters.OrderAdapter;
import com.example.shopease.models.OrderResponse;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<OrderResponse.OrderData> orderList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Fetch the orders from the backend
        fetchOrders();

        return view;
    }

    private void fetchOrders() {
        Context context = getContext();
        String token = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .getString("jwt_token", null);

        if (token != null) {
            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            Call<OrderResponse> call = apiService.getCustomerOrders("Bearer " + token);

            call.enqueue(new Callback<OrderResponse>() {
                @Override
                public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        orderList = response.body().getData();
                        orderAdapter = new OrderAdapter(getActivity(), orderList);
                        recyclerView.setAdapter(orderAdapter);
                    } else {
                        Toast.makeText(getActivity(), "Failed to retrieve orders", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<OrderResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.e("OrdersFragment", "JWT token is missing.");
        }
    }
}
