package com.example.cloudy;

import android.content.Context;

public class SharedPreferences {
    private static final String PREFERENCES_NAME = "MyPreferences";

    private static final String LOCATION_PERMISION="locationPermision";

    private static final String LATITUDE="latitude";
    private static final String LONGITUDE="longitude";
    private static final String GPS="1";
    private static final String TYPE_KEY = "defaultType";
    private static final String TEMPERATURE_UNIT_KEY = "temperatureUnit";
    private static final String WIND_UNIT_KEY = "windUnit";
    private static final String WIND_DIRECTION_UNIT_KEY = "windDirectionUnit";
    private static final String PRECIPITATION_UNIT_KEY = "precipitationUnit";
    private static final String PRESSURE_UNIT_KEY = "pressureUnit";

    private android.content.SharedPreferences sharedPreferences;

    public SharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setLocationPermision(String locationPermision){
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOCATION_PERMISION, locationPermision);
        editor.apply();
    }
    public String getLocationPermision(){return sharedPreferences.getString(LOCATION_PERMISION,"0");}

    public void setDefaultType(String defaultType) {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TYPE_KEY, defaultType);
        editor.apply();
    }
    public void setLatitude(String latitude){
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LATITUDE, latitude);
        editor.apply();
    }

    public void setLongitudee(String longitude){
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LONGITUDE, longitude);
        editor.apply();
    }
    public void setGps(String gps){
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(GPS, gps);
        editor.apply();
    }
    public String getLatitude(){
        return sharedPreferences.getString(LATITUDE, "");
    }
    public String getLongitude(){
        return sharedPreferences.getString(LONGITUDE, "");
    }
    public String getGPS(){
        return sharedPreferences.getString(GPS, "1");
    }

    public String getDefaultType() {
        return sharedPreferences.getString(TYPE_KEY, "");
    }

    public void setTemperatureUnit(String temperatureUnit) {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEMPERATURE_UNIT_KEY, temperatureUnit);
        editor.apply();
    }

    public String getTemperatureUnit() {
        return sharedPreferences.getString(TEMPERATURE_UNIT_KEY, "");
    }

    public void setWindUnit(String windUnit) {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WIND_UNIT_KEY, windUnit);
        editor.apply();
    }

    public String getWindUnit() {
        return sharedPreferences.getString(WIND_UNIT_KEY, "");
    }

    public void setWindDirectionUnit(String windDirectionUnit) {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WIND_DIRECTION_UNIT_KEY, windDirectionUnit);
        editor.apply();
    }

    public String getWindDirectionUnit() {
        return sharedPreferences.getString(WIND_DIRECTION_UNIT_KEY, "");
    }

    public void setPrecipitationUnit(String precipitationUnit) {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PRECIPITATION_UNIT_KEY, precipitationUnit);
        editor.apply();
    }

    public String getPrecipitationUnit() {
        return sharedPreferences.getString(PRECIPITATION_UNIT_KEY, "");
    }

    public void setPressureUnit(String pressureUnit) {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PRESSURE_UNIT_KEY, pressureUnit);
        editor.apply();
    }

    public String getPressureUnit() {
        return sharedPreferences.getString(PRESSURE_UNIT_KEY, "");
    }
}
