package com.example.cloudy;

import android.content.Context;
import android.util.Log;

import java.text.DecimalFormat;

public class Units extends SettingsFragment{
    private final String DEFAULT_TYPE="Metric";
    private final String METRIC_TYPE="Metric";
    private final String IMPERIAL_TYPE="Imperial";
    private final String CUSTOM_TYPE="Custom";
    private String type;
    private String temperatureUnit;
    private String windspeedUnit;
    private String winddirectionUnit;
    private String precipitationUnit;
    private String pressureUnit;
    private String timeFormat;


    private String temperature;
    private String windspeed;
    private String winddirection;
    private String precipitation;
    private String pressure;
    private String time;

    private boolean metric=true,imperial=false,custom=false;
    private boolean c=false,f=false,k=false;
    private boolean kmh=true,mph=false,knots=false;
    private boolean hpa=true,inHg=false,mb=false;
    private boolean in=false,mm=true,lmp=false;
    private boolean cardinal=true,degree=false;

    Context context;

    Units(){
        type=DEFAULT_TYPE;
    }

    Units(Context context){
        this.context=context;
    }

    public boolean isMetric() {
        return metric;
    }

    public void setMetric(boolean metric) {
        this.metric = metric;
    }

    public boolean isImperial() {
        return imperial;
    }

    public void setImperial(boolean imperial) {
        this.imperial = imperial;
    }

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public boolean isC() {
        return c;
    }

    public void setC() {
        this.c = true; this.f=false; this.k=false;
    }

    public boolean isF() {
        return f;
    }

    public void setF() {
        this.c = false; this.f=true; this.k=false;
    }

    public boolean isK() {
        return k;
    }

    public void setK() {
        this.c = false; this.f=false; this.k=true;
    }

    public boolean isKmh() {
        return kmh;
    }

    public void setKmh() {
        this.kmh = true; this.mph=false; this.knots=false;
    }

    public boolean isMph() {
        return mph;
    }

    public void setMph() {
        this.kmh = false; this.mph=true; this.knots=false;
    }

    public boolean isKnots() {
        return knots;
    }

    public void setKnots() {
        this.kmh = false; this.mph=false; this.knots=true;
    }

    public boolean isHpa() {
        return hpa;
    }

    public void setHpa() {
        this.hpa = true; this.inHg=false; this.mb=false;
    }

    public boolean isInHg() {
        return inHg;
    }

    public void setInHg() {
        this.hpa = false; this.inHg=true; this.mb=false;
    }

    public boolean isMb() {
        return mb;
    }

    public void setMb() {
        this.hpa = false; this.inHg=false; this.mb=true;
    }

    public boolean isIn() {
        return in;
    }

    public void setIn() {
        this.in = true; this.mm=false; this.lmp=false;
    }

    public boolean isMm() {
        return mm;
    }

    public void setMm() {
        this.in = false; this.mm=true; this.lmp=false;
    }

    public boolean isLmp() {
        return lmp;
    }

    public void setLmp() {
        this.in = false; this.mm=false; this.lmp=true;
    }

    public boolean isCardinal() {
        return cardinal;
    }

    public void setCardinal() {
        this.cardinal = true; this.degree=false;
    }

    public boolean isDegree() {
        return degree;
    }

    public void setDegree() {
        this.cardinal = false; this.degree=true;
    }

    public String getDEFAULT_TYPE() {
        return DEFAULT_TYPE;
    }

