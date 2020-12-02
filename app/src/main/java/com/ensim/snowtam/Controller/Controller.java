package com.ensim.snowtam.Controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import com.ensim.snowtam.Model.LocationIndicator;
import com.ensim.snowtam.Model.Model;
import com.ensim.snowtam.Model.RealtimeNotam;

import java.util.ArrayList;
import java.util.List;

public class Controller  {
    private final Model model;

    private final Context context;

    public Controller(AssetManager am, Context context) {
        model = new Model(am, context);
        this.context = context;
    }

    /**
     * Returns a LocationIndicator Object
     * @param location
     * @return
     */
    public LocationIndicator getLocationIndicator(String location) {
        return model.getLocationIndicator(location);
    }

    /**
     * Returns the latitude of a given location (ICAO code)
     * @param location
     * @return
     */
    public Double getLatitude(String location) {
        return model.getLocationIndicator(location).getLatitude();
    }

    /**
     * Returns the longitude of a given location (ICAO Code)
     * @param location
     * @return
     */
    public Double getLongitude(String location) {
        return model.getLocationIndicator(location).getLongitude();
    }


    /**
     * Orders requests from an airfield list
     * @param airfields
     */
    public void sendRealtimeNotamRequest(List<String> airfields) {
        for (String location:
                airfields) {
            model.requestRealtimeNotam(location);
        }
    }

    /**
     * Returns a list of RealtimeNotam from a list of airfields (String)
     * @param airfields
     * @return
     */
    public List<RealtimeNotam> getRealtimeNotams(List<String> airfields) {
        List<RealtimeNotam> rtn_list = new ArrayList<>();
        for (String location:
             airfields) {
            rtn_list.add(model.getRealtimeNotam(location));
        }
        return rtn_list;
    }
}
