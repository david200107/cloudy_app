package com.example.cloudy;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSON_Parser{


    //Current weather parameters//
    private String temperature_c;
    private String windspeed_c;
    private String pressure_c;
    private String windDirection_c;
    private String weatherCode_c;
    private String cloudCover_c;

    //Hourly weather parameters/////
    private String temperature_h;
    private String rel_humidity_h;
    private String dewpoint_h;
    private String apparent_temperature_h;
    private String precip_probability_h;
    private String precipitation_h;
    private String rain_h;
    private String showers_h;
    private String snowfall_h;
    private String snowDepth_h;
    private String weatherCode_h;
    private String sealevel_pressure_h;
    private String surface_pressure_h;
    private String cloudcover_h;
    private String visibility_h;
    private String windspeed_h;
    private String windgusts_h;
    private String windDirection_h;

    //Daily weather parameters//
    private String weatherCode_d;
    private String maxTemp_d;
    private String minTemp_d;
    private String sunrise_d;
    private String sunset_d;
    private String uvIndex_d;
    private String precipitation_d;
    private String rain_d;
    private String showers_d;
    private String snowfall_d;
    private String precip_hours_d;
    private String precip_probability_d;
    private String maxWindspeed_d;
    private String maxWindgusts_d;
    private String windDirection_d;

    private JSONObject jsonResponse;
    private String jsonString;
    private JSONObject currentWeather;
    private JSONObject hourlyWetaher;
    private String dateTime;



    public String getTemperature_c() {
        return temperature_c;
    }

    public String getWindspeed_c() {
        return windspeed_c;
    }

    public String getPressure_c() {
        return pressure_c;
    }

    public String getWindDirection_c() {
        return windDirection_c;
    }

    public String getWeatherCode_c() {
        return weatherCode_c;
    }

    public String getCloudCover_c() {
        return cloudCover_c;
    }

    public String getTemperature_h() {
        return temperature_h;
    }

    public String getRel_humidity_h() {
        return rel_humidity_h;
    }

    public String getDewpoint_h() {
        return dewpoint_h;
    }

    public String getApparent_temperature_h() {
        return apparent_temperature_h;
    }

    public String getPrecip_probability_h() {
        return precip_probability_h;
    }

    public String getPrecipitation_h() {
        return precipitation_h;
    }

    public String getRain_h() {
        return rain_h;
    }

    public String getShowers_h() {
        return showers_h;
    }

    public String getSnowfall_h() {
        return snowfall_h;
    }

    public String getSnowDepth_h() {
        return snowDepth_h;
    }

    public String getWeatherCode_h() {
        return weatherCode_h;
    }

    public String getSealevel_pressure_h() {
        return sealevel_pressure_h;
    }

    public String getSurface_pressure_h() {
        return surface_pressure_h;
    }

    public String getCloudcover_h() {
        return cloudcover_h;
    }

    public String getVisibility_h() {
        return visibility_h;
    }

    public String getWindspeed_h() {
        return windspeed_h;
    }

    public String getWindgusts_h() {
        return windgusts_h;
    }

    public String getWindDirection_h() {
        return windDirection_h;
    }

    public String getWeatherCode_d() {
        return weatherCode_d;
    }

    public String getMaxTemp_d() {

        return maxTemp_d;
    }

    public String getMinTemp_d() {
        return minTemp_d;
    }

    public String getSunrise_d() {
        return sunrise_d;
    }

    public String getSunset_d() {
        return sunset_d;
    }

    public String getUvIndex_d() {
        return uvIndex_d;
    }

    public String getPrecipitation_d() {
        return precipitation_d;
    }

    public String getRain_d() {
        return rain_d;
    }

    public String getShowers_d() {
        return showers_d;
    }

    public String getSnowfall_d() {
        return snowfall_d;
    }

    public String getPrecip_hours_d() {
        return precip_hours_d;
    }

    public String getPrecip_probability_d() {
        return precip_probability_d;
    }

    public String getMaxWindspeed_d() {
        return maxWindspeed_d;
    }

    public String getMaxWindgusts_d() {
        return maxWindgusts_d;
    }

    public String getWindDirection_d() {
        return windDirection_d;
    }

    public JSONObject getJsonResponse() {
        return jsonResponse;
    }

    public String getJsonString() {
        return jsonString;
    }

    public JSONObject getCurrentWeather() {
        return currentWeather;
    }

    public JSONObject getHourlyWetaher() {
        return hourlyWetaher;
    }

    public String getJson_current_temp() {
        return json_current_temp;
    }

    public String getJson_curr_wind_spd() {
        return json_curr_wind_spd;
    }

    public String getJson_clouds() {
        return json_clouds;
    }

    public String getJson_current_precip() {
        return json_current_precip;
    }

    public String getJson_w_code() {
        return json_w_code;
    }

    public String getJson_wind_gust_spd() {
        return json_wind_gust_spd;
    }

    public String getPrec_chance_json() {
        return prec_chance_json;
    }

    public String getIcon_code() {
        return icon_code;
    }

    public double getJson_pressure() {
        return json_pressure;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public double getLow_temp() {
        return low_temp;
    }

    public double getPrecip_amount() {
        return precip_amount;
    }

    public String getUTCOffset() {
        return UTCOffset;
    }

    public String getTime() {
        return timeH;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "JSON_Parser{" +
                "temperature_c='" + temperature_c + '\'' +
                ", windspeed_c='" + windspeed_c + '\'' +
                ", pressure_c='" + pressure_c + '\'' +
                ", windDirection_c='" + windDirection_c + '\'' +
                ", weatherCode_c='" + weatherCode_c + '\'' +
                ", cloudCover_c='" + cloudCover_c + '\'' +
                ", temperature_h='" + temperature_h + '\'' +
                ", rel_humidity_h='" + rel_humidity_h + '\'' +
                ", dewpoint_h='" + dewpoint_h + '\'' +
                ", apparent_temperature_h='" + apparent_temperature_h + '\'' +
                ", precip_probability_h='" + precip_probability_h + '\'' +
                ", precipitation_h='" + precipitation_h + '\'' +
                ", rain_h='" + rain_h + '\'' +
                ", showers_h='" + showers_h + '\'' +
                ", snowfall_h='" + snowfall_h + '\'' +
                ", snowDepth_h='" + snowDepth_h + '\'' +
                ", weatherCode_h='" + weatherCode_h + '\'' +
                ", sealevel_pressure_h='" + sealevel_pressure_h + '\'' +
                ", surface_pressure_h='" + surface_pressure_h + '\'' +
                ", cloudcover_h='" + cloudcover_h + '\'' +
                ", visibility_h='" + visibility_h + '\'' +
                ", windspeed_h='" + windspeed_h + '\'' +
                ", windgusts_h='" + windgusts_h + '\'' +
                ", windDirection_h='" + windDirection_h + '\'' +
                ", weatherCode_d='" + weatherCode_d + '\'' +
                ", maxTemp_d='" + maxTemp_d + '\'' +
                ", minTemp_d='" + minTemp_d + '\'' +
                ", sunrise_d='" + sunrise_d + '\'' +
                ", sunset_d='" + sunset_d + '\'' +
                ", uvIndex_d='" + uvIndex_d + '\'' +
                ", precipitation_d='" + precipitation_d + '\'' +
                ", rain_d='" + rain_d + '\'' +
                ", showers_d='" + showers_d + '\'' +
                ", snowfall_d='" + snowfall_d + '\'' +
                ", precip_hours_d='" + precip_hours_d + '\'' +
                ", precip_probability_d='" + precip_probability_d + '\'' +
                ", maxWindspeed_d='" + maxWindspeed_d + '\'' +
                ", maxWindgusts_d='" + maxWindgusts_d + '\'' +
                ", windDirection_d='" + windDirection_d + '\'' +
                ", jsonResponse=" + jsonResponse +
                ", jsonString='" + jsonString + '\'' +
                ", currentWeather=" + currentWeather +
                ", hourlyWetaher=" + hourlyWetaher +
                ", json_current_temp='" + json_current_temp + '\'' +
                ", json_curr_wind_spd='" + json_curr_wind_spd + '\'' +
                ", json_clouds='" + json_clouds + '\'' +
                ", json_current_precip='" + json_current_precip + '\'' +
                ", json_w_code='" + json_w_code + '\'' +
                ", json_wind_gust_spd='" + json_wind_gust_spd + '\'' +
                ", prec_chance_json='" + prec_chance_json + '\'' +
                ", icon_code='" + icon_code + '\'' +
                ", json_pressure=" + json_pressure +
                ", max_temp=" + max_temp +
                ", low_temp=" + low_temp +
                ", precip_amount=" + precip_amount +
                ", date "+ dateTime +
                '}';
    }

    private String json_current_temp;
    private String json_curr_wind_spd;
    private String json_clouds;
    private String json_current_precip;
    private String json_w_code;
    private String json_wind_gust_spd;
    private String prec_chance_json;
    private String icon_code;

    private String UTCOffset;

    private double json_pressure;
    private double max_temp;
    private double low_temp;
    private double precip_amount;

    private String timeH;

    JSON_Parser(String jsonString){
        this.jsonString=jsonString;
    }









    boolean parseJSONData_currentW() {
        try {
            jsonResponse=new JSONObject(jsonString);
            currentWeather=jsonResponse.getJSONObject("current_weather");
            hourlyWetaher=jsonResponse.getJSONObject("hourly");

            //Parsing current weather parameters
            temperature_c=currentWeather.getString("temperature");
            pressure_c=String.valueOf(hourlyWetaher.getJSONArray("pressure_msl").getDouble(0));
            windspeed_c=currentWeather.getString("windspeed");
            windDirection_c=currentWeather.getString("winddirection");
            cloudCover_c=hourlyWetaher.getJSONArray("cloudcover").getString(0);
            weatherCode_c=currentWeather.getString("weathercode");


        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    boolean parseJSONData_todayW(){
      ;
        try {
            jsonResponse=new JSONObject(jsonString);
            JSONObject JO=jsonResponse.getJSONObject("daily");
            JSONArray daily_max_temp=JO.getJSONArray("temperature_2m_max");
            JSONArray daily_min_temp=JO.getJSONArray("temperature_2m_min");
            JSONArray daily_wind=JO.getJSONArray("windspeed_10m_max");
            JSONArray daily_prec_sum=JO.getJSONArray("precipitation_sum");
            JSONArray daily_prec_time=JO.getJSONArray("precipitation_probability_max");
            JSONArray daily_uv_index=JO.getJSONArray("uv_index_max");
            JSONArray daily_wind_gusts=JO.getJSONArray("windgusts_10m_max");
            JSONArray daily_wind_dir=JO.getJSONArray("winddirection_10m_dominant");

            //Parsing today weather parameters
            weatherCode_d=JO.getJSONArray("weathercode").getString(0);
            maxTemp_d=daily_max_temp.getString(0);

            minTemp_d=daily_min_temp.getString(0);
            precipitation_d=daily_prec_sum.getString(0);
            maxWindspeed_d=daily_wind.getString(0);
            precip_probability_d=daily_prec_time.getString(0);
            uvIndex_d=daily_uv_index.getString(0);
            maxWindgusts_d=daily_wind_gusts.getString(0);
            windDirection_d=daily_wind_dir.getString(0);

        } catch (JSONException e) {
            return false;
        }
        return true;
    }
    boolean parseJSONData_dailyW(int day_index){


        try {

            jsonResponse=new JSONObject(jsonString);
            JSONObject JO=jsonResponse.getJSONObject("daily");
            JSONArray daily_max_temp=JO.getJSONArray("temperature_2m_max");
            JSONArray daily_min_temp=JO.getJSONArray("temperature_2m_min");
            JSONArray daily_prec_sum=JO.getJSONArray("precipitation_sum");
            JSONArray daily_prec_time=JO.getJSONArray("precipitation_hours");
            JSONArray daily_wind=JO.getJSONArray("windspeed_10m_max");
            JSONArray daily_wind_gst=JO.getJSONArray("windgusts_10m_max");
            JSONArray daily_wind_dir=JO.getJSONArray("winddirection_10m_dominant");
            JSONArray daily_uv_index=JO.getJSONArray("uv_index_max");
            JSONArray time=JO.getJSONArray("time");
            JSONArray w_code=JO.getJSONArray("weathercode");
            JSONArray daily_sunrise=JO.getJSONArray("sunrise");
            JSONArray daily_sunset=JO.getJSONArray("sunset");






            weatherCode_d=w_code.getString(day_index+1);

            maxTemp_d=daily_max_temp.getString(day_index+1);
          //  Log.d("Daily Weather:",String.valueOf(daily_max_temp.getString(day_index+1)));
            minTemp_d=daily_min_temp.getString(day_index+1);
            precip_hours_d=daily_prec_time.getString(day_index+1);
            uvIndex_d=daily_uv_index.getString(day_index+1);
            maxWindgusts_d=daily_wind_gst.getString(day_index+1);
            windDirection_d=daily_wind_dir.getString(day_index+1);
            precip_probability_d=daily_prec_time.getString(day_index+1);
            precipitation_d=daily_prec_sum.getString(day_index+1);
            maxWindspeed_d=daily_wind.getString(day_index+1);
            dateTime=time.getString(day_index+1);
            sunset_d=daily_sunset.getString(day_index+1);
            sunrise_d=daily_sunrise.getString(day_index+1);

          //  dayName=datetime.getDayName(dateTime);


        } catch (JSONException e) {
            return false;
        }
        return true;
    }
    boolean parseJSONData_Hourly( int index){

        String icon;
        try {
            jsonResponse=new JSONObject(jsonString);
            JSONObject JO=jsonResponse.getJSONObject("hourly");
            JSONArray hourly_temp=JO.getJSONArray("temperature_2m");
            JSONArray weather_c=JO.getJSONArray("weathercode");
            JSONArray hourly_prec_sum=JO.getJSONArray("precipitation");
            JSONArray hourly_wind_speed=JO.getJSONArray("windspeed_10m");
            JSONArray time=JO.getJSONArray("time");
           // Date date=new Date();
           // SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            timeH=time.getString(index);



            UTCOffset=jsonResponse.getString("utc_offset_seconds");

            weatherCode_h=weather_c.getString(index);
            temperature_h=hourly_temp.getString(index);
            precipitation_h=hourly_prec_sum.getString(index);
            windspeed_h=hourly_wind_speed.getString(index);

            visibility_h=JO.getJSONArray("visibility").getString(index);
            surface_pressure_h=JO.getJSONArray("surface_pressure").getString(index);
            dewpoint_h=JO.getJSONArray("dewpoint_2m").getString(index);
            rel_humidity_h=JO.getJSONArray("relativehumidity_2m").getString(index);
            apparent_temperature_h=JO.getJSONArray("apparent_temperature").getString(index);
            windgusts_h=JO.getJSONArray("windgusts_10m").getString(index);
            cloudcover_h=JO.getJSONArray("cloudcover").getString(index);




        } catch (JSONException e) {

            return false;
        }
        return true;
    }


}
