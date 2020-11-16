package com.ensim.snowtam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ensim.snowtam.Controller.Controller;

public class MainActivity extends AppCompatActivity {

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new Controller(getAssets());
    }
}