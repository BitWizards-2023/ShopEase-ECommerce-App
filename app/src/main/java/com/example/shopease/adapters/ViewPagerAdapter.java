package com.example.shopease.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopease.fragments.AdditionalDetailsFragment;
import com.example.shopease.fragments.BasicDetailsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BasicDetailsFragment();
            case 1:
                return new AdditionalDetailsFragment();
            default:
                return new BasicDetailsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Two tabs: Basic and Additional Details
    }
}
