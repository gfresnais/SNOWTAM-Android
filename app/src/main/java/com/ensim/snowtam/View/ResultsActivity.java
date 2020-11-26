package com.ensim.snowtam.View;

import android.content.Intent;
import android.os.Bundle;

import com.ensim.snowtam.Controller.Controller;
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

        // Tabbed Activity
        // Sections
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), airfields, controller.getRealtimeNotams(airfields));

        // Pager view
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        // Tab Layout
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}