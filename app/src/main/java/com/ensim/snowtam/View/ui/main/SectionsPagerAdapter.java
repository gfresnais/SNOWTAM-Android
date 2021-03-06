package com.ensim.snowtam.View.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ensim.snowtam.Controller.Controller;
import com.ensim.snowtam.Model.FormattedNotam;
import com.ensim.snowtam.Model.LocationIndicator;
import com.ensim.snowtam.Model.RealtimeNotam;
import com.ensim.snowtam.R;
import com.ensim.snowtam.View.fragment.ItemFragment;
import com.google.android.gms.maps.model.LatLng;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final List<String> TAB_TITLES;

    private final Map<Integer, List<FormattedNotam>> mRtn_list;
    private final Map<Integer, List<FormattedNotam>> mDecoded;
    private final Map<Integer, LocationIndicator> mLocMap;

    /**
     * Constructor
     * @param fm
     * @param airfields
     * @param controller
     */
    public SectionsPagerAdapter(FragmentManager fm, List<String> airfields, Controller controller) {
        super(fm);

        TAB_TITLES = airfields;
        mRtn_list = controller.getFormattedNotams(airfields);

        mLocMap = controller.getLocationIndicatorMap(airfields);

        // Creates the Decoded FormattedNotam
        mDecoded = new HashMap<>();
        for (int i = 0; i < mRtn_list.size(); i++) {
            mDecoded.putIfAbsent(i, controller.getDecodedFormattedNotam(mRtn_list.get(i), mLocMap.get(i)));
        }
    }

    /**
     * Returns an ItemFragment
     * @param position
     * @return
     */
    @NotNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return ItemFragment.newInstance(mRtn_list.get(position), mLocMap.get(position), mDecoded.get(position));
    }

    /**
     * Returns the page title
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // Returns the page title from the list
        return TAB_TITLES.get(position);
    }

    /**
     * Returns the number of tabs
     * @return
     */
    @Override
    public int getCount() {
        // Varies from 1 to 4
        return TAB_TITLES.size();
    }
}