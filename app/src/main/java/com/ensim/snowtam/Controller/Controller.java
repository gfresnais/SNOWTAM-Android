package com.ensim.snowtam.Controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import com.ensim.snowtam.Model.FormattedNotam;
import com.ensim.snowtam.Model.LocationIndicator;
import com.ensim.snowtam.Model.Model;
import com.ensim.snowtam.Model.RealtimeNotam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller  {
    private final Model model;

    private final Context context;

    public Controller(AssetManager am, Context context) {
        model = new Model(am, context);
        this.context = context;
    }

    /**********************/
    /* LOCATION INDICATOR */
    /*********************/

    /**
     * Orders LocationIndicator requests from an airfield list
     * @param airfields
     */
    public void sendLocationIndicatorRequest(List<String> airfields) {
        for (String location:
                airfields) {
            model.requestLocationIndicator(location);
        }
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


    /******************/
    /* REMOTE LOADING */
    /******************/

    /**
     * Orders RealtimeNotam requests from an airfield list
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

    /**
     * Returns a list of RealtimeNotam from a list of airfields (String)
     * @param airfields
     * @return
     */
    public Map<Integer, List<FormattedNotam>> getFormattedNotams(List<String> airfields) {
        return createMapFormattedNotams( getRealtimeNotams(airfields) );
    }

    /**
     * Creates a Map of FormattedNotams Lists
     * @param rtn_list
     * @return
     */
    private Map<Integer, List<FormattedNotam>> createMapFormattedNotams(List<RealtimeNotam> rtn_list) {
        // Map which will be returned
        Map<Integer, List<FormattedNotam>> listMap = new HashMap<>();

        for (int i = 0; i < rtn_list.size(); i++) {
            // Get a List of FormattedNotam by calling the model's method
            List<FormattedNotam> formattedNotams = model.getFormattedNotams(rtn_list.get(i));

            // Add the List to the Map
            listMap.putIfAbsent(i, formattedNotams);
        }

        return listMap;
    }

    /*****************/
    /* LOCAL LOADING */
    /*****************/

    /**
     * Returns a list of RealtimeNotam from a list of airfields (String)
     * Used with a local file, for testing purposes
     * @param airfields
     * @return
     */
    public List<RealtimeNotam> getLocalRealtimeNotams(List<String> airfields) {
        List<RealtimeNotam> rtn_list = new ArrayList<>();
        for (String location:
                airfields) {
            model.localRealtimeNotam(location);
            rtn_list.add(model.getRealtimeNotam(location));
        }
        return rtn_list;
    }

    /**
     * Returns a list of RealtimeNotam from a list of airfields (String)
     * Used with a local file, for testing purposes
     * @param airfields
     * @return
     */
    public Map<Integer, List<FormattedNotam>> getLocalFormattedNotams(List<String> airfields) {
        return createMapFormattedNotams( getLocalRealtimeNotams(airfields) );
    }
}
