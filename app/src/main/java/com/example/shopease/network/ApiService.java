package com.example.shopease.network;

import com.example.shopease.models.CategoryResponse;
import com.example.shopease.models.LoginRequest;
import com.example.shopease.models.LoginResponse;
import com.example.shopease.models.ProductResponse;
import com.example.shopease.models.ProductSearchResponse;
import com.example.shopease.models.RegisterRequest;
import com.example.shopease.models.RegisterResponse;
import com.example.shopease.models.UpdateProfileRequest;
import com.example.shopease.models.UpdateProfileResponse;
import com.example.shopease.models.UploadResponse;
import com.example.shopease.models.UserProfileResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/api/v1/auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest request);

    @POST("/api/v1/auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @Multipart
    @POST("/api/Blob/upload")
    Call<UploadResponse> uploadImage(@Part MultipartBody.Part image);

    // API for fetching profile details using JWT token
    @GET("/api/v1/Auth/me")
    Call<UserProfileResponse> getUserProfile(@Header("Authorization") String authToken);

    @PUT("/api/v1/User/update-me")
    Call<UpdateProfileResponse> updateUserProfile(@Header("Authorization") String authToken, @Body UpdateProfileRequest request);

    @GET("/api/Category")
    Call<CategoryResponse> getCategories();

    @GET("api/v1/products")
    Call<ProductResponse> getProducts();

    // New method to search products by category
    @GET("api/v1/products/search")
    Call<ProductSearchResponse> searchProducts(
            @Query("categoryId") String categoryId,
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );
}
