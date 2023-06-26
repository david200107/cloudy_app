package com.example.cloudy;

public class L_Model {

    private String weatherIcon, location,temperature;

    public L_Model(String weatherIcon, String location, String temperature) {
        this.weatherIcon = weatherIcon;
        this.location = location;
        this.temperature = temperature;

    }
    public String getWeatherIcon() {
        return weatherIcon;
    }

    public String getLocation() {
        return location;
    }

    public String getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "L_Model{" +
                "weatherIcon='" + weatherIcon + '\'' +
                ", location='" + location + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}
