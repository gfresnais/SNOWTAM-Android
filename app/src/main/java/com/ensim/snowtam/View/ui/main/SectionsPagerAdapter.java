package com.ensim.snowtam.View.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ensim.snowtam.Model.RealtimeNotam;
import com.ensim.snowtam.R;
import com.ensim.snowtam.View.fragment.ItemFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final List<String> TAB_TITLES;

    private final List<RealtimeNotam> mRtn_list;

    public SectionsPagerAdapter(FragmentManager fm, List<String> airfields, List<RealtimeNotam> rtn_list) {
        super(fm);

        TAB_TITLES = airfields;
        mRtn_list = rtn_list;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return ItemFragment.newInstance(mRtn_list.get(position));
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