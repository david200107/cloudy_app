package com.example.cloudy;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cloudy.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

Button imperial, metrics, customise, celsius,farenhait,kelvin,kmh,mph,knots,in,mm,lmp,mb,hpa,mmhg,cardianl,degree,h12,h24;
SharedPreferences sharedPreferences;
Units units;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentSettingsBinding fragmentSettingsBinding;

        fragmentSettingsBinding=FragmentSettingsBinding.inflate(getLayoutInflater());
        View view = fragmentSettingsBinding.getRoot();

        sharedPreferences =new SharedPreferences(getContext());
        units=new Units();



        imperial=fragmentSettingsBinding.imperial;
        metrics=fragmentSettingsBinding.metrics;
        customise=fragmentSettingsBinding.customise;
        celsius=fragmentSettingsBinding.c;
        farenhait=fragmentSettingsBinding.f;
        kelvin=fragmentSettingsBinding.k;
        kmh=fragmentSettingsBinding.kmh;
        mph=fragmentSettingsBinding.mph;
        knots=fragmentSettingsBinding.knots;
        in=fragmentSettingsBinding.in;
        mm=fragmentSettingsBinding.mm;
        lmp=fragmentSettingsBinding.lmp;
        hpa=fragmentSettingsBinding.hpa;
        mmhg=fragmentSettingsBinding.mmhg;
        mb=fragmentSettingsBinding.mb;
        cardianl=fragmentSettingsBinding.cardinal;
        degree=fragmentSettingsBinding.degree;
        h12=fragmentSettingsBinding.h12;
        h24=fragmentSettingsBinding.h24;



        if(sharedPreferences.getDefaultType().equals("Metric")){

            enableMetricButton();
        }
        if(sharedPreferences.getDefaultType().equals("Imperial")){

            enableImperialButton();
        }
        if(sharedPreferences.getDefaultType().equals("Custom")){

            enableCustomButton();

            switch (sharedPreferences.getTemperatureUnit()){
                case "c":

                    celsius.setBackgroundColor(Color.BLUE);
                    farenhait.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    kelvin.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
                case "f":

                    farenhait.setBackgroundColor(Color.BLUE);
                    celsius.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    kelvin.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
                case "k":

                    kelvin.setBackgroundColor(Color.BLUE);
                    celsius.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    farenhait.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
            }
            switch (sharedPreferences.getPrecipitationUnit()){
                case"mm":
                    mm.setBackgroundColor(Color.BLUE);
                    in.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    lmp.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
                case"in":
                    in.setBackgroundColor(Color.BLUE);
                    mm.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    lmp.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
                case"lmp":
                    lmp.setBackgroundColor(Color.BLUE);
                    in.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    mm.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
            }
            switch (sharedPreferences.getWindUnit()){
                case"kmh":
                    kmh.setBackgroundColor(Color.BLUE);
                    mph.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    knots.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
                case"mph":
                    mph.setBackgroundColor(Color.BLUE);
                    kmh.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    knots.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
                case"knots":
                    knots.setBackgroundColor(Color.BLUE);
                    kmh.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    mph.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
            }
            switch (sharedPreferences.getPressureUnit()){
                case"hpa":
                    hpa.setBackgroundColor(Color.BLUE);
                    mmhg.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    mb.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
                case"inhg":
                    mmhg.setBackgroundColor(Color.BLUE);
                    hpa.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    mb.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
                case"mb":
                    mb.setBackgroundColor(Color.BLUE);
                    hpa.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    mmhg.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
            }
            switch (sharedPreferences.getWindDirectionUnit()){
                case "cardinal":
                    cardianl.setBackgroundColor(Color.BLUE);
                    degree.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
                case "degree":
                    degree.setBackgroundColor(Color.BLUE);
                    cardianl.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                    break;
            }
        }

        imperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enableImperialButton();
                sharedPreferences.setDefaultType("Imperial");

                sharedPreferences.setTemperatureUnit("f");
                sharedPreferences.setWindUnit("mph");
                sharedPreferences.setPressureUnit("inhg");
                sharedPreferences.setPrecipitationUnit("in");
                sharedPreferences.setWindDirectionUnit("cardinal");

            }
        });
        metrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableMetricButton();
                sharedPreferences.setDefaultType("Metric");

                sharedPreferences.setTemperatureUnit("c");
                sharedPreferences.setWindUnit("kmh");
                sharedPreferences.setPressureUnit("hpa");
                sharedPreferences.setPrecipitationUnit("mm");
                sharedPreferences.setWindDirectionUnit("cardinal");


            }
        });
        customise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                enableCustomButton();

            }
        });



        celsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setTemperatureUnit("c");
               enableCustomButton();
                celsius.setBackgroundColor(Color.BLUE);
                farenhait.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                kelvin.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));

            }
        });
        farenhait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setTemperatureUnit("f");
                farenhait.setBackgroundColor(Color.BLUE);
                customise.setBackgroundColor(Color.BLUE);
                imperial.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                metrics.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                celsius.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                kelvin.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));

            }
        });
        kelvin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setTemperatureUnit("k");
                enableCustomButton();
                kelvin.setBackgroundColor(Color.BLUE);
                farenhait.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                celsius.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));

            }
        });
        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setPrecipitationUnit("mm");
                enableCustomButton();
                mm.setBackgroundColor(Color.BLUE);
                in.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                lmp.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));

            }
        });
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setPrecipitationUnit("in");
                enableCustomButton();
                in.setBackgroundColor(Color.BLUE);
                mm.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                lmp.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));

            }
        });
        lmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setPrecipitationUnit("lmp");
                enableCustomButton();
                lmp.setBackgroundColor(Color.BLUE);
                in.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                mm.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));

            }
        });
        kmh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setWindUnit("kmh");
                enableCustomButton();
                kmh.setBackgroundColor(Color.BLUE);
                mph.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                knots.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));

            }
        });
        mph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setWindUnit("mph");
                enableCustomButton();
                mph.setBackgroundColor(Color.BLUE);
                kmh.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                knots.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));

            }
        });
        knots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setWindUnit("knots");
                enableCustomButton();
                knots.setBackgroundColor(Color.BLUE);
                mph.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                kmh.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));

            }
        });
        hpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setPressureUnit("hpa");
                enableCustomButton();
                hpa.setBackgroundColor(Color.BLUE);
                mmhg.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                mb.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            }
        });
        mmhg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setPressureUnit("hpa");
                enableCustomButton();
                mmhg.setBackgroundColor(Color.BLUE);
                hpa.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                mb.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            }
        });
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setDefaultType("Custom");
                sharedPreferences.setPressureUnit("hpa");
                enableCustomButton();
                mb.setBackgroundColor(Color.BLUE);
                mmhg.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
                hpa.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            }
        });
        cardianl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setWindDirectionUnit("cardinal");
                cardianl.setBackgroundColor(Color.BLUE);
                degree.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            }
        });
        degree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setWindDirectionUnit("degree");
                degree.setBackgroundColor(Color.BLUE);
                cardianl.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            }
        });


        return view;
    }
    void enableMetricButton(){
        metrics.setBackgroundColor(Color.BLUE);
        imperial.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        customise.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        celsius.setBackgroundColor(Color.BLUE);
        farenhait.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        kelvin.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        kmh.setBackgroundColor(Color.BLUE);
        mph.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        knots.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        hpa.setBackgroundColor(Color.BLUE);
        mmhg.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        mb.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        mm.setBackgroundColor(Color.BLUE);
        in.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        lmp.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        cardianl.setBackgroundColor(Color.BLUE);
        degree.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        h12.setBackgroundColor(Color.BLUE);
        h24.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));

    }
    void enableImperialButton(){
           imperial.setBackgroundColor(Color.BLUE);
           metrics.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
           customise.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
           farenhait.setBackgroundColor(Color.BLUE);
           celsius.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
           kelvin.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
           mph.setBackgroundColor(Color.BLUE);
            kmh.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            knots.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            mmhg.setBackgroundColor(Color.BLUE);
            hpa.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            mb.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            in.setBackgroundColor(Color.BLUE);
            mm.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            lmp.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            cardianl.setBackgroundColor(Color.BLUE);
            degree.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
            h12.setBackgroundColor(Color.BLUE);
            h24.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
    }
    void enableCustomButton(){
        customise.setBackgroundColor(Color.BLUE);
        imperial.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
        metrics.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.incative_btn));
    }
}