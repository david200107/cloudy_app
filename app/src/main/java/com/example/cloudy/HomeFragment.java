package com.example.cloudy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cloudy.databinding.FragmentHomeBinding;
import com.example.cloudy.databinding.NavHeaderLayoutBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;




public class HomeFragment extends Fragment {

    //Current Weather
    Context context;
    Activity activity;
    TextView w_condition;
    TextView w_current_temp;
    ImageView condition_img;

    //Weather message layout
    //TODO
    //TextView weather_message;

    //Current condition layout
    TextView pressure, windSpeed_current, clouds, wind_dir_c;

    //Today weather layout
    ImageView todayWeather_image;
    TextView description, maxTemp, minTemp, rainAmount_day, windSpeed_day, prec_probability_day,wind_dir_day,wind_gusts_day,uv_index_day;

    //Hour weather layout
    String[] compReference ={"a","b","c","d","e","f","g","h","i","j","k","l"};
    TextView[] time =new TextView[12];
    TextView[] temp =new TextView[12];
    TextView[] hour_prec_ch =new TextView[12];
    TextView[] hour_prec_amount =new TextView[12];
    ImageView[] hour_weather_icon =new ImageView[12];

    // Daily Forecast layout
    TextView[] day_name =new TextView[7];
    TextView[] day_maxTemp =new TextView[7];
    TextView[] day_minTemp =new TextView[7];
    TextView[] day_prec_ch =new TextView[7];
    TextView[] day_prec_amount =new TextView[7];
    ImageView[] day_weather_icon =new ImageView[7];


    //Other
    EditText locationTxt;
    FragmentHomeBinding binding;
    Button openNav;
    TextView locName;
    LocationManager locationManager;
    double[] coordinates = new double[2];
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    LocationDatabase databaseHelper;

    GpsTracker GPSLoc;
    Geocoder geocoder;
    List<Address> addresses = null;
    MapView map;
    RecyclerView sideNavRecyclerView;
    NavHeaderLayoutBinding navHeaderLayoutBinding;

    // Weather data
    private final OkHttpClient client = new OkHttpClient();
    private String JSONData;
    private StorageReference mStorageRef,StorageRefence_w_icon;
    private StorageReference storageReference;

    ArrayList<L_Model> list= new ArrayList<>();
    List<Location> locations=new ArrayList<>();
    L_Adapter adapter;

    int h,sunseth,sunriseh;

    Units units= new Units();
   // private Dialog errorDialog;

    //Astronomy
    TextView sunrise,sunset,moonrise,moonset;
    final String[] jsonSunrise = new String[1];
    final String[] jsonSunset = new String[1];
    final String[] jsonMoonrise = new String[1];
    final String[] jsonMoonset = new String[1];
    private final String API_KEY="043ed98b045a414bb90d51c7404fe804";
    private String url="https://api.ipgeolocation.io/astronomy?apiKey="+API_KEY+"&lat="+coordinates[0]+"&long="+coordinates[1];

    private SharedPreferences sharedPreferences;



    Calendar currentTime;


