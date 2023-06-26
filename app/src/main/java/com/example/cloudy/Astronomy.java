package com.example.cloudy;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class Astronomy{

    public String getSunset() {
        return Sunset;
    }

    public String getSunrise() {
        return Sunrise;
    }

    public String getMoonrise() {
        return Moonrise;
    }

    public String getMoonset() {
        return Moonset;
    }
    private String Sunset,Sunrise,Moonrise,Moonset;

private double lat,lon;
Context context;

    private final String API_KEY="043ed98b045a414bb90d51c7404fe804";
    private String url="https://api.ipgeolocation.io/astronomy?apiKey="+API_KEY+"&lat="+lat+"&long="+lon;


    Astronomy(double lat,double lon, Context context){
       this.context=context;
        this.lat=lat;
        this.lon=lon;
    }


    void getData(){
        url="https://api.ipgeolocation.io/astronomy?apiKey="+API_KEY+"&lat="+lat+"&long="+lon;
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {

                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        Sunrise =jsonObject.getString("sunrise");
                        Sunset =jsonObject.getString("sunset");
                        Moonrise =jsonObject.getString("moonrise");
                        Moonset =jsonObject.getString("moonset");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        queue.add(stringRequest);
    }





    }

