package com.example.shopease.activities;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.shopease.R;
import com.example.shopease.fragments.CartFragment;
import com.example.shopease.fragments.HomeFragment;
import com.example.shopease.fragments.OrdersFragment;
import com.example.shopease.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set default fragment to HomeFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container, new HomeFragment()).commit();

        // Handle navigation item selection and switch between fragments
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Get the ID of the clicked navigation item
            int itemId = item.getItemId();

            // Determine which fragment to load based on the selected item
            if (itemId == R.id.navigation_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.navigation_cart) {
                selectedFragment = new CartFragment();
            } else if (itemId == R.id.navigation_orders) {
                selectedFragment = new OrdersFragment();
            } else if (itemId == R.id.navigation_profile) {
                selectedFragment = new ProfileFragment();
            }

            // Replace the current fragment with the selected fragment
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container, selectedFragment).commit();
            }

            return true;
        });

        // Call method to handle back press
        handleBackPress();
    }

    /**
     * Handles back press event and shows a confirmation dialog before exiting the application.
     */
    private void handleBackPress() {
        // Register a callback to handle the back press event
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Show a confirmation dialog when back button is pressed
                new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Exit Application")
                        .setMessage("Are you sure you want to exit the application?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Exit the application and remove it from the back stack
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Dismiss the dialog and remain in the app
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        };

        // Add the callback to the OnBackPressedDispatcher for the current activity
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}