        HomeFragment(double lat, double lon){
            this.coordinates[0]=lat;
            this.coordinates[1]=lon;
        }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        navHeaderLayoutBinding=NavHeaderLayoutBinding.inflate(inflater,container,false);
        map=navHeaderLayoutBinding.mapview;
        inflater.inflate(R.layout.nav_header_layout,container,false);
        inflater.inflate(R.layout.fragment_home, container, false);
        View view = binding.getRoot();
        {
            super.onCreate(savedInstanceState);


            drawerLayout = binding.myDraw;
            openNav=binding.openNavi;
            locName=binding.location;
            bottomNavigationView = view.findViewById(R.id.bottom_navigation);

            currentTime = Calendar.getInstance();
            currentTime.setTimeZone(TimeZone.getTimeZone("UTC"));


            //Set navbar color
            requireActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.blk_clr));
            openNav.setOnClickListener(view1 -> drawerLayout.openDrawer(GravityCompat.START));
            GPSLoc = new GpsTracker(getContext());

            geocoder = new Geocoder(getContext(), Locale.getDefault());
            databaseHelper = new LocationDatabase(getContext());

            sharedPreferences = new SharedPreferences(getContext());


             if (sharedPreferences.getDefaultType().equals("Imperial")) {
                units.setType("Imperial");
            }
             if (sharedPreferences.getDefaultType().equals("Custom")) {
                 units.setType("Custom");

                 switch (sharedPreferences.getTemperatureUnit()){
                     case "c": units.setC();
                     break;
                     case "f":units.setF();
                     break;
                     case "k":units.setK();
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
                     case"kmh": units.setKmh();
                         break;
                     case"mph": units.setMph();
                         break;
                     case"knots":units.setKnots();
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

            //Side nav map init
            MapView map;
            Configuration.getInstance().load(requireContext().getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(requireContext().getApplicationContext()));
            map = binding.map;
            map.setTileSource(TileSourceFactory.MAPNIK);
            map.getController().setZoom(16.0);
            GeoPoint point = new GeoPoint(coordinates[0],coordinates[1]);
            Marker startMarker = new Marker(map);
            startMarker.setPosition(point);
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
            map.getOverlays().add(startMarker);
            map.getController().setCenter(point);
            map.setMultiTouchControls(false);

            locations=databaseHelper.getAllLocationsSortedById();
            list.add(new L_Model("1"," ","-"));

            for (Location location : locations) {
                ApiRequests requests=new ApiRequests(location.getLatitude(),location.getLongitude());
                client.newCall(requests.getWeather_request()).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        ResponseBody responseBody=response.body();
                        assert responseBody != null;
                        JSON_Parser json_parser=new JSON_Parser(responseBody.string());
                        json_parser.parseJSONData_todayW();
                        list.add(new L_Model(json_parser.getWeatherCode_d(),new GpsTracker(getContext()).getAddress(location.getLatitude(),location.getLongitude()),new DecimalFormat("##").format(Double.valueOf(json_parser.getMaxTemp_d()))+"*C"));
                    }
                });
            }

            adapter = new L_Adapter(list,getContext());
            sideNavRecyclerView=binding.sideNavRecyclerview;
            sideNavRecyclerView.setAdapter(adapter);





            getWeatherCT();
            getData_hourly(coordinates[0],coordinates[1]);
            getData_daily(coordinates[0],coordinates[1]);
           // loadingDialog.dismissDialog();
            //test();



            sunrise=binding.sunrise;
            sunset=binding.sunset;
            moonrise=binding.moonrise;
            moonset=binding.moonset;

            url="https://api.ipgeolocation.io/astronomy?apiKey="+API_KEY+"&lat="+coordinates[0]+"&long="+coordinates[1];
            RequestQueue queue = Volley.newRequestQueue(requireContext());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            jsonSunrise[0] =jsonObject.getString("sunrise");
                            jsonSunset[0] =jsonObject.getString("sunset");
                            jsonMoonrise[0] =jsonObject.getString("moonrise");
                            jsonMoonset[0] =jsonObject.getString("moonset");

                            ((Activity) requireContext()).runOnUiThread(() -> {
                                try {
                                    Log.d("Sunset:",String.valueOf(new Datetime().getHourTime(jsonSunset[0])));
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                                sunset.setText(jsonSunset[0]);
                                sunrise.setText(jsonSunrise[0]);
                                moonrise.setText(jsonMoonrise[0]);
                                moonset.setText(jsonMoonset[0]);

                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, Throwable::printStackTrace);

            queue.add(stringRequest);
            locName.setOnKeyListener((v, keyCode, event) -> {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    try {
                        addresses = geocoder.getFromLocationName(String.valueOf(locName.getText()), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addresses.size() > 0) {
                        sharedPreferences=new SharedPreferences(getContext());
                        coordinates[0] = addresses.get(0).getLatitude();
                        coordinates[1] = addresses.get(0).getLongitude();
                        sharedPreferences.setLatitude(String.valueOf(coordinates[0]));
                        sharedPreferences.setLongitudee(String.valueOf(coordinates[1]));

                        databaseHelper.insertLocation(new Location(coordinates[0],coordinates[1]));
                        locations=databaseHelper.getAllLocationsSortedById();
                        locName.setText(GPSLoc.getAddress(coordinates[0], coordinates[1]));

                        getWeatherCT();
                       getData_hourly(coordinates[0],coordinates[1]);
                        getData_daily(coordinates[0],coordinates[1] );

                        sharedPreferences.setGps("0");

                        Intent intent = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                   }
                    return true;
                }
                return false;
            });
        }


     //   Log.d("UTC time", String.valueOf(currentTime.get(Calendar.HOUR_OF_DAY)));
        return view;
    }



    public void getWeatherCT(){

        initComponenents();
        ApiRequests requests=new ApiRequests(coordinates[0], coordinates[1]);
        client.newCall(requests.getWeather_request()).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {

                    ResponseBody responseBody = response.body();
                    assert responseBody != null;
                    if(responseBody==null){

                    }
                    JSONData = responseBody.string();
                    JSON_Parser JSONparser=new JSON_Parser(JSONData);

                    ((Activity) requireContext()).runOnUiThread(() -> {

                        //Current weather conditions

                        locName.setText(GPSLoc.getAddress(coordinates[0], coordinates[1]));
                        JSONparser.parseJSONData_currentW();
                        w_condition.setText(new Wearher_code().getWeatherDescription(JSONparser.getWeatherCode_c()));
                        units.setTemperature(JSONparser.getTemperature_c());
                        Log.d("Mp2343","Value:"+units.getTemperature());
                        w_current_temp.setText(units.getTemperature());
                        units.setPressure(JSONparser.getPressure_c());
                        pressure.setText(units.getPressure());
                        units.setWindspeed(JSONparser.getWindspeed_c());
                        windSpeed_current.setText(units.getWindspeed());
                        clouds.setText(JSONparser.getCloudCover_c()+"%");
                        units.setWinddirection(JSONparser.getWindDirection_c());
                        wind_dir_c.setText(units.getWinddirection());

                        mStorageRef = FirebaseStorage.getInstance().getReference("weather_condition/"+new Wearher_code().getCurrentWImage(JSONparser.getWeatherCode_c())+".png");

                        try {
                            File localfile = File.createTempFile("tempfile","png");
                            mStorageRef.getFile(localfile).addOnSuccessListener(taskSnapshot -> {

                                Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                Drawable img=new BitmapDrawable(requireContext().getResources(),bitmap);
                                condition_img.setImageDrawable(img);

                            }).addOnFailureListener(Throwable::printStackTrace);
                        } catch (IOException e) {

                            e.printStackTrace();
                        }

                        //Today weather

                        JSONparser.parseJSONData_todayW();
                        description.setText(new Wearher_code().getWeatherDescription(JSONparser.getWeatherCode_d()));
                        units.setTemperature(JSONparser.getMaxTemp_d());
                        maxTemp.setText(units.getTemperature());
                        units.setTemperature(JSONparser.getMinTemp_d());
                        minTemp.setText(units.getTemperature());
                        units.setWindspeed(JSONparser.getMaxWindspeed_d());
                        windSpeed_day.setText(units.getWindspeed());
                        units.setWindspeed(JSONparser.getMaxWindgusts_d());
                        wind_gusts_day.setText(units.getWindspeed());
                        units.setWinddirection(JSONparser.getWindDirection_d());
                        wind_dir_day.setText(units.getWinddirection());
                        units.setPrecipitation(JSONparser.getPrecipitation_d());
                        rainAmount_day.setText(units.getPrecipitation());
                        prec_probability_day.setText(JSONparser.getPrecip_probability_d()+"%");
                        uv_index_day.setText(JSONparser.getUvIndex_d());


                        try {
                            StorageRefence_w_icon=FirebaseStorage.getInstance().getReference("daily_weather_cond/"+new Wearher_code().getCurrentWIcon(JSONparser.getWeatherCode_d())+".png");
                            File localFile=File.createTempFile("tempfile1","png");
                            StorageRefence_w_icon.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                Drawable img=new BitmapDrawable(requireContext().getResources(),bitmap);
                                todayWeather_image.setImageDrawable(img);

                            });
                        }catch (IOException e){

                            e.printStackTrace();
                        }
                    });
                }
            }
        });



    }



    void initComponenents(){

       
        //weather codition layout
        w_condition =binding.wCondition;
        w_current_temp =binding.wTemperature;
        condition_img=binding.imageView1;
        //Weather message layout
        // weather_symbol=(Image findViewById(R.id.weather_symbol);
        //   weather_message = binding.weatherMessage;
        //Current conditions layout
        pressure = binding.pressureC;
        windSpeed_current = binding.windC;
        clouds = binding.cloudCoverageC;
        wind_dir_c = binding.windDirC;
        //Today weather layout
        todayWeather_image = binding.imageView5;
        description =binding.weatherCondition;
        maxTemp = binding.maxTempT;
        minTemp = binding.minTempT;
        rainAmount_day = binding.precipitation;
        windSpeed_day = binding.windSpeed;
        prec_probability_day = binding.precProbability;
        wind_gusts_day=binding.windGusts;
        wind_dir_day=binding.direction;
        uv_index_day=binding.uvIndex;
        locationTxt = binding.location;

    }

    void initHourlyForecast(int index,String identifier){
        activity=getActivity();
        String time_id="hour_time_"+identifier;
        int resID_time= requireContext().getResources().getIdentifier(time_id,"id", requireContext().getPackageName());
        time[index]= this.activity.findViewById(resID_time);

        String hour_weather_icon_id="hour_weather_icon_"+identifier;
        int resID_hour_weather_icon=getContext().getResources().getIdentifier(hour_weather_icon_id,"id",getContext().getPackageName());
        hour_weather_icon[index]=(ImageView) this.activity.findViewById(resID_hour_weather_icon);

        String temp_id="hour_max_temp_"+identifier;
        int resID_temp=getContext().getResources().getIdentifier(temp_id,"id",getContext().getPackageName());
        temp[index]=(TextView) this.activity.findViewById(resID_temp);



        String hour_prec_ch_id="hour_prec_ch_"+identifier;
        int resID_hour_prec_ch=getContext().getResources().getIdentifier(hour_prec_ch_id,"id",getContext().getPackageName());
        hour_prec_ch[index]=(TextView) this.activity.findViewById(resID_hour_prec_ch);

        String hour_prec_amount_id="hour_prec_amount_"+identifier;
        int resID_hour_prec_amount=getContext().getResources().getIdentifier(hour_prec_amount_id,"id",getContext().getPackageName());
        hour_prec_amount[index]=(TextView) this.activity.findViewById(resID_hour_prec_amount);

    }




    void getData_hourly(double lat,double lon){

         String[] UTC_Offset = new String[1];

        ApiRequests api_requests=new ApiRequests(lat,lon);
        Icon_code iconcode=new Icon_code();

        client.newCall(api_requests.getWeather_request()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    ResponseBody responseBody=response.body();
                    JSONData= responseBody.string();
                    JSON_Parser json_parser=new JSON_Parser(JSONData);
                   // UTC_Offset[0] =Integer.parseInt(json_parser.getUTCOffset())/3600;
                    json_parser.parseJSONData_Hourly(0);
                    UTC_Offset[0] =json_parser.getUTCOffset();

                    int UTCH=Integer.parseInt(UTC_Offset[0])/3600;
                    Log.d("UTCH",String.valueOf(UTCH));
                    ((Activity)getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for(int index=0;index<12;index++){
                                initHourlyForecast(index,compReference[index]);
                                Date d=new Date();
                                int currentHour= 0;
                                currentHour = Integer.valueOf(currentTime.get(Calendar.HOUR_OF_DAY));

                                json_parser.parseJSONData_Hourly(index+currentHour+UTCH);
                                try {
                                     h=Integer.parseInt(new Datetime().getHour(json_parser.getTime()));
                                     Log.d("Sunseth",String.valueOf(jsonSunset[0]));
                                     sunriseh=Integer.parseInt(new Datetime().getHourTime(jsonSunrise[0].toString()));
                                     sunseth=Integer.parseInt(new Datetime().getHourTime(jsonSunset[0].toString()));
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                                int finalIndex2 = index;
                                ((Activity)getContext()).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {
                                            time[finalIndex2].setText(new Datetime().getHour(json_parser.getTime()));
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }


                                         new DecimalFormat("#").format(Double.valueOf(json_parser.getTemperature_h())).toString();
                                         units.setTemperature(new DecimalFormat("#").format(Double.valueOf(json_parser.getTemperature_h())));
                                         temp[finalIndex2].setText(units.getTemperature());
                                         units.setWindspeed(new DecimalFormat("#").format(Double.valueOf(json_parser.getWindspeed_h())));
                                         hour_prec_ch[finalIndex2].setText(units.getWindspeed());
                                         units.setPrecipitation(json_parser.getPrecipitation_h());
                                         hour_prec_amount[finalIndex2].setText(units.getPrecipitation());

                                    }
                                });
                                try {
                                    if(h>sunseth||h<sunriseh&&h<sunriseh){
                                        storageReference= FirebaseStorage.getInstance().getReference("daily_weather_cond/"+iconcode.initIconCode(json_parser.getWeatherCode_h()+"n")+".png");
                                    }else{
                                        storageReference= FirebaseStorage.getInstance().getReference("daily_weather_cond/"+iconcode.initIconCode(json_parser.getWeatherCode_h())+".png");

                                    }
                                    File localFile=File.createTempFile("tempfile1","png");
                                    int finalIndex = index;
                                    storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                            Drawable img=new BitmapDrawable(getContext().getResources(),bitmap);
                                            hour_weather_icon[finalIndex].setImageDrawable(img);
                                        }
                                    });
                                }catch (IOException e){

                                    e.printStackTrace();
                                }

                            }
                        }
                    });
                }
            }
        });


    }
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    public void onPause() {
        super.onPause();
        map.onPause();
    }

    void initDailyForecast(int index,String identifier){
        context=getContext();
        activity=getActivity();
        String day_name_id="day_name_"+identifier;
        int resID_day_name=context.getResources().getIdentifier(day_name_id,"id",context.getPackageName());
        day_name[index]= this.activity.findViewById(resID_day_name);

        String day_weather_icon_id="day_weather_icon_"+identifier;
        int resID_day_weather_icon=context.getResources().getIdentifier(day_weather_icon_id,"id",context.getPackageName());
        day_weather_icon[index]= this.activity.findViewById(resID_day_weather_icon);

        String day_max_temp_id="day_max_temp_"+identifier;
        int resID_day_max_temp=context.getResources().getIdentifier(day_max_temp_id,"id",context.getPackageName());
        day_maxTemp[index]= this.activity.findViewById(resID_day_max_temp);

        String day_min_temp_id="day_min_temp_"+identifier;
        int resID_day_min_temp=context.getResources().getIdentifier(day_min_temp_id,"id",context.getPackageName());
        day_minTemp[index]= this.activity.findViewById(resID_day_min_temp);

        String day_prec_ch_id="day_prec_chance_"+identifier;
        int resID_day_prec_ch=context.getResources().getIdentifier(day_prec_ch_id,"id",context.getPackageName());
        day_prec_ch[index]= this.activity.findViewById(resID_day_prec_ch);

        String day_prec_amount_id="day_prec_amount_"+identifier;
        int resID_day_prec_amount=context.getResources().getIdentifier(day_prec_amount_id,"id",context.getPackageName());
        day_prec_amount[index]= this.activity.findViewById(resID_day_prec_amount);

    }

    void getData_daily(double lat,double lon){


        ApiRequests requests=new ApiRequests(lat,lon);


        client.newCall(requests.getWeather_request()).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    ResponseBody responseBody=response.body();
                    assert responseBody != null;
                    JSONData= responseBody.string();
                    JSON_Parser json_parser=new JSON_Parser(JSONData);
                   // json_parser.parseJSONData_todayW();

                        for(int index=0;index<6;index++){

                            int finalIndex1 = index;
                            initDailyForecast(index,compReference[index]);

                            ((Activity) getContext()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    json_parser.parseJSONData_dailyW(finalIndex1);
                                    units.setTemperature(String.valueOf(json_parser.getMaxTemp_d()));
                                    day_maxTemp[finalIndex1].setText( units.getTemperature());
                                    units.setTemperature(String.valueOf(new DecimalFormat("##").format(Double.valueOf(json_parser.getMinTemp_d()))));
                                    day_minTemp[finalIndex1].setText(units.getTemperature());
                                    units.setWindspeed(String.valueOf(new DecimalFormat("##").format(Double.valueOf(json_parser.getMaxWindspeed_d()))));
                                    day_prec_ch[finalIndex1].setText(units.getWindspeed());
                                    units.setPrecipitation(String.valueOf(new DecimalFormat("##").format(Double.valueOf(json_parser.getPrecipitation_d()))));
                                    day_prec_amount[finalIndex1].setText(units.getPrecipitation());

                                    try {
                                        Log.d("Testdate:",String.valueOf(finalIndex1));
                                        day_name[finalIndex1].setText(new Datetime().getDayName(json_parser.getDateTime())+"\n"+new Datetime().getDate(json_parser.getDateTime(),"yyyy-MM-dd"));
                                   } catch (ParseException e) {

                                        e.printStackTrace();
                                    }

                                    try {
                                        StorageRefence_w_icon=FirebaseStorage.getInstance().getReference("daily_weather_cond/"+new Icon_code().initIconCode(json_parser.getWeatherCode_d())+".png");
                                        File localFile=File.createTempFile("tempfile1","png");
                                        StorageRefence_w_icon.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
                                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                            Drawable img=new BitmapDrawable(context.getResources(),bitmap);
                                            day_weather_icon[finalIndex1].setImageDrawable(img);

                                        });
                                    }catch (IOException e){

                                        e.printStackTrace();
                                    }
                                }
                            });


                        }

                }
            }
        });

    }

}