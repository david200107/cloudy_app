package com.example.cloudy;


import androidx.fragment.app.Fragment;

public class D_Model extends Fragment {


    private String date,condition,max_temp,min_tmep,prec_amount,prec_hour,prec_probability,wind,wind_gst,wind_dir,uv_index,sunet,sunrise;



    public D_Model(String date, String condition, String max_temp, String min_tmep, String prec_amount, String prec_hour,String prec_probability, String wind, String wind_gst,String wind_dir, String uv_index,String sunset,String sunrise) {
        this.date = date;
        this.condition = condition;
        this.max_temp = max_temp;
        this.min_tmep = min_tmep;
        this.prec_amount = prec_amount;
        this.prec_hour = prec_hour;
        this.prec_probability=prec_probability;
        this.wind = wind;
        this.wind_gst = wind_gst;
        this.uv_index = uv_index;
        this.wind_dir=wind_dir;
        this.sunrise=sunrise;
        this.sunet=sunset;
    }

    public String getDate() {
        return date;
    }

    public String getCondition() {
        return condition;
    }

    public String getMax_temp() {
        return max_temp;
    }

    public String getMin_tmep() {
        return min_tmep;
    }

    public String getPrec_amount() {
        return prec_amount;
    }

    public String getPrec_hour() {
        return prec_hour;
    }

    public String getWind() {
        return wind;
    }

    public String getWind_gst() {
        return wind_gst;
    }

    public String getUv_index() {
        return uv_index;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public String getSunet() {
        return sunet;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getPrec_probability() {
        return prec_probability;
    }

    @Override
    public String toString() {
        return "D_Model{" +
                "date='" + date + '\'' +
                ", condition='" + condition + '\'' +
                ", max_temp='" + max_temp + '\'' +
                ", min_tmep='" + min_tmep + '\'' +
                ", prec_amount='" + prec_amount + '\'' +
                ", prec_hour='" + prec_hour + '\'' +
                ", wind='" + wind + '\'' +
                ", wind_gst='" + wind_gst + '\'' +
                ", wind_dir='" + wind_dir + '\'' +
                ", uv_index='" + uv_index + '\'' +
                ", sunet='" + sunet + '\'' +
                ", sunrise='" + sunrise + '\'' +
                '}';
    }
}
