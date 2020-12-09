package com.ensim.snowtam.Model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Object structure of a LocationIndicator
 * Used in JSON files from the ICAO API
 */
public class LocationIndicator {

    private String terrCode;
    private String stateName;
    private String iCAOCode;
    private String aFTN;
    private String locationName;
    private String lat;
    private String _long;
    private Double latitude;
    private Double longitude;
    private String codcoun;
    private String iATACode;
    private String ctryCode;

    public String getTerrCode() {
        return terrCode;
    }

    public void setTerrCode(String terrCode) {
        this.terrCode = terrCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getICAOCode() {
        return iCAOCode;
    }

    public void setICAOCode(String iCAOCode) {
        this.iCAOCode = iCAOCode;
    }

    public String getAFTN() {
        return aFTN;
    }

    public void setAFTN(String aFTN) {
        this.aFTN = aFTN;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCodcoun() {
        return codcoun;
    }

    public void setCodcoun(String codcoun) {
        this.codcoun = codcoun;
    }

    public String getIATACode() {
        return iATACode;
    }

    public void setIATACode(String iATACode) {
        this.iATACode = iATACode;
    }

    public String getCtryCode() {
        return ctryCode;
    }

    public void setCtryCode(String ctryCode) {
        this.ctryCode = ctryCode;
    }

    public LatLng getLatLng() { return new LatLng(latitude, longitude); }

    @Override
    public String toString() {
        return "LocationIndicator [terrCode = " + terrCode + ", stateName = " + stateName + ", ICAOCode = " + iCAOCode + ", AFTN = " + aFTN + ", LocationName = " + locationName + ", lat = " + lat + ", long = " + _long + ", latitude = " + latitude + ", longitude = " + longitude + ", codcoun = " + codcoun + ", iATACode = " + iATACode + ", ctryCode = " + ctryCode + "]";
    }
}
