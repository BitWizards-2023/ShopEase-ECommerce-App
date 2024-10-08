/*

File: ProfileViewPagerAdapter.java
Description: Adapter for managing fragments in a ViewPager2 for displaying user profile details. It switches between BasicDetailsFragment and AdditionalDetailsFragment and passes arguments to each fragment.
Author: Senula Nanayakkara
Date: 2024/09/30

*/

package com.example.shopease.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopease.fragments.AdditionalDetailsFragment;
import com.example.shopease.fragments.BasicDetailsFragment;

public class ProfileViewPagerAdapter extends FragmentStateAdapter {

    private BasicDetailsFragment basicDetailsFragment;
    private AdditionalDetailsFragment additionalDetailsFragment;
    private Bundle basicDetailsArguments;
    private Bundle additionalDetailsArguments;

    // Constructor to initialize the fragments and parent fragment activity
    public ProfileViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, BasicDetailsFragment basicDetailsFragment, AdditionalDetailsFragment additionalDetailsFragment) {
        super(fragmentActivity);
        this.basicDetailsFragment = basicDetailsFragment;
        this.additionalDetailsFragment = additionalDetailsFragment;
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
                // Pass arguments to BasicDetailsFragment if available
                if (basicDetailsArguments != null) {
                    basicDetailsFragment.setArguments(basicDetailsArguments);
                }
                return basicDetailsFragment;
            case 1:
                // Pass arguments to AdditionalDetailsFragment if available
                if (additionalDetailsArguments != null) {
                    additionalDetailsFragment.setArguments(additionalDetailsArguments);
                }
                return additionalDetailsFragment;
            default:
                return new Fragment();  // Fallback case, should never happen
        }
    }

    /**
     * Returns the number of fragments (tabs) in the ViewPager.
     */
    @Override
    public int getItemCount() {
        return 2;
    }

    /**
     * Sets the arguments (data) for BasicDetailsFragment.
     */
    public void setBasicDetailsArguments(Bundle arguments) {
        this.basicDetailsArguments = arguments;
    }

    /**
     * Sets the arguments (data) for AdditionalDetailsFragment.
     */
    public void setAdditionalDetailsArguments(Bundle arguments) {
        this.additionalDetailsArguments = arguments;
    }

    /**
     * Retrieves the BasicDetailsFragment instance.
     */
    public BasicDetailsFragment getBasicDetailsFragment() {
        return basicDetailsFragment;
    }

    /**
     * Retrieves the AdditionalDetailsFragment instance.
     */
    public AdditionalDetailsFragment getAdditionalDetailsFragment() {
        return additionalDetailsFragment;
    }

    /**
     * Retrieves the fragment at the given position.
     */
    public Fragment getFragment(int position) {
        if (position == 0) {
            return basicDetailsFragment;
        } else if (position == 1) {
            return additionalDetailsFragment;
        }
        return null;
    }
}

