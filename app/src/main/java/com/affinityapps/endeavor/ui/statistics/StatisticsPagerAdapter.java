package com.affinityapps.endeavor.ui.statistics;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class StatisticsPagerAdapter extends FragmentStateAdapter {

    private static final int TAB_ITEM_SIZE = 3;
    private static final String HOURS = "hoursPosition";
    private static final String MILES = "milesPosition";
    private static final String PURCHASES = "purchasesPosition";

    public StatisticsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment fragment;
        Bundle args;

        switch (position) {
            case 0:
                fragment = new MilesGraphFragment();
                args = new Bundle();
                args.putInt(MILES, position + 1);
                fragment.setArguments(args);
                return fragment;
            case 1:
                fragment = new PurchasesGraphFragment();
                args = new Bundle();
                args.putInt(PURCHASES, position + 1);
                fragment.setArguments(args);
                return fragment;
            default:
                fragment = new HoursGraphFragment();
                args = new Bundle();
                args.putInt(HOURS, position + 1);
                fragment.setArguments(args);
                return fragment;
        }
    }

    @Override
    public int getItemCount() {
        return TAB_ITEM_SIZE;
    }
}