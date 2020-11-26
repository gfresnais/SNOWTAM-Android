package com.ensim.snowtam.Controller;

import android.content.res.AssetManager;

import com.ensim.snowtam.Model.LocationIndicator;
import com.ensim.snowtam.Model.Model;

public class Controller  {
    private Model model;

    public Controller(AssetManager am) {
        model = new Model(am);
    }

    /**
     * Returns the latitude
     * @return
     */
    public Double getLatitude() {
        return model.getLocationIndicator().getLatitude();
    }

    /**
     * Returns the longitude
     * @return
     */
    public Double getLongitude() {
        return model.getLocationIndicator().getLongitude();
    }

    public void testRealtimeNotam() {
        System.out.println(model.getRealtimeNotam("UUEE").toString());
    }
}
