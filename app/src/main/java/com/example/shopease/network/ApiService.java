/*
File: ApiService.java
Description: Interface defining the API service endpoints for the Shopease application. It includes methods for user authentication, profile management, product and category retrieval, cart management, and placing orders. This service uses Retrofit for making HTTP requests to the backend.
The methods include various types of requests such as POST, PUT, GET, and Multipart requests for handling different operations in the app.
Author: Senula Nanayakkara
Date: 2024/09/30
*/
package com.example.shopease.network;

import com.example.shopease.models.CartRequest;
import com.example.shopease.models.CategoryResponse;
import com.example.shopease.models.DetailedProductResponse;
import com.example.shopease.models.LoginRequest;
import com.example.shopease.models.LoginResponse;
import com.example.shopease.models.LogoutRequest;
import com.example.shopease.models.OrderRequest;
import com.example.shopease.models.OrderResponse;
import com.example.shopease.models.ProductResponse;
import com.example.shopease.models.ProductSearchResponse;
import com.example.shopease.models.RegisterRequest;
import com.example.shopease.models.RegisterResponse;
import com.example.shopease.models.UpdateCartRequest;
import com.example.shopease.models.UpdateProfileRequest;
import com.example.shopease.models.UpdateProfileResponse;
import com.example.shopease.models.UploadResponse;
import com.example.shopease.models.UserProfileResponse;
import com.example.shopease.models.VendorProfileResponse;
import com.example.shopease.models.CartResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import java.util.Map;

public interface ApiService {

    //POST request for register customer
    @POST("/api/v1/auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest request);

    //POST request for login customer
    @POST("/api/v1/auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    //POST request for upload image file
    @Multipart
    @POST("/api/Blob/upload")
    Call<UploadResponse> uploadImage(@Part MultipartBody.Part image);

    // API for fetching profile details using JWT token
    @GET("/api/v1/Auth/me")
    Call<UserProfileResponse> getUserProfile(@Header("Authorization") String authToken);

    @PUT("/api/v1/User/update-me")
    Call<UpdateProfileResponse> updateUserProfile(@Header("Authorization") String authToken, @Body UpdateProfileRequest request);

    // Logout user
    @POST("/api/v1/Auth/logout")
    Call<Void> logoutUser(@Body LogoutRequest logoutRequest);

    //Fetch Categories
    @GET("/api/Category")
    Call<CategoryResponse> getCategories();

    //Fetch Products
    @GET("api/v1/products")
    Call<ProductResponse> getProducts();

    //Fetch prdocut by id
    @GET("/api/v1/products/{id}")
    Call<DetailedProductResponse> getProductDetails(@Path("id") String productId);

    // New method to search products by category
    @GET("api/v1/products/search")
    Call<ProductSearchResponse> searchProducts(
            @Query("categoryId") String categoryId,
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );

    //POST request for submit vendor rating
    @POST("/api/VendorRating/submit/{vendorId}")
    Call<Void> submitVendorRating(@Path("vendorId") String vendorId, @Header("Authorization") String token, @Body Map<String, Object> ratingRequest);

    //Place a new order
    @POST("/api/v1/orders")
    Call<Void> placeOrder(
            @Header("Authorization") String authToken,
            @Body OrderRequest orderRequest
    );

    //Fetch list of orders for customer
    @GET("api/v1/orders/customer")
    Call<OrderResponse> getCustomerOrders(@Header("Authorization") String token);

    // PUT request to update cart item
    @PUT("/api/v1/cart/items")
    Call<Void> updateCartItem(@Body CartRequest cartRequest, @Header("Authorization") String authHeader);

    // GET request to fetch cart items
    @GET("/api/v1/cart")
    Call<CartResponse> getCartItems(@Header("Authorization") String authHeader);

    // PATCH request to update a cart item
    @PATCH("/api/v1/cart/items/{cartItemId}")
    Call<Void> updateCartItem(@Path("cartItemId") String cartItemId, @Header("Authorization") String authHeader, @Body UpdateCartRequest updateCartRequest);

    // GET request to checkout cart
    @GET("/api/v1/cart/checkout")
    Call<Void> checkoutCart(@Header("Authorization") String token);

    /**
     * Fetches the vendor profile details based on the vendorId.
     */
    @GET("/api/VendorRating/profile/{vendorId}")
    Call<VendorProfileResponse> getVendorProfile(
            @Path("vendorId") String vendorId,
            @Header("Authorization") String authHeader
    );
}