    public String getType() {
        return type;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public String getWindspeedUnit() {
        return windspeedUnit;
    }

    public String getWinddirectionUnit() {
        return winddirectionUnit;
    }

    public String getPrecipitationUnit() {
        return precipitationUnit;
    }

    public String getPressureUnit() {
        return pressureUnit;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public void setWindspeedUnit(String windspeedUnit) {
        this.windspeedUnit = windspeedUnit;
    }

    public void setWinddirectionUnit(String winddirectionUnit) {
        this.winddirectionUnit = winddirectionUnit;
    }

    public void setPrecipitationUnit(String precipitationUnit) {
        this.precipitationUnit = precipitationUnit;
    }

    public void setPressureUnit(String pressureUnit) {
        this.pressureUnit = pressureUnit;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }




    public String getTemperature() {

        if(type.equals(DEFAULT_TYPE)){
            if(temperature.equals("null")){
                return "N/A";
            }
            double temp=Double.parseDouble(temperature);

            return new DecimalFormat("##").format(temp)+"*C";

        } else if (type.equals(IMPERIAL_TYPE)) {

            return CtoF();

        } else if (type.equals(CUSTOM_TYPE)) {
        Log.d("Code executed","Custom");

        if(isC()){
            return temperature+"*C";

        } else if (isF()) {
            return CtoF();

        } else if (isK()) {
            return CtoK();
        }

        }
        return null;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindspeed() {
        if(windspeed.equals("null")){
            return "N/A";
        }
        if(type.equals(DEFAULT_TYPE))
        return windspeed+"Km/h";
        else if (type.equals(IMPERIAL_TYPE)) {
            return KmhToMph();
        } else if (type.equals(CUSTOM_TYPE)) {
            if(isKmh()){
                return windspeed+"Km/h";
            } else if (isMph()) {
                return KmhToMph();
            } else if (isKnots()) {
                return KmhToKnots();
            }

        }
        return null;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getWinddirection() {
        if(winddirection.equals("null")){
            return "N/A";
        }
        if(isCardinal()){
            return degreeToCardinal();
        } else if (isDegree()) {
            return winddirection+"*";
        }
        return null;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getPrecipitation() {
        if(precipitation.equals("null")){
            return "N/A";
        }
       if(type.equals(DEFAULT_TYPE)) {
           return precipitation + "mm";
       }else if(type.equals(IMPERIAL_TYPE)){
           return mmToIn();
       } else if (type.equals(CUSTOM_TYPE)) {

           if(isMm()){
               return precipitation + "mm";
           } else if (isIn()) {
               return mmToIn();
           } else if (isLmp()) {
               return mmToLmp();
           }

       }
        return null;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getPressure() {
        if(pressure.equals("null")){
            return "N/A";
        }
        if(type.equals(DEFAULT_TYPE))
            return pressure+"hpa ";
        else if (type.equals(IMPERIAL_TYPE)) {
            return HpaToInHg();
        } else if (type.equals(CUSTOM_TYPE)) {
            if(isHpa()){
                return pressure+"hpa ";
            } else if (isInHg()) {
                return HpaToInHg();
            } else if (isMb()) {
                return HpaToMb();
            }
        }
        return null;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDefaultUnits(){
        type=DEFAULT_TYPE;
        temperatureUnit="*C";
        windspeedUnit="km/h";
        winddirectionUnit="Cardinal";
        precipitationUnit="mm";
        pressureUnit="hpa";
        timeFormat="12";


    }
    public void setImperialUnits(){
        type="Imperial";
;

    }
    private void setCustomUnits(){
        type="Custom";
    }

    public String CtoF(){

        if(temperature.equals("null")){
            return "null";
        }
        double ftemp=Double.parseDouble(temperature);
        double convertedTemperature=(Double.parseDouble(temperature)*9/5)+32;
        return String.valueOf(new DecimalFormat("#").format(convertedTemperature))+"*F";

    }
    public String CtoK(){

        if(temperature.equals("null")){
            return "null";
        }
        double convertedTemperature=(Double.parseDouble(temperature)+274.15);
        return  String.valueOf(new DecimalFormat("#").format(convertedTemperature))+"*K";

    }
    public String HpaToInHg(){
        if (pressure.equals("null"))
                return "N/A";
        double convertedPressure =Double.parseDouble(pressure)/33.864;
        return String.valueOf((new DecimalFormat("##.#").format(convertedPressure)+"inHg"));
    }
    public String HpaToMb(){
        if (pressure.equals("null"))
            return "N/A";
        return pressure+"mBa";
    }
    public String KmhToMph(){
        if(windspeed.equals("null"))
            return "N/A";
        double convertedWIndspeed=Double.parseDouble(windspeed)*0.621371;
        return  String.valueOf(new DecimalFormat("#").format(convertedWIndspeed)+"mph");
    }
    public String KmhToKnots(){
        if(windspeed.equals("null"))
            return "N/A";
        double convertedWIndspeed=Double.parseDouble(windspeed)*0.5399568;
        return  String.valueOf(new DecimalFormat("##.#").format(convertedWIndspeed)+" knots");
    }
    public String degreeToCardinal(){
        //Log.d("degree",String.valueOf(winddirection))
        double degree=Double.parseDouble(winddirection);
        Log.d("Degree",String.valueOf(degree));
        if(degree>=0&&degree<45)
            return "N";
        if(degree>=45&&degree<90)
            return "NE";
        if(degree>=90&&degree<135)
            return "E";
        if(degree>=135&&degree<180)
            return "SE";
        if(degree>=180&&degree<225)
            return "SE";
        if(degree>=225&&degree<315)
            return "W";
        if(degree>=315&&degree<361)
            return "NW";
        return "N/A";
    }
    public String mmToIn(){
        if(precipitation.equals("null"))
            return "N/A";
        double convertedPrecipitation=Double.parseDouble(precipitation)*0.0393700;
        return  String.valueOf(new DecimalFormat("##.#").format(convertedPrecipitation))+"in";
    }

    public String mmToLmp(){
        if(precipitation.equals("null"))
            return "N/A";
        return  precipitation+"l/mp";
    }




}


