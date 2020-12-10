package com.ensim.snowtam.Model;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Model {

    private final String url = "https://applications.icao.int/dataservices/api/";
    private final String api_key = "?api_key=29bfe5a0-259c-11eb-8d6d-d3711efdc6b7";
    private final String format_arg = "&format=json";

    private final String rt_notams = "notams-realtime-list";
    private final String loc_ind = "doc7910";

    private final String airports_arg = "&airports=";
    private final String criticality_arg = "&criticality=1";
    private final String locations_arg = "&locations=";

    private final AssetManager am;
    private final Context context;

    private final Map<String, RealtimeNotam> rtn_map;
    private final Map<String, LocationIndicator> loc_map;

    public Model(AssetManager am, Context context) {
        this.am = am;
        this.context = context;
        rtn_map = new HashMap<>();
        loc_map = new HashMap<>();
    }


    /* GETTERS */
    public RealtimeNotam getRealtimeNotam(String location) {
        return rtn_map.get(location);
    }

    public LocationIndicator getLocationIndicator(String location) { return loc_map.get(location); }

    /******************/
    /* REALTIME NOTAM */
    /******************/

    /**
     * Gets a local JSON file for a RealtimeNotam
     * For tests purposes only
     * @param location
     */
    public void localRealtimeNotam(String location) {
        JSONArray jsonArray = loadJSONFromAsset(location + ".json"); // UUEE.json
        parseRealtimeNotam(location, jsonArray);
    }

    /**
     * Requests a RealtimeNotam using the ICAO API
     * @param location
     */
    public void requestRealtimeNotam(String location) {
        // Call the API
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + rt_notams + api_key + format_arg + criticality_arg + locations_arg + location,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Notam_Response", "Length : "+response.length());
                        parseRealtimeNotam(location, response);
                    }
                },
                error -> Log.e("Notam_Error", "Time : " + error.getNetworkTimeMs()));

        SingletonRequestQueue.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

    /**
     * Parses a RealtimeNotam in an array
     * @param location
     * @param jsonArray
     */
    private void parseRealtimeNotam(String location, JSONArray jsonArray) {
        int index = -1;

        try {
            if( jsonArray != null ) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    if( jsonObject.getString("id").contains("SW") ) {
                        index = i;
                        break;
                    }
                }
                // Found a SNOWTAM
                if( index != -1 ) {
                    rtn_map.putIfAbsent(location, createRealtimeNotam((JSONObject) jsonArray.get(index)));
                } else throw new Resources.NotFoundException();
            }
        } catch (JSONException e) {
            Log.e("JSON_Notam_Err", Objects.requireNonNull(e.getMessage()));
        } catch (Resources.NotFoundException e) {
            Log.e("JSON_Notam_Not_Found", "The .json was not found");
        }
    }


    /**
     * Creates a RealtimeNotam object from a JSON object
     * @param jsonObject
     * @return
     */
    private RealtimeNotam createRealtimeNotam(JSONObject jsonObject) {
        RealtimeNotam rtn = new RealtimeNotam();
        try {
            rtn.setId(jsonObject.getString("id"));
            rtn.setEntity(jsonObject.getString("entity"));
            rtn.setStatus(jsonObject.getString("status"));
            rtn.setQcode(jsonObject.getString("Qcode"));
            rtn.setArea(jsonObject.getString("Area"));
            rtn.setSubArea(jsonObject.getString("SubArea"));
            rtn.setCondition(jsonObject.getString("Condition"));
            rtn.setSubject(jsonObject.getString("Subject"));
            rtn.setModifier(jsonObject.getString("Modifier"));
            rtn.setMessage(jsonObject.getString("message"));
            rtn.setStartdate(jsonObject.getString("startdate"));
            rtn.setEnddate(jsonObject.getString("enddate"));
            rtn.setAll(jsonObject.getString("all"));
            rtn.setLocation(jsonObject.getString("location"));
            rtn.setIsICAO(jsonObject.getString("isICAO"));
            rtn.setCreated(jsonObject.getString("Created"));
            rtn.setKey(jsonObject.getString("key"));
            rtn.setType(jsonObject.getString("type"));
            rtn.setStateCode(jsonObject.getString("StateCode"));
            rtn.setStateName(jsonObject.getString("StateName"));
            rtn.setCriticality(jsonObject.getString("criticality"));
        } catch (JSONException e) {
            Log.e("Create_Rtn_Err", Objects.requireNonNull(e.getMessage()));
        }

        return  rtn;
    }

    /**
     * Returns a List of FormattedNotams for a given RealtimeNotam
     * @param rtn
     * @return
     */
    public List<FormattedNotam> getFormattedNotams(RealtimeNotam rtn) {
        if(rtn == null) return null;

        List<FormattedNotam> formattedNotams = new ArrayList<>();

        // Get the complete Realtime Notam message then split it
        String[] rtn_all = rtn.getAll().split("\n");

        // Will store the complete list of split messages
        List<String> rtn_spaces = new ArrayList<>();

        // For each String found, split the spaces if a letter is found
        /*for (String s : rtn_all) {
            if (s.substring(0, 2).matches("[A-Z][)]")) {
                Collections.addAll(rtn_spaces, s.split(" "));
            } else rtn_spaces.add(s);
        }*/

        // For each String, add title and content to a new FormattedNotam
        for (String s : rtn_all) {
            if(s.length() > 0) {
                // Instanciate a FormattedNotam which will be added to the List
                FormattedNotam fn = new FormattedNotam();

                // If it begins with a letter it's a title and the rest is a content
                if (s.substring(0, 2).matches("[A-Z][)]")) {
                    // Example of value : A)
                    String title = s.substring(0, s.indexOf(")") + 1);
                    fn.setTitle(title);
                    // The rest of the String is the content, remove the parenthesis
                    fn.setContent( s.substring(title.length()) );
                } else if( s.startsWith("CREATED") || s.startsWith("SOURCE") ) {
                    String title = s.substring(0, s.indexOf(":"));
                    fn.setTitle(title);
                    fn.setContent( s.substring(title.length() + 1) );
                }else fn.setContent(s);

                // Add the FormattedNotam
                formattedNotams.add(fn);
            }
        }

        return formattedNotams;
    }


    /**********************/
    /* LOCATION INDICATOR */
    /**********************/

    /**
     * Loads a local LocationIndicator from a JSON file
     * @param location
     */
    public void localLocationIndicator(String location) {
        JSONArray jsonArray = loadJSONFromAsset(location + "_loc.json");
        parseLocationIndicator(location, jsonArray);
    }

    /**
     * Requests a LocationIndicator
     * @param location
     */
    public void requestLocationIndicator(String location) {
        // Call the API
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + loc_ind + api_key + airports_arg + location + format_arg,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("LocInd_Response", "Length : "+response.length());
                        parseLocationIndicator(location, response);
                    }
                },
                error -> Log.e("LocInd_Error", "Time : " + error.getNetworkTimeMs()));

        SingletonRequestQueue.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

    /**
     * Parses a LocationIndicator
     * @param location
     * @param jsonArray
     */
    public void parseLocationIndicator(String location, JSONArray jsonArray) {
        try {
            if( jsonArray != null ) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                loc_map.putIfAbsent(location, createLocationIndicatorFromJSON(jsonObject));
            }
        } catch (JSONException e) {
            Log.e("JSON_Loc_Ind_Err", Objects.requireNonNull(e.getMessage()));
        }
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
            Log.e("Create_Loc_Ind_Err", Objects.requireNonNull(e.getMessage()));
            return null;
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
            String json = new String(buffer, StandardCharsets.UTF_8);
            jsonArray = new JSONArray(json);
        } catch (FileNotFoundException e) {
            Log.e("JSON_Not_Found", Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            Log.e("loadJSON_Exc", Objects.requireNonNull(e.getMessage()));
        }

        return jsonArray;
    }
}
