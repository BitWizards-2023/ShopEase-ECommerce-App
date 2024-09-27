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
import com.example.shopease.models.RegisterRequest;

public class BasicDetailsFragment extends Fragment {

    private EditText usernameInput, emailInput, firstNameInput, lastNameInput, passwordInput, phoneNumberInput;

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

        return view;
    }

    // You can also create a method to fetch the data from these fields
    public RegisterRequest getRegisterRequest() {
        String userName = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String phoneNumber = phoneNumberInput.getText().toString();
        String password = passwordInput.getText().toString();
        return new RegisterRequest(email, password, userName, firstName, lastName, "user", null, phoneNumber);
    }
}
