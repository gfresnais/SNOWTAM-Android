package com.ensim.snowtam.Controller;

import android.content.res.AssetManager;

import com.ensim.snowtam.Model.Model;

public class Controller  {
    private Model model;

    public Controller(AssetManager am) {
        model = new Model(am);
        model.getLocationIndicator();
    }
}
