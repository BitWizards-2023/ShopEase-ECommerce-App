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
    private boolean isProfileMode = false;  // Whether we are displaying profile info or not

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_details, container, false);

        // Initialize the input fields
        usernameInput = view.findViewById(R.id.username);
        emailInput = view.findViewById(R.id.email);
        firstNameInput = view.findViewById(R.id.first_name);
        lastNameInput = view.findViewById(R.id.last_name);
        phoneNumberInput = view.findViewById(R.id.phone_number);
        passwordInput = view.findViewById(R.id.password);

        // Disable password field when in profile mode
        if (isProfileMode) {
            passwordInput.setVisibility(View.GONE);  // Hide the password field when displaying profile
        }

        // Check if arguments exist and set the fields
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

    // Method to set profile mode for displaying data instead of gathering input
    public void setProfileMode(boolean isProfileMode) {
        this.isProfileMode = isProfileMode;
    }

    // Use this method to fetch data for signup
    public RegisterRequest getRegisterRequest() {
        if (isProfileMode) {
            return null;  // No data collection when in profile mode
        }

        String userName = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String phoneNumber = phoneNumberInput.getText().toString();
        String password = passwordInput.getText().toString();
        String role = "User";

        return new RegisterRequest(email, password, userName, firstName, lastName, role, null, phoneNumber);
    }

    // Create method to get the update request with all necessary details
    // Create method to get the update request with all necessary details
    public UpdateProfileRequest getUpdateRequest() {
        // Log the current field values to check what's being captured
        Log.e("BasicDetailsFragment", "Email: " + (emailInput != null ? emailInput.getText().toString() : "null"));
        Log.e("BasicDetailsFragment", "First Name: " + (firstNameInput != null ? firstNameInput.getText().toString() : "null"));
        Log.e("BasicDetailsFragment", "Last Name: " + (lastNameInput != null ? lastNameInput.getText().toString() : "null"));
        Log.e("BasicDetailsFragment", "Phone Number: " + (phoneNumberInput != null ? phoneNumberInput.getText().toString() : "null"));

        // Create a new UpdateProfileRequest and pass empty strings for any fields that are null
        UpdateProfileRequest updateRequest = new UpdateProfileRequest();
        updateRequest.setEmail(emailInput != null ? emailInput.getText().toString() : "");
        updateRequest.setFirstName(firstNameInput != null ? firstNameInput.getText().toString() : "");
        updateRequest.setLastName(lastNameInput != null ? lastNameInput.getText().toString() : "");
        updateRequest.setPhoneNumber(phoneNumberInput != null ? phoneNumberInput.getText().toString() : "");

        return updateRequest;
    }
}
