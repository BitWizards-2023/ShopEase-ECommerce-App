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

        return view;
    }

    // You can also create a method to fetch the data from these fields
    public Address getAddress() {
        String phone = phoneInput.getText().toString();
        String streetAddress = streetAddressInput.getText().toString();
        String city = cityInput.getText().toString();
        String state = stateInput.getText().toString();
        String postalCode = postalCodeInput.getText().toString();
        String country = countryInput.getText().toString();

        return new Address(streetAddress, city, state, postalCode, country);
    }
}
