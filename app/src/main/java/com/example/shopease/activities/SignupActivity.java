package com.example.shopease.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopease.R;
import com.example.shopease.adapters.ViewPagerAdapter;
import com.example.shopease.fragments.AdditionalDetailsFragment;
import com.example.shopease.fragments.BasicDetailsFragment;
import com.example.shopease.models.Address;
import com.example.shopease.models.RegisterRequest;
import com.example.shopease.models.RegisterResponse;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;
import com.example.shopease.utils.CustomDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText userNameInput, emailInput, passwordInput, firstNameInput, lastNameInput, phoneInput, streetAddressInput, cityInput, stateInput, postalCodeInput, countryInput;
    private Button signupButton, loginButton; // Added loginButton
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize UI components
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        signupButton = findViewById(R.id.signup_button);
        loginButton = findViewById(R.id.login_button);


        // Set up the adapter for ViewPager2
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Bind the TabLayout and ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Basic Details");
                    break;
                case 1:
                    tab.setText("Additional Details");
                    break;
            }
        }).attach();


        // Navigate to LoginActivity when login button is clicked
        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        signupButton.setOnClickListener(view -> {
            BasicDetailsFragment basicDetailsFragment = (BasicDetailsFragment) getSupportFragmentManager().findFragmentByTag("f0");
            AdditionalDetailsFragment additionalDetailsFragment = (AdditionalDetailsFragment) getSupportFragmentManager().findFragmentByTag("f1");

            if (basicDetailsFragment != null && additionalDetailsFragment != null) {
                RegisterRequest request = basicDetailsFragment.getRegisterRequest();
                Address address = additionalDetailsFragment.getAddress();
                request.setAddress(address); // Add the address to the request

                // Call the API
                registerUser(request);
            }
        });
    }

    private void registerUser(RegisterRequest request) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<RegisterResponse> call = apiService.registerUser(request);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    // Show success dialog
                    CustomDialog.showDialog(SignupActivity.this, true, "Success", "Registration Successful. Redirecting to Login.");

                    // Navigate to LoginActivity after a short delay (optional, but user-friendly)
                    signupButton.postDelayed(() -> {
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Optional: Prevent user from going back to the signup screen
                    }, 2000); // Delay of 2 seconds
                } else {
                    // Show error dialog
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorBody);
                        String errorMessage = jsonObject.getString("message");
                        CustomDialog.showDialog(SignupActivity.this, false, "Error", errorMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CustomDialog.showDialog(SignupActivity.this, false, "Error", "An unexpected error occurred.");
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // Handle error
                Log.e("SignupActivity", "Error: " + t.getMessage());
                Toast.makeText(SignupActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
