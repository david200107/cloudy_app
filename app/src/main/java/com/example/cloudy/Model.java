package com.example.cloudy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cloudy.databinding.FragmentHomeBinding;
import com.example.cloudy.databinding.RowBinding;

public class Model extends Fragment {

    public static final int FORECAST_TYPE=0;
    public static final int DATETIME_TYPE=1;
    TextView time_,condition_,temp_,prec__amount_,cloud_cover_,visibility_,pressure_,aparent_temp_,humidity_,dew_point_,wind_,wind_gst_;
    String time,condition,temp,prec_amount,cloud_cover,visibility,pressure,aparent_temp,humidity,dew_point,wind,wind_gst;
    String datetime;
    public int type;
    public boolean expandable;
    public RowBinding binding;

    public Model(){


    }


    public Model(String Datetime)
    {
        this.datetime=Datetime;
        this.type=DATETIME_TYPE;

    }

    public Model(String time, String condition, String temp, String prec_amount, String cloud_cover,
                 String visibility, String pressure, String aparent_temp, String humidity, String dew_point, String wind, String wind_gst) {
        this.time = time;
        this.condition = condition;
        this.temp = temp;
        this.prec_amount = prec_amount;
        this.cloud_cover = cloud_cover;
        this.visibility = visibility;
        this.pressure = pressure;
        this.aparent_temp = aparent_temp;
        this.humidity = humidity;
        this.dew_point = dew_point;
        this.wind = wind;
        this.wind_gst = wind_gst;
        this.type=FORECAST_TYPE;
    }

    @Override
    public String toString() {
        return "Model{" +
                "time_=" + time_ +
                ", condition_=" + condition_ +
                ", temp_=" + temp_ +
                ", prec__amount_=" + prec__amount_ +
                ", cloud_cover_=" + cloud_cover_ +
                ", visibility_=" + visibility_ +
                ", pressure_=" + pressure_ +
                ", aparent_temp_=" + aparent_temp_ +
                ", humidity_=" + humidity_ +
                ", dew_point_=" + dew_point_ +
                ", wind_=" + wind_ +
                ", wind_gst_=" + wind_gst_ +
                ", time='" + time + '\'' +
                ", condition='" + condition + '\'' +
                ", temp='" + temp + '\'' +
                ", prec_amount='" + prec_amount + '\'' +
                ", cloud_cover='" + cloud_cover + '\'' +
                ", visibility='" + visibility + '\'' +
                ", pressure='" + pressure + '\'' +
                ", aparent_temp='" + aparent_temp + '\'' +
                ", humidity='" + humidity + '\'' +
                ", dew_point='" + dew_point + '\'' +
                ", wind='" + wind + '\'' +
                ", wind_gst='" + wind_gst + '\'' +
                ", datetime='" + datetime + '\'' +
                ", type=" + type +
                ", expandable=" + expandable +
                ", binding=" + binding +
                '}';
    }

    public String getTime() {
        return time;
    }

    public String getCondition() {
        return condition;
    }

    public String getTemp() {
        return temp;
    }

    public String getPrec_amount() {
        return prec_amount;
    }

    public String getCloud_cover() {
        return cloud_cover;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public String getAparent_temp() {
        return aparent_temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getDew_point() {
        return dew_point;
    }

    public String getWind() {
        return wind;
    }

    public String getWind_gst() {
        return wind_gst;
    }

    public String getDatetime() {
        return datetime;
    }

    void initComponets(){


    }


    void setParameters(){

    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
    public boolean isExpandable() {
        return expandable;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = RowBinding.inflate(inflater, container, false);
        inflater.inflate(R.layout.fragment_home, container, false);
        View view = binding.getRoot();

        temp_=binding.hTemp;
        temp_.setText("ABC");



        return view;
    }


}
