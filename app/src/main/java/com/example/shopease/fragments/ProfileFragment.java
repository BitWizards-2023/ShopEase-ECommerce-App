/*

File: ProfileFragment.java
Description: Fragment for displaying and updating the user's profile. It fetches the profile data, displays it in a ViewPager2, and allows the user to update their profile information.
Author: Senula Nanayakkara
Date: 2024/09/29

*/
package com.example.shopease.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.shopease.R;
import com.example.shopease.adapters.ProfileViewPagerAdapter;
import com.example.shopease.models.Address;
import com.example.shopease.models.UpdateProfileRequest;
import com.example.shopease.models.UpdateProfileResponse;
import com.example.shopease.models.UserProfileResponse;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;
import com.example.shopease.utils.CustomDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment"; // Tag for logging
    private TabLayout tabLayout; // Tab layout for the profile page
    private ViewPager2 viewPager; // ViewPager2 for switching between profile tabs
    private ImageView profileImageView; // Profile image view
    private TextView usernameText; // Text view for displaying the username
    private ProfileViewPagerAdapter adapter; // Adapter for the ViewPager2
    private Button updateProfileButton; // Button to update the profile
    private String userId; // ID of the logged-in user

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize UI components
        profileImageView = view.findViewById(R.id.profile_image);
        usernameText = view.findViewById(R.id.username_text);
        tabLayout = view.findViewById(R.id.profile_tab_layout);
        viewPager = view.findViewById(R.id.profile_view_pager);
        updateProfileButton = view.findViewById(R.id.update_profile_button);

        Log.e(TAG, "ProfileFragment view created.");

        // Fetch profile details from the backend
        fetchUserProfile();

        // Handle the update profile button click
        updateProfileButton.setOnClickListener(view1 -> updateUserProfile());

        return view;
    }

    /**
     * Fetches the user profile data from the backend using the stored JWT token.
     */
    private void fetchUserProfile() {
        Log.e(TAG, "Attempting to fetch user profile...");

        // Retrieve JWT from SharedPreferences (or any other secure storage)
        Context context = getContext();
        String token = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .getString("jwt_token", null);

        if (token != null) {
            String authHeader = "Bearer " + token;

            Log.e(TAG, "JWT token found: " + token);

            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            Call<UserProfileResponse> call = apiService.getUserProfile(authHeader);

            // Make an API call to fetch the user's profile data
            call.enqueue(new Callback<UserProfileResponse>() {
                @Override
                public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        UserProfileResponse.Data profileData = response.body().getData();
                        userId = profileData.getId();

                        Log.e(TAG, "Profile fetched: " + profileData.getUsername());

                        // Set the profile image and username
                        usernameText.setText(profileData.getUsername());

                        // Load the profile image using Glide
                        Glide.with(getContext())
                                .load(profileData.getProfilePic())
                                .placeholder(R.drawable.ic_default_profile)
                                .into(profileImageView);

                        Log.e(TAG, "Profile image and username set.");

                        // Set up ViewPager2 with the profile data passed to fragments
                        setupViewPager(profileData);

                        Log.d(TAG, "Profile fetched successfully");
                    } else {
                        Log.e(TAG, "Failed to fetch profile. Response code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                    Log.e(TAG, "Error: " + t.getMessage());
                    Toast.makeText(getContext(), "Error fetching profile", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.e(TAG, "JWT token is missing. User not logged in.");
            Toast.makeText(getContext(), "User is not logged in", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Updates the user profile using data from the BasicDetailsFragment and AdditionalDetailsFragment.
     */
    private void updateUserProfile() {
        // Get the correct fragment instances from the adapter
        BasicDetailsFragment basicDetailsFragment = (BasicDetailsFragment) adapter.getFragment(0);
        AdditionalDetailsFragment additionalDetailsFragment = (AdditionalDetailsFragment) adapter.getFragment(1);

        if (basicDetailsFragment == null || additionalDetailsFragment == null) {
            Log.e(TAG, "Error: Fragments are null.");
            return;
        }

        // Get update request from basic details fragment
        UpdateProfileRequest updateRequest = basicDetailsFragment.getUpdateRequest();

        // Log the phone number before making the API call
        Log.e("ProfileFragment", "Phone Number in Update Request: " + updateRequest.getPhoneNumber());

        // Get address from additional details fragment
        Address address = additionalDetailsFragment.getAddress();
        updateRequest.setAddress(address);  // Add the address to the request

        // Retrieve JWT from SharedPreferences (or any other secure storage)
        Context context = getContext();
        String token = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .getString("jwt_token", null);

        if (token != null) {
            String authHeader = "Bearer " + token;

            Log.e(TAG, "JWT token found: " + token);
            // Call the API to update the profile
            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            Call<UpdateProfileResponse> call = apiService.updateUserProfile(authHeader, updateRequest);

            // Make an API call to update the profile data
            call.enqueue(new Callback<UpdateProfileResponse>() {
                @Override
                public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        CustomDialog.showDialog(getContext(), true, "Profile update success!", response.body().getMessage());
                        Log.e(TAG, "Profile updated successfully.");
                    } else {
                        CustomDialog.showDialog(getContext(), false, "Profile update failed!", response.message());
                        Log.e(TAG, "Profile update failed. Response: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                    CustomDialog.showDialog(getContext(), false, "Profile update failed!", "Profile update failed! Please try again.");
                    Log.e(TAG, "Error: " + t.getMessage());
                }
            });
        } else {
            Log.e(TAG, "JWT token is missing. User not logged in.");
            Toast.makeText(getContext(), "User is not logged in", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sets up the ViewPager2 with fragments for basic details and additional details using the fetched profile data.
     *
     * @param profileData The profile data fetched from the backend API.
     */
    private void setupViewPager(UserProfileResponse.Data profileData) {
        Log.e(TAG, "Setting up ViewPager with profile data...");

        // Prepare data for BasicDetailsFragment
        Bundle basicDetailsBundle = new Bundle();
        basicDetailsBundle.putString("username", profileData.getUsername());
        basicDetailsBundle.putString("email", profileData.getEmail());
        basicDetailsBundle.putString("firstName", profileData.getFirstName());
        basicDetailsBundle.putString("lastName", profileData.getLastName());
        basicDetailsBundle.putString("phoneNumber", profileData.getPhoneNumber());

        Log.e(TAG, "Basic details bundle created: " + basicDetailsBundle.toString());

        // Prepare data for AdditionalDetailsFragment
        Bundle additionalDetailsBundle = new Bundle();
        additionalDetailsBundle.putString("street", profileData.getAddress().getStreet());
        additionalDetailsBundle.putString("city", profileData.getAddress().getCity());
        additionalDetailsBundle.putString("state", profileData.getAddress().getState());
        additionalDetailsBundle.putString("postalCode", profileData.getAddress().getPostalCode());
        additionalDetailsBundle.putString("country", profileData.getAddress().getCountry());

        Log.e(TAG, "Additional details bundle created: " + additionalDetailsBundle.toString());

        // Create the fragments and set arguments
        BasicDetailsFragment basicDetailsFragment = new BasicDetailsFragment();
        basicDetailsFragment.setArguments(basicDetailsBundle);

        AdditionalDetailsFragment additionalDetailsFragment = new AdditionalDetailsFragment();
        additionalDetailsFragment.setArguments(additionalDetailsBundle);

        Log.e(TAG, "Fragments created and arguments set.");

        // Set up ViewPager2 with an adapter
        adapter = new ProfileViewPagerAdapter(requireActivity(), basicDetailsFragment, additionalDetailsFragment);
        viewPager.setAdapter(adapter);

        // Bind TabLayout with ViewPager2
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

        Log.e(TAG, "ViewPager setup complete.");
    }
}
