package com.example.shopease.network;

import com.example.shopease.models.LoginRequest;
import com.example.shopease.models.LoginResponse;
import com.example.shopease.models.RegisterRequest;
import com.example.shopease.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/v1/auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest request);

    @POST("/api/v1/auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
