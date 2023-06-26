package com.example.cloudy;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.cloudy.databinding.FragmentDailyForecastBinding;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class DailyForecastFragment extends Fragment {

    private RecyclerView recyclerView;
    private String JSONData;

    private ArrayList<D_Model> list;

    Units units,units_;
    SharedPreferences sharedPreferences;

    private D_Adapter adapter;
    double coordinates[]=new double[2];

    DailyForecastFragment(double cooridnates[]){
        this.coordinates=cooridnates;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        units=new Units();
        units_=new Units();
        sharedPreferences =new SharedPreferences(getContext());




        com.example.cloudy.databinding.FragmentDailyForecastBinding dailyForecastBinding = FragmentDailyForecastBinding.inflate(getLayoutInflater());
        View view = dailyForecastBinding.getRoot();

        if (sharedPreferences.getDefaultType().equals("Imperial")) {
            units.setType("Imperial");
            units_.setType("Imperial");
        }
        if (sharedPreferences.getDefaultType().equals("Custom")) {
            units.setType("Custom");
            units_.setType("Custom");

            switch (sharedPreferences.getTemperatureUnit()){
                case "c": units.setC(); units_.setC();
                    break;
                case "f":units.setF(); units_.setF();
                    break;
                case "k":units.setK(); units_.setK();
            }
            switch (sharedPreferences.getPrecipitationUnit()){
                case"mm": units.setMm();
                    break;
                case"in": units.setIn();
                    break;
                case"lmp":units.setLmp();
                    break;
            }
            switch (sharedPreferences.getWindUnit()){
                case"kmh": units.setKmh(); units_.setKmh();
                    break;
                case"mph": units.setMph(); units_.setMph();
                    break;
                case"knots":units.setKnots(); units_.setKnots();
                    break;
            }
            switch (sharedPreferences.getPressureUnit()){
                case"hpa": units.setHpa();
                    break;
                case"inhg": units.setInHg();
                    break;
                case"mb":units.setMb();
                    break;
            }
            switch (sharedPreferences.getWindDirectionUnit()){
                case"cardinal": units.setCardinal();
                    break;
                case"degree": units.setDegree();
                    break;
            }
        }


        list= new ArrayList<>();
        recyclerView= dailyForecastBinding.recyclerviewD;

        ApiRequests api_requests = new ApiRequests(coordinates[0], coordinates[1]);
        OkHttpClient client = new OkHttpClient();

        client.newCall(api_requests.getWeather_request()).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    ResponseBody responseBody=response.body();
                    assert responseBody != null;
                    JSONData= responseBody.string();
                    JSON_Parser json_parser=new JSON_Parser(JSONData);

                    ((Activity) requireContext()).runOnUiThread(() -> {

                        for(int i=0;i<7;i++){
                            json_parser.parseJSONData_dailyW(i-1);
                            try {
                                units.setTemperature(json_parser.getMaxTemp_d());
                                units.setPrecipitation(json_parser.getPrecipitation_d());
                                units.setWindspeed(json_parser.getMaxWindspeed_d());
                                units_.setTemperature(json_parser.getMinTemp_d());
                                units_.setWindspeed(json_parser.getMaxWindgusts_d());


                                list.add(new D_Model(json_parser.getDateTime(),json_parser.getWeatherCode_d(),
                                        units.getTemperature(),units_.getTemperature(), units.getPrecipitation(), json_parser.getPrecip_hours_d(),
                                        json_parser.getPrecip_probability_d(),units.getWindspeed(),units_.getWindspeed(),json_parser.getWindDirection_d(),json_parser.getUvIndex_d(),
                                        new Datetime().getTime(json_parser.getSunset_d()),new Datetime().getTime(json_parser.getSunrise_d())));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter = new D_Adapter(list,getContext());
                        recyclerView.setAdapter(adapter);
                        SimpleItemAnimator itemAnimator= (SimpleItemAnimator) recyclerView.getItemAnimator();
                        assert itemAnimator != null;
                        itemAnimator.setSupportsChangeAnimations(false);
                    });

                }
            }
        });







        return view;
    }
}