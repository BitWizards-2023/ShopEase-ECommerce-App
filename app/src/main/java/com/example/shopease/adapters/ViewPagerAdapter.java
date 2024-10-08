/*

File: ViewPagerAdapter.java
Description: Adapter for managing fragments in a ViewPager2. It switches between BasicDetailsFragment and AdditionalDetailsFragment to show different details in each tab.
Author: Senula Nanayakkara
Date: 2024/10/02

*/
package com.example.shopease.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopease.fragments.AdditionalDetailsFragment;
import com.example.shopease.fragments.BasicDetailsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    // Constructor to initialize the adapter with the parent fragment activity
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     * Returns the fragment to display based on the current position (tab).
     * Position 0: BasicDetailsFragment, Position 1: AdditionalDetailsFragment.
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BasicDetailsFragment(); // Display BasicDetailsFragment for tab 1
            case 1:
                return new AdditionalDetailsFragment(); // Display AdditionalDetailsFragment for tab 2
            default:
                return new BasicDetailsFragment(); // Fallback to BasicDetailsFragment if an unexpected position is encountered
        }
    }

    /**
     * Returns the total number of fragments (tabs) in the ViewPager.
     */
    @Override
    public int getItemCount() {
        return 2; // Two tabs: Basic Details and Additional Details
    }
}

