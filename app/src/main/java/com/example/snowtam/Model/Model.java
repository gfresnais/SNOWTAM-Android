package com.example.snowtam.Model;

import org.json.JSONArray;
import org.json.JSONObject;

final class Model {

    private static final String url = "https://applications.icao.int/dataservices/api/";
    private static final String api_key = "?api_key=key";
    private static final String format_arg = "&format=";

    private static final String rt_notams = "notams-realtime-list";
    private static final String loc_ind = "doc7910";

    private static final String airports_arg = "&airports=";
    private static final String criticality_arg = "&criticality=";
    private static final String locations_arg = "&locations=";

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
        JSONObject jsonObject = new JSONObject();


        return jsonObject;
    }
}
