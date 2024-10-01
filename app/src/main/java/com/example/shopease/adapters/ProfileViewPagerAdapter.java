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

    public ProfileViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, BasicDetailsFragment basicDetailsFragment, AdditionalDetailsFragment additionalDetailsFragment) {
        super(fragmentActivity);
        this.basicDetailsFragment = basicDetailsFragment;
        this.additionalDetailsFragment = additionalDetailsFragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                // Pass arguments to BasicDetailsFragment
                if (basicDetailsArguments != null) {
                    basicDetailsFragment.setArguments(basicDetailsArguments);
                }
                return basicDetailsFragment;
            case 1:
                // Pass arguments to AdditionalDetailsFragment
                if (additionalDetailsArguments != null) {
                    additionalDetailsFragment.setArguments(additionalDetailsArguments);
                }
                return additionalDetailsFragment;
            default:
                return new Fragment();  // Fallback, should never happen
        }
    }

    @Override
    public int getItemCount() {
        return 2;  // Two tabs: Basic Details and Additional Details
    }

    // Methods to set arguments for Basic and Additional details fragments
    public void setBasicDetailsArguments(Bundle arguments) {
        this.basicDetailsArguments = arguments;
    }

    public void setAdditionalDetailsArguments(Bundle arguments) {
        this.additionalDetailsArguments = arguments;
    }

    // Method to retrieve the BasicDetailsFragment
    public BasicDetailsFragment getBasicDetailsFragment() {
        return basicDetailsFragment;
    }

    // Method to retrieve the AdditionalDetailsFragment
    public AdditionalDetailsFragment getAdditionalDetailsFragment() {
        return additionalDetailsFragment;
    }

    public Fragment getFragment(int position) {
        if (position == 0) {
            return basicDetailsFragment;
        } else if (position == 1) {
            return additionalDetailsFragment;
        }
        return null;
    }
}
