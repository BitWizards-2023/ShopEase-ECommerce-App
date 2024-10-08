/*

File: AdditionalDetailsFragment.java
Description: Fragment for displaying or collecting additional user details, such as address and phone number. It can be used in both profile mode (view-only) and input mode (data collection).
Author: Senula Nanayakkara
Date: 2024/09/28

*/
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
    private boolean isProfileMode = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additional_details, container, false);

        // Initialize the input fields
        streetAddressInput = view.findViewById(R.id.street_address);
        cityInput = view.findViewById(R.id.city);
        stateInput = view.findViewById(R.id.state);
        postalCodeInput = view.findViewById(R.id.postal_code);
        countryInput = view.findViewById(R.id.country);

        // If arguments are passed, populate the fields with data
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

    /**
     * Sets profile mode for the fragment, indicating whether the fragment is being used for displaying data or gathering input.
     * @param isProfileMode boolean to set the mode
     */
    public void setProfileMode(boolean isProfileMode) {
        this.isProfileMode = isProfileMode;
    }

    /**
     * Fetches the address input from the form to be used in the signup process or other purposes.
     * @return Address object containing the details entered in the form
     */
    public Address getAddress() {
        // Create a new Address object and return empty strings for any fields that are null
        return new Address(
                streetAddressInput != null ? streetAddressInput.getText().toString() : "",
                cityInput != null ? cityInput.getText().toString() : "",
                stateInput != null ? stateInput.getText().toString() : "",
                postalCodeInput != null ? postalCodeInput.getText().toString() : "",
                countryInput != null ? countryInput.getText().toString() : ""
        );
    }
}
