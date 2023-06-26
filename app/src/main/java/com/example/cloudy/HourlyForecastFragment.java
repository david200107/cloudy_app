package com.example.cloudy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cloudy.databinding.FragmentHourlyForecastBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class HourlyForecastFragment extends Fragment {

    private RecyclerView recyclerView;
    private  String JSONData;
    private Button btn;
    ApiRequests api_requests;
    private ArrayList<Model> list;
    private FragmentHourlyForecastBinding hourlyForecastBinding;
    private MultiViewAdapter adapter;
    Units units, units_, units__;

    double coordinates[]=new double[2];





    private final String API_KEY="043ed98b045a414bb90d51c7404fe804";
    private String url="https://api.ipgeolocation.io/astronomy?apiKey="+API_KEY+"&lat="+coordinates[0]+"&long="+coordinates[1];


    HourlyForecastFragment(double coordinates[]){
        this.coordinates=coordinates;
    }

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        units=new Units();
        units_=new Units();
        units__=new Units();
        sharedPreferences =new SharedPreferences(getContext());

        hourlyForecastBinding = FragmentHourlyForecastBinding.inflate(getLayoutInflater());
        View view = hourlyForecastBinding.getRoot();



        if (sharedPreferences.getDefaultType().equals("Imperial")) {
            units.setType("Imperial");
            units_.setType("Imperial");
            units__.setType("Imperial");
        }
        if (sharedPreferences.getDefaultType().equals("Custom")) {
            units.setType("Custom");
            units_.setType("Custom");
            units__.setType("Custom");

            switch (sharedPreferences.getTemperatureUnit()){
                case "c": units.setC(); units_.setC(); units__.setC();
                    break;
                case "f":units.setF(); units_.setF(); units__.setF();
                    break;
                case "k":units.setK(); units_.setK(); units__.setK();
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
        api_requests=new ApiRequests(coordinates[0], coordinates[1]);
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

                    for(int i=0;i<72;i+=2){
                        json_parser.parseJSONData_Hourly(i);


                        try {
                            if(new Datetime().getHour(json_parser.getTime()).equals("0")){
                                list.add(new Model(new Datetime().getDate(json_parser.getTime(),"yyyy-MM-dd'T'HH:mm")));
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {


                            units.setTemperature(json_parser.getTemperature_h());
                            units.setPrecipitation(json_parser.getPrecipitation_h());
                            units.setPressure(json_parser.getSurface_pressure_h());
                            units.setWindspeed(json_parser.getWindspeed_h());
                            units_.setWindspeed(json_parser.getWindgusts_h());
                            units_.setTemperature(json_parser.getApparent_temperature_h());
                            units__.setTemperature(json_parser.getDewpoint_h());



                                list.add(new Model(new Datetime().getHour(json_parser.getTime())+":00",json_parser.getWeatherCode_h(),units.getTemperature(),
                                        units.getPrecipitation(),json_parser.getCloudcover_h(),json_parser.getVisibility_h(),units.getPressure(),
                                        units_.getTemperature(),json_parser.getRel_humidity_h(),units__.getTemperature(),units.getWindspeed(),
                                        units_.getWindspeed()));


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter = new MultiViewAdapter(list,getContext());
                    recyclerView=hourlyForecastBinding.recyclerview;
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