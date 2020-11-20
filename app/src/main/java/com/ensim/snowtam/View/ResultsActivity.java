package com.ensim.snowtam.View;

import android.content.Intent;
import android.os.Bundle;

import com.ensim.snowtam.Controller.Controller;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ensim.snowtam.R;

import com.ensim.snowtam.View.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private Controller controller;

    private List<String> airfields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get the search airfields
        Intent intent = getIntent();
        airfields = new ArrayList<>();
        airfields.add((String) intent.getSerializableExtra("airfield1"));
        airfields.add((String) intent.getSerializableExtra("airfield2"));
        airfields.add((String) intent.getSerializableExtra("airfield3"));
        airfields.add((String) intent.getSerializableExtra("airfield4"));


        // Tabbed Activity
        // Sections
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), airfields);

        // Pager view
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        // Tab Layout
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        controller = new Controller(getAssets());
    }
}