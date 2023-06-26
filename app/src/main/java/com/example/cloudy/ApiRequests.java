package com.example.cloudy;

import okhttp3.Request;

public class ApiRequests {
    double lat;
    double lon;


    ApiRequests(double lat , double lon){
        this.lat=lat; this.lon=lon;
    }
    Request getWeather_request(){
        Request request = new Request.Builder()
                .url("https://api.open-meteo.com/v1/forecast?latitude="+lat+"&longitude="+lon+
                        "&hourly=temperature_2m,relativehumidity_2m,dewpoint_2m,apparent_temperature,precipitation_probability,precipitation,rain,showers,snowfall,snow_depth,weathercode,pressure_msl,surface_pressure,cloudcover,visibility,windspeed_10m,winddirection_10m,windgusts_10m&" +
                        "daily=weathercode,temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,precipitation_sum,rain_sum,showers_sum,snowfall_sum,precipitation_hours,precipitation_probability_max,windspeed_10m_max,windgusts_10m_max,winddirection_10m_dominant" +
                        "&current_weather=true&timezone=auto")
                .get()
                .build();
        return request;
    }
}
