package com.ensim.snowtam.Model;

import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
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
     * Returns a LocationIndicator object
     * @return
     */
    public LocationIndicator getLocationIndicator() {
        JSONArray jsonArray = loadJSONFromAsset("Location_Indicators.json");
        JSONObject jsonObject = null;
        LocationIndicator loc = null;

        try {
            jsonObject = (JSONObject) jsonArray.get(0);
            loc = createLocationIndicatorFromJSON(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loc;
    }

    /**
     * Creates a LocationIndicator object from a JSON Object
     * @param jsonObject
     * @return
     */
    private LocationIndicator createLocationIndicatorFromJSON(JSONObject jsonObject) {
        LocationIndicator loc = new LocationIndicator();

        try {
            loc.setTerrCode(jsonObject.getString("Terr_code"));
            loc.setStateName(jsonObject.getString("State_Name"));
            loc.setICAOCode(jsonObject.getString("ICAO_Code"));
            loc.setAFTN(jsonObject.getString("AFTN"));
            loc.setLocationName(jsonObject.getString("Location_Name"));
            loc.setLat(jsonObject.getString("Lat"));
            loc.setLong(jsonObject.getString("Long"));
            loc.setLatitude((Double) jsonObject.get("Latitude"));
            loc.setLongitude((Double) jsonObject.get("Longitude"));
            loc.setCodcoun(jsonObject.getString("codcoun"));
            loc.setIATACode(jsonObject.getString("IATA_Code"));
            loc.setCtryCode(jsonObject.getString("ctry_code"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loc;
    }

    /**
     * Loads a JSON files from the assets folder and returns a JSON Array
     * @param filename
     * @return
     */
    private JSONArray loadJSONFromAsset(String filename) {
        JSONArray jsonArray = null;

        try {
            InputStream is = am.open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            jsonArray = new JSONArray(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
