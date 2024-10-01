package com.example.shopease.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.shopease.R;
import com.example.shopease.models.LoginRequest;
import com.example.shopease.models.LoginResponse;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;
import com.example.shopease.utils.CustomDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton, signupButton;
    private static final String TAG = "LoginActivity";
    private static final String CHANNEL_ID = "shop_ease_notifications";  // Notification Channel ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);

        // Handle login button click
        loginButton.setOnClickListener(view -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            // Validate input
            if (email.isEmpty() || password.isEmpty()) {
                CustomDialog.showDialog(LoginActivity.this, false, "Error", "Please fill all fields.");
                return;
            }

            // Create LoginRequest
            LoginRequest loginRequest = new LoginRequest(email, password);

            // Call API to login
            loginUser(loginRequest);
        });

        // Navigate to SignUpActivity on Sign Up button click
        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser(LoginRequest loginRequest) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<LoginResponse> call = apiService.loginUser(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    String token = response.body().getData().getToken();

                    if (token != null) {
                        // Save token in SharedPreferences (or other secure storage)
                        getSharedPreferences("auth_prefs", MODE_PRIVATE)
                                .edit()
                                .putString("jwt_token", token)
                                .apply();

                        // Show success popup message
                        CustomDialog.showDialog(LoginActivity.this, true, "Success", "Login Successful. Redirecting to Home.");

                        // Show success notification
                        showNotification("Login Successful", "You have successfully logged in to ShopEase.");

                        // Navigate to HomeActivity after a short delay (2 seconds)
                        loginButton.postDelayed(() -> {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish(); // Close login activity to prevent going back to it
                        }, 2000); // Delay of 2 seconds
                    } else {
                        CustomDialog.showDialog(LoginActivity.this, false, "Error", "Login failed. Please try again.");
                        showNotification("Login Failed", "Login failed. Please try again.");
                    }
                } else {
                    // Handle errors like incorrect credentials
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e(TAG, "Error: " + errorBody);
                        CustomDialog.showDialog(LoginActivity.this, false, "Error", "Invalid credentials. Please try again.");
                        showNotification("Login Failed", "Invalid credentials. Please try again.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        CustomDialog.showDialog(LoginActivity.this, false, "Error", "An unexpected error occurred.");
                        showNotification("Login Failed", "An unexpected error occurred.");
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Handle network or API error
                Log.e(TAG, "Error: " + t.getMessage());
                CustomDialog.showDialog(LoginActivity.this, false, "Error", "Network error occurred. Please check your connection.");
                showNotification("Network Error", "Network error occurred. Please check your connection.");
            }
        });
    }


    private void showNotification(String title, String message) {
        // Create notification channel for Android O+ (API level 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "ShopEase Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Notifications for ShopEase");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Build and display the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}
