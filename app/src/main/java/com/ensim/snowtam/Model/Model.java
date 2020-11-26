package com.ensim.snowtam.Model;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class Model {

    private final String url = "https://applications.icao.int/dataservices/api/";
    private final String api_key = "?api_key=key";
    private final String format_arg = "&format=json";

    private final String rt_notams = "notams-realtime-list";
    private final String loc_ind = "doc7910";

    private final String airports_arg = "&airports=";
    private final String criticality_arg = "&criticality=1";
    private final String locations_arg = "&locations=";

    private final AssetManager am;

    public Model(AssetManager am) {
        this.am = am;
    }


    /**
     * Returns a RealtimeNotam object
     * @param loc
     * @return
     */
    public RealtimeNotam getRealtimeNotam(String loc) {
        // Local loading
        JSONArray jsonArray = loadJSONFromAsset(loc + ".json"); // UUEE.json

        // API loading
        /*if( jsonArray == null ) {
            // Call the API
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + api_key + format_arg + criticality_arg + locations_arg + loc,
                    response -> Log.w("Notam_Response", response.toString()),
                    error -> Log.w("Notam_Error", error.getMessage()));
        }*/

        int index = -1;
        RealtimeNotam rtn = null;

        try {
            if( jsonArray != null ) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    if( jsonObject.getString("all").contains("SNOWTAM") ) {
                        index = i;
                        break;
                    }
                }
                // Found a SNOWTAM
                if( index != -1 ) {
                    rtn = createRealtimeNotam((JSONObject) jsonArray.get(index));
                } else throw new Resources.NotFoundException();
            }
        } catch (JSONException e) {
            Log.w("JSON_Notam_Err", Objects.requireNonNull(e.getMessage()));
        } catch (Resources.NotFoundException e) {
            Log.w("JSON_Notam_Not_Found", "The .json was not found");
        }


        return rtn;
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
            Log.w("Create_Rtn_Err", Objects.requireNonNull(e.getMessage()));
        }

        return  rtn;
    }

    /**
     * Returns a LocationIndicator object
     * @return
     */
    public LocationIndicator getLocationIndicator(String location) {
        // Local loading
        JSONArray jsonArray = loadJSONFromAsset(location + ".json");

        // API loading
        /*if( jsonArray == null ) {
            // Call the API
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + api_key + airports_arg + format_arg,
                    response -> Log.w("Location_Ind_Response", response.toString()),
                    error -> Log.w("Location_Ind_Error", error.getMessage()));
        }*/

        LocationIndicator loc = null;

        try {
            if( jsonArray != null ) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                loc = createLocationIndicatorFromJSON(jsonObject);
            }
        } catch (JSONException e) {
            Log.w("JSON_Loc_Ind_Err", Objects.requireNonNull(e.getMessage()));
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
            Log.w("Create_Loc_Ind_Err", Objects.requireNonNull(e.getMessage()));
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
            Log.w("JSON_Not_Found", Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
