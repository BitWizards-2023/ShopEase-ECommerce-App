/*

File: BasicDetailsFragment.java
Description: Fragment for displaying or collecting basic user details like username, email, and password. It can be used in both profile mode (view-only) and input mode (data collection).
Author: Senula Nanayakkara
Date: 2024/09/28

*/
package com.example.shopease.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopease.R;
import com.example.shopease.models.RegisterRequest;
import com.example.shopease.models.UpdateProfileRequest;

public class BasicDetailsFragment extends Fragment {

    private EditText usernameInput, emailInput, firstNameInput, lastNameInput, passwordInput, phoneNumberInput;
    private boolean isProfileMode = false;  // Determines if the fragment is in profile view mode

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_details, container, false);

        // Initialize input fields
        usernameInput = view.findViewById(R.id.username);
        emailInput = view.findViewById(R.id.email);
        firstNameInput = view.findViewById(R.id.first_name);
        lastNameInput = view.findViewById(R.id.last_name);
        phoneNumberInput = view.findViewById(R.id.phone_number);
        passwordInput = view.findViewById(R.id.password);

        // Hide password field if the fragment is in profile mode
        if (isProfileMode) {
            passwordInput.setVisibility(View.GONE);  // Password input is not needed in profile mode
        }

        // Populate fields if arguments are passed to the fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            usernameInput.setText(arguments.getString("username", ""));
            emailInput.setText(arguments.getString("email", ""));
            firstNameInput.setText(arguments.getString("firstName", ""));
            lastNameInput.setText(arguments.getString("lastName", ""));
            phoneNumberInput.setText(arguments.getString("phoneNumber", ""));
        }

        return view;
    }

    /**
     * Sets the profile mode for the fragment. If set to true, the fragment will be used for displaying data, not for collecting input.
     * @param isProfileMode boolean to specify whether the fragment is in profile mode
     */
    public void setProfileMode(boolean isProfileMode) {
        this.isProfileMode = isProfileMode;
    }

    /**
     * Fetches data for registration purposes, excluding the password when in profile mode.
     * @return RegisterRequest object containing the data for user registration
     */
    public RegisterRequest getRegisterRequest() {
        if (isProfileMode) {
            return null;
        }

        // Collect user details for registration
        String userName = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String phoneNumber = phoneNumberInput.getText().toString();
        String password = passwordInput.getText().toString();
        String role = "Customer";

        // Return a new RegisterRequest with the collected data
        return new RegisterRequest(email, password, userName, firstName, lastName, role, null, phoneNumber);
    }

    /**
     * Creates an UpdateProfileRequest object with the updated basic details.
     * @return UpdateProfileRequest object containing updated user details
     */
    public UpdateProfileRequest getUpdateRequest() {

        // Create and return an UpdateProfileRequest with the current input values
        UpdateProfileRequest updateRequest = new UpdateProfileRequest();
        updateRequest.setEmail(emailInput != null ? emailInput.getText().toString() : "");
        updateRequest.setFirstName(firstNameInput != null ? firstNameInput.getText().toString() : "");
        updateRequest.setLastName(lastNameInput != null ? lastNameInput.getText().toString() : "");
        updateRequest.setPhoneNumber(phoneNumberInput != null ? phoneNumberInput.getText().toString() : "");

        return updateRequest;
    }
}
