package com.ensim.snowtam.Controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import com.ensim.snowtam.Model.FormattedNotam;
import com.ensim.snowtam.Model.LocationIndicator;
import com.ensim.snowtam.Model.Model;
import com.ensim.snowtam.Model.RealtimeNotam;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller  {
    private final Model model;

    /**
     * Constructor
     * @param am
     * @param context
     */
    public Controller(AssetManager am, Context context) {
        model = new Model(am, context);
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
     * Sends a request for a local LocationIndicator object
     * @param airfields
     * @return
     */
    public void sendLocalLocationIndicatorRequest(List<String> airfields) {
        for (String location:
                airfields) {
            model.localLocationIndicator(location);
        }
    }

    /**
     * Returns a LocationIndicator object for a given location
     * @param location
     * @return
     */
    private LocationIndicator getLocationIndicator(String location) {
        return model.getLocationIndicator(location);
    }

    /**
     * Returns a Map of LocationIndicator objects from a list of airfields
     * @param airfields
     * @return
     */
    public Map<Integer, LocationIndicator> getLocationIndicatorMap(List<String> airfields) {
        // Map which will be returned
        Map<Integer, LocationIndicator> listMap = new HashMap<>();

        for (int i = 0; i < airfields.size(); i++) {
            // Add the List to the Map
            listMap.putIfAbsent(i, getLocationIndicator(airfields.get(i)));
        }

        return listMap;
    }


    /******************/
    /* REALTIME NOTAM */
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
     * Orders RealtimeNotam requests from an airfield list (Local loading)
     * @param airfields
     */
    public void sendLocalRealtimeNotamRequest(List<String> airfields) {
        for (String location:
                airfields) {
            model.localRealtimeNotam(location);
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

    /*******************/
    /* FORMATTED NOTAM */
    /*******************/

    /**
     * Returns a list of FormattedNotam from a list of airfields (String)
     * @param airfields
     * @return
     */
    public Map<Integer, List<FormattedNotam>> getFormattedNotams(List<String> airfields) {
        return createMapFormattedNotams( getRealtimeNotams(airfields) );
    }

    /**
     * Returns a List of decoded notams (FormattedNotam) from a List of FormattedNotam
     * @param encodedNotams
     * @return
     */
    public List<FormattedNotam> getDecodedFormattedNotam(List<FormattedNotam> encodedNotams, LocationIndicator locationIndicator) {
        return model.getDecodedFormattedNotam(encodedNotams, locationIndicator);
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
}
