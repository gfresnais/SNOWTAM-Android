package com.ensim.snowtam.View.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ensim.snowtam.Model.FormattedNotam;
import com.ensim.snowtam.Model.LocationIndicator;
import com.ensim.snowtam.Model.RealtimeNotam;
import com.ensim.snowtam.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    private final List<FormattedNotam> mRtn;
    private final LocationIndicator mLoc;
    private final List<FormattedNotam> mDec;
    private List<FormattedNotam> mRecList;

    private ResultsRecyclerViewAdapter adapter;
    private Button btnDecode;

    private boolean isDecoded = false;

    /**
     * Creates an ItemFragment with an argument
     */
    public ItemFragment(List<FormattedNotam> rtn, LocationIndicator loc, List<FormattedNotam> dec) {
        mRtn = rtn;
        mLoc = loc;
        mDec = dec;
        mRecList = new ArrayList<>(mRtn);
    }

    public static ItemFragment newInstance(List<FormattedNotam> rtn, LocationIndicator loc, List<FormattedNotam> dec) {
        return new ItemFragment(rtn, loc, dec);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results_item_list, container, false);


        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new ResultsRecyclerViewAdapter(mRecList, context);

        // Sets the adapter with the Notams list
        recyclerView.setAdapter(adapter);

        // Button Decode
        btnDecode =  view.findViewById(R.id.btnDecode);
        btnDecode.setOnClickListener(this::onClickDecode);


        // MapView
        MapView mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(googleMap -> {
            LatLng latLng = new LatLng(48, 0.20);
            String title = getString(R.string.app_name);

            // If there's a LocationIndicator existing
            if( mLoc != null ) {
                latLng = mLoc.getLatLng();
                title = mLoc.getLocationName();
            }

            googleMap.addMarker( new MarkerOptions().position(latLng).title(title) );

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
        });
        mapView.onResume();

        return view;
    }

    /**
     * Change the data used by the RecyclerView
     * @param v
     */
    public void onClickDecode(View v) {
        Log.i("ItemFragment", "Decode");
        mRecList.clear();
        if( !isDecoded ) {
            mRecList.addAll(mDec);
            btnDecode.setText(getString(R.string.btnEncode));
        } else {
            mRecList.addAll(mRtn);
            btnDecode.setText(getString(R.string.btnDecode));
        }
        isDecoded = !isDecoded;
        adapter.notifyDataSetChanged();
    }
}