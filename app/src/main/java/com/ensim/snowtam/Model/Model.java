package com.ensim.snowtam.Model;

import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public final class Model {

    private final String url = "https://applications.icao.int/dataservices/api/";
    private final String api_key = "?api_key=key";
    private final String format_arg = "&format=";

    private final String rt_notams = "notams-realtime-list";
    private final String loc_ind = "doc7910";

    private final String airports_arg = "&airports=";
    private final String criticality_arg = "&criticality=";
    private final String locations_arg = "&locations=";

    private final AssetManager am;

    public Model(AssetManager am) {
        this.am = am;
    }

    /**
     * Returns a JSON Array containing Realtime Notams
     * @return
     */
    public JSONArray getRealtimeNotams() {
        JSONArray jsonArray = new JSONArray();

        // Load the JSON file

        // Get the JSON Array

        return jsonArray;
    }

    /**
     * Returns a JSON Object containing the given notam id
     * @param id
     * @return
     */
    public JSONObject getNotamById(String id) {
        JSONObject jsonObject = new JSONObject();

        // Get the array

        // Get the corrresponding notam id if exists

        return jsonObject;
    }

    /**
     * Returns a JSON Object containing Location Indicator
     * @return
     */
    public JSONObject getLocationIndicator() {
        JSONObject jsonObject = loadJSONFromAsset("Location_Indicators.json");
        System.out.println(jsonObject.toString());



        return jsonObject;
    }

    private JSONObject loadJSONFromAsset(String filename) {
        JSONObject jsonObject = null;

        try {
            InputStream is = am.open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            jsonObject = jsonArray.getJSONObject(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
