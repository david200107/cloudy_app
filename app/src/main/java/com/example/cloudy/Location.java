package com.example.cloudy;

public class Location {
    private int id;
    private double latitude;
    private double longitude;

    public Location( double latitude, double longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
