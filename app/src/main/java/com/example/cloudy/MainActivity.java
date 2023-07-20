package com.example.cloudy;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity  {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    BottomNavigationView bottomNavigationView;


    HomeFragment homeFragment;
    HourlyForecastFragment hourlyForecastFragment;
    DailyForecastFragment dailyForecastFragment;
    SettingsFragment settingsFragment;
    LocationManager locationManager;
    GpsTracker GPSLoc;

    double coordinates[]=new double[2];


    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        sharedPreferences=new SharedPreferences(getApplicationContext());


        if(sharedPreferences.getGPS().equals("1")){
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            GPSLoc = new GpsTracker(getApplicationContext());
            GPSLoc.getLocation();
            coordinates[0]=GPSLoc.getLatitude();
            coordinates[1]=GPSLoc.getLongitude();

        }else{
            coordinates[0]=Double.parseDouble(sharedPreferences.getLatitude());
            coordinates[1]=Double.parseDouble(sharedPreferences.getLongitude());

        }
        sharedPreferences.setGps("1");



        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            sharedPreferences.setLocationPermision("1");

        } else {

           showPermissionDialog();
        }





        if(sharedPreferences.getLocationPermision().equals("1")) {

            if (isNetworkAvailable(getBaseContext())) {

                if(isGPSEnabled(getBaseContext())){
                    homeFragment = new HomeFragment(coordinates[0], coordinates[1]);
                    hourlyForecastFragment = new HourlyForecastFragment(coordinates);
                    dailyForecastFragment = new DailyForecastFragment(coordinates);
                    settingsFragment = new SettingsFragment();


                    getSupportFragmentManager().beginTransaction().add(R.id.fragmentx, homeFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragmentx, hourlyForecastFragment).hide(hourlyForecastFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragmentx, dailyForecastFragment).hide(dailyForecastFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragmentx, settingsFragment).hide(settingsFragment).commit();


                    bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.home:
                                    getSupportFragmentManager().beginTransaction().hide(hourlyForecastFragment).commit();
                                    getSupportFragmentManager().beginTransaction().hide(dailyForecastFragment).commit();
                                    getSupportFragmentManager().beginTransaction().hide(settingsFragment).commit();
                                    getSupportFragmentManager().beginTransaction().show(homeFragment).commit();

                                    return true;
                                case R.id.hourly:
                                    getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                                    getSupportFragmentManager().beginTransaction().hide(dailyForecastFragment).commit();
                                    getSupportFragmentManager().beginTransaction().hide(settingsFragment).commit();
                                    getSupportFragmentManager().beginTransaction().show(hourlyForecastFragment).commit();

                                    return true;
                                case R.id.daily:
                                    getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                                    getSupportFragmentManager().beginTransaction().hide(hourlyForecastFragment).commit();
                                    getSupportFragmentManager().beginTransaction().hide(settingsFragment).commit();
                                    getSupportFragmentManager().beginTransaction().show(dailyForecastFragment).commit();
                                    return true;
                                case R.id.set:
                                    getSupportFragmentManager().beginTransaction().hide(hourlyForecastFragment).commit();
                                    getSupportFragmentManager().beginTransaction().hide(dailyForecastFragment).commit();
                                    getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                                    getSupportFragmentManager().beginTransaction().show(settingsFragment).commit();
                                    return true;
                            }
                            return false;
                        }
                    });
                }else{
                    GPSDisabledDialog();
                }


            } else {
                networkDisabledDialog();
            }
        }




    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
        }

        return false;
    }

    public static boolean isGPSEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager != null) {
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            return isGPSEnabled;
        }

        return false;
    }


    private void showPermissionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Permission Required")
                .setMessage("Location permission is required to access the app. Please grant the permission in App Settings and then restart app")
                .setPositiveButton("Open Settings", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                })
                .setCancelable(false)
                .show();
    }

    private void networkDisabledDialog() {
        new AlertDialog.Builder(this)
                .setTitle("No network connection")
                .setMessage("Please check if wifi or mobile data is enabled.")
                .setPositiveButton("Ok", (dialog, which) -> {
                    Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                })

                .setCancelable(false)
                .show();
    }

    private void GPSDisabledDialog() {
        new AlertDialog.Builder(this)
                .setTitle("GPS (Location) is disabled")
                .setMessage("Please enable GPS location to use app.")
                .setPositiveButton("Ok", (dialog, which) -> {
                    Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                })

                .setCancelable(false)
                .show();
    }


}