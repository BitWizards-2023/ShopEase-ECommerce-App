package com.example.shopease.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopease.R;
import com.example.shopease.models.Address;

public class AdditionalDetailsFragment extends Fragment {

    private EditText phoneInput, streetAddressInput, cityInput, stateInput, postalCodeInput, countryInput;
    private boolean isProfileMode = false;  // Whether we are displaying profile info or not

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additional_details, container, false);

        // Initialize the input fields
        phoneInput = view.findViewById(R.id.phone_number);
        streetAddressInput = view.findViewById(R.id.street_address);
        cityInput = view.findViewById(R.id.city);
        stateInput = view.findViewById(R.id.state);
        postalCodeInput = view.findViewById(R.id.postal_code);
        countryInput = view.findViewById(R.id.country);

        // Check if arguments exist and set the fields
        Bundle arguments = getArguments();
        if (arguments != null) {
            streetAddressInput.setText(arguments.getString("street", ""));
            cityInput.setText(arguments.getString("city", ""));
            stateInput.setText(arguments.getString("state", ""));
            postalCodeInput.setText(arguments.getString("postalCode", ""));
            countryInput.setText(arguments.getString("country", ""));
        }

        return view;
    }

    // Method to set profile mode for displaying data instead of gathering input
    public void setProfileMode(boolean isProfileMode) {
        this.isProfileMode = isProfileMode;
    }

    // Use this method to fetch data for signup
    public Address getAddress() {
        // Create a new Address and pass empty strings for any fields that are null
        return new Address(
                streetAddressInput != null ? streetAddressInput.getText().toString() : "",
                cityInput != null ? cityInput.getText().toString() : "",
                stateInput != null ? stateInput.getText().toString() : "",
                postalCodeInput != null ? postalCodeInput.getText().toString() : "",
                countryInput != null ? countryInput.getText().toString() : ""
        );
    }
}
