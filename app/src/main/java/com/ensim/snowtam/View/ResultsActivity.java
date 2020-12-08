package com.ensim.snowtam.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.ensim.snowtam.Controller.Controller;
import com.ensim.snowtam.Model.RealtimeNotam;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.ensim.snowtam.R;

import com.ensim.snowtam.View.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        // Initialize the controller
        Controller controller = new Controller(getAssets(), this);

        // Get the search airfields
        Intent intent = getIntent();
        List<String> airfields = new ArrayList<>();
        airfields.add((String) intent.getSerializableExtra("airfield1"));
        airfields.add((String) intent.getSerializableExtra("airfield2"));
        airfields.add((String) intent.getSerializableExtra("airfield3"));
        airfields.add((String) intent.getSerializableExtra("airfield4"));

        // Prevents null objects from being in the list
        airfields.removeAll(Collections.singleton(null));
        // Prevents empty String from being in the list
        airfields.removeAll(Collections.singleton(""));
        // Sets all to uppercase
        airfields.replaceAll(String::toUpperCase);

        // Sends the request first
        //controller.sendRealtimeNotamRequest(airfields);

        // Creates a progressDialog to make the user wait
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_results));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgress(0);
        progressDialog.show();

        // Handler to create a loading screen and wait for the request to be fully done
        Handler handler = new Handler();
        // Wait for 3 seconds
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.w("Results_Act_Wait", "Is waiting");

                /* Tabbed Activity */
                // Sections
                List<RealtimeNotam> rtn = controller.getLocalRealtimeNotams(airfields);
                SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), airfields, rtn);

                // Pager view
                ViewPager viewPager = findViewById(R.id.view_pager);
                viewPager.setAdapter(sectionsPagerAdapter);

                // Tab Layout
                TabLayout tabs = findViewById(R.id.tabs);
                tabs.setupWithViewPager(viewPager);

                // MapView
                MapView mapView = findViewById(R.id.mapView);
                mapView.onCreate(savedInstanceState);
                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        LatLng lemans = new LatLng(48, 0.20);
                        googleMap.addMarker(new MarkerOptions().position(lemans).title("Marker in Le Mans"));

                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lemans, 12));
                    }
                });
                mapView.onResume();

                progressDialog.dismiss();
            }
        }, 3000);
    }
}