/*

File: HomeActivity.java
Description: Handles the main home screen functionality, including navigation between different fragments (Home, Cart, Orders, Profile). Also manages the back press to confirm app exit.
Author: Senula Nanayakkara
Date: 2024/09/25

*/
package com.example.shopease.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopease.R;
import com.example.shopease.adapters.ViewPagerAdapter;
import com.example.shopease.fragments.AdditionalDetailsFragment;
import com.example.shopease.fragments.BasicDetailsFragment;
import com.example.shopease.models.Address;
import com.example.shopease.models.RegisterRequest;
import com.example.shopease.models.RegisterResponse;
import com.example.shopease.models.UploadResponse;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;
import com.example.shopease.utils.CustomDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private String uploadedProfilePicUrl; // URL for the uploaded image

    private Button signupButton, loginButton;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ImageView profileImageView, editIcon;
    private String uploadedImageUrl = "";

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
        profileImageView = findViewById(R.id.profile_image);
        editIcon = findViewById(R.id.edit_icon);

        // Set up the adapter for ViewPager2
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Bind the TabLayout and ViewPager2 for tab navigation
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

        // Handle image upload when clicking on the edit icon
        editIcon.setOnClickListener(view -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PICK_IMAGE);
        });

        // Navigate to LoginActivity on Login button click
        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Handle signup button click and registration
        signupButton.setOnClickListener(view -> {
            BasicDetailsFragment basicDetailsFragment = (BasicDetailsFragment) getSupportFragmentManager().findFragmentByTag("f0");
            AdditionalDetailsFragment additionalDetailsFragment = (AdditionalDetailsFragment) getSupportFragmentManager().findFragmentByTag("f1");

            if (basicDetailsFragment != null && additionalDetailsFragment != null) {
                RegisterRequest request = basicDetailsFragment.getRegisterRequest();
                if (request == null) {
                    // Stop the registration if email or password is missing
                    return;
                }

                Address address = additionalDetailsFragment.getAddress();
                request.setRole("");
                request.setAddress(address); // Add the address to the request
                request.setProfile_pic(uploadedImageUrl); // Add the profile pic URL to the request

                // Call the API to register the user
                registerUser(request);
            }
        });

    }

    /**
     * Called when the user selects an image from the gallery.
     *
     * @param requestCode - The request code for picking the image
     * @param resultCode - The result code indicating success or failure
     * @param data - The intent containing the selected image data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();

            // Set the selected image in the ImageView
            profileImageView.setImageURI(selectedImage);

            // Upload the selected image to the server
            try {
                String filePath = getFilePathFromUri(selectedImage);
                uploadProfileImage(filePath);
            } catch (Exception e) {
                Toast.makeText(SignupActivity.this, "Failed to upload image.", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    /**
     * Helper method to retrieve the file path from a URI.
     *
     * @param uri - The URI of the selected image
     * @return String - The file path of the selected image
     * @throws IOException - If the file path cannot be retrieved
     */
    private String getFilePathFromUri(Uri uri) throws IOException {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String filePath = cursor.getString(column_index);
            cursor.close();
            return filePath;
        }
        throw new IOException("Unable to retrieve the file path.");
    }

    /**
     * Uploads the selected profile image to the server.
     *
     * @param filePath - The file path of the selected image
     */
    private void uploadProfileImage(String filePath) {
        File imageFile = new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", imageFile.getName(), requestFile);

        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<UploadResponse> call = apiService.uploadImage(body);

        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    uploadedImageUrl = response.body().getFileUrl();  // Store the uploaded image URL
                    Log.e("SignupActivity", "Image URL: " + uploadedImageUrl);
                    Toast.makeText(SignupActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Image upload failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Registers the user by sending the registration request to the backend API.
     *
     * @param request - The RegisterRequest containing user details
     */
    private void registerUser(RegisterRequest request) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<RegisterResponse> call = apiService.registerUser(request);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    // Show success dialog
                    CustomDialog.showDialog(SignupActivity.this, true, "Success", "Registration Successful. Redirecting to Login.");

                    // Navigate to LoginActivity after a short delay
                    signupButton.postDelayed(() -> {
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Close SignupActivity to prevent navigation back
                    }, 2000); // Delay of 2 seconds
                } else {
                    // Handle registration failure
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("SignupActivity", "Registration error: " + errorBody);

                        JSONObject jsonObject = new JSONObject(errorBody);
                        String errorMessage = jsonObject.getString("message");
                        CustomDialog.showDialog(SignupActivity.this, false, "Error", errorMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CustomDialog.showDialog(SignupActivity.this, false, "Error", "An unexpected error occurred.");
                    }
                }
            }


            // Handle network or API failure
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e("SignupActivity", "Error: " + t.getMessage());
                Toast.makeText(SignupActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
