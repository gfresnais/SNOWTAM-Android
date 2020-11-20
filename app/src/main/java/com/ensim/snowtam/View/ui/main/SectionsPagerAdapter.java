package com.ensim.snowtam.View.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ensim.snowtam.R;

import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final List<String> TAB_TITLES;

    public SectionsPagerAdapter(FragmentManager fm, List<String> airfields) {
        super(fm);

        TAB_TITLES = airfields;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // Returns the page title from the list
        return TAB_TITLES.get(position);
    }

    @Override
    public int getCount() {
        // Varies from 1 to 4
        return TAB_TITLES.size();
    }
}