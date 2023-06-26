package com.example.cloudy;

import java.util.HashMap;

public class Wearher_code {

    HashMap<String, String> weather_code = new HashMap<>();

    Wearher_code() {


    }

    String getWeatherDescription(String code) {


        weather_code.put("0", "Clear sky");
        weather_code.put("1", "Mainly clear");
        weather_code.put("2", "Partly cloudy");
        weather_code.put("3", "Overcast");
        weather_code.put("45", "Fog");
        weather_code.put("48", "Rime fog");
        weather_code.put("51", "Light Drizzle");
        weather_code.put("53", "<Moderate Drizzle");
        weather_code.put("55", "Heavy Drizzle");
        weather_code.put("56", "Freezing Drizzle");
        weather_code.put("57", "Freezing Deizzle");
        weather_code.put("61", "Light Rain");
        weather_code.put("63", "Moderate Rain");
        weather_code.put("65", "Heavy Rain");
        weather_code.put("66", "Freezing Rain");
        weather_code.put("67", "Freezing Rain");
        weather_code.put("71", "Light Snow Fall");
        weather_code.put("73", "Moderate Snow Fall");
        weather_code.put("75", "Heavy Snow Fall");
        weather_code.put("77", "Snow Grains");
        weather_code.put("80", "Rain Showers");
        weather_code.put("81", "Rain Showers");
        weather_code.put("82", "Rain Showers");
        weather_code.put("85", "Snow Showers");
        weather_code.put("86", "Snow Showers");
        weather_code.put("95", "Thunderstorm");
        weather_code.put("96", "Hail");
        weather_code.put("99", "Heavy Hail");

        weather_code.put("0n", "Clear sky");
        weather_code.put("1n", "Mainly clear");
        weather_code.put("2n", "Partly cloudy");
        weather_code.put("3n", "Overcast");
        weather_code.put("45n", "Fog");
        weather_code.put("48n", "Rime fog");
        weather_code.put("51n", "Light Drizzle");
        weather_code.put("53n", "<Moderate Drizzle");
        weather_code.put("55n", "Heavy Drizzle");
        weather_code.put("56n", "Freezing Drizzle");
        weather_code.put("57n", "Freezing Deizzle");
        weather_code.put("61n", "Light Rain");
        weather_code.put("63n", "Moderate Rain");
        weather_code.put("65n", "Heavy Rain");
        weather_code.put("66n", "Freezing Rain");
        weather_code.put("67n", "Freezing Rain");
        weather_code.put("71n", "Light Snow Fall");
        weather_code.put("73n", "Moderate Snow Fall");
        weather_code.put("75n", "Heavy Snow Fall");
        weather_code.put("77n", "Snow Grains");
        weather_code.put("80n", "Rain Showers");
        weather_code.put("81n", "Rain Showers");
        weather_code.put("82n", "Rain Showers");
        weather_code.put("85n", "Snow Showers");
        weather_code.put("86n", "Snow Showers");
        weather_code.put("95n", "Thunderstorm");
        weather_code.put("96n", "Hail");
        weather_code.put("99n", "Heavy Hail");

        return weather_code.get(code);


    }

    String getCurrentWImage(String code) {


        weather_code.put("0", "Clear sky");
        weather_code.put("1", "Mainly clear");
        weather_code.put("2", "Partly cloudy");
        weather_code.put("3", "Overcast");
        weather_code.put("45", "Fog");
        weather_code.put("48", "Rime fog");
        weather_code.put("51", "Drizzle");
        weather_code.put("53", "Drizzle");
        weather_code.put("55", "Drizzle");
        weather_code.put("56", "Freezing Drizzle");
        weather_code.put("57", "Freezing Drizzle");
        weather_code.put("61", "Rain");
        weather_code.put("63", "Rain");
        weather_code.put("65", "Rain");
        weather_code.put("66", "Rain");
        weather_code.put("67", "Rain");
        weather_code.put("71", "Snow");
        weather_code.put("73", "Snow");
        weather_code.put("75", "Snow");
        weather_code.put("77", "Snow");
        weather_code.put("80", "Rain");
        weather_code.put("81", "Rain");
        weather_code.put("82", "Rain");
        weather_code.put("85", "Snow");
        weather_code.put("86", "Snow");
        weather_code.put("95", "Thunderstorm");
        weather_code.put("96", "Thunderstorm");
        weather_code.put("99", "thunderstorm");

        return weather_code.get(code);


    }

    String getCurrentWIcon(String code) {


        weather_code.put("0", "0");
        weather_code.put("1", "1");
        weather_code.put("2", "2");
        weather_code.put("0n", "0n");
        weather_code.put("1n", "1n");
        weather_code.put("2n", "2n");
        weather_code.put("3", "3");
        weather_code.put("45", "4546");
        weather_code.put("48", "4546");
        weather_code.put("51", "51535556576667");
        weather_code.put("53", "51535556576667");
        weather_code.put("55", "51535556576667");
        weather_code.put("56", "51535556576667");
        weather_code.put("57", "51535556576667");
        weather_code.put("61", "616365");
        weather_code.put("63", "616365");
        weather_code.put("65", "616365");
        weather_code.put("66", "616365");
        weather_code.put("67", "616365");
        weather_code.put("71", "71737577");
        weather_code.put("73", "71737577");
        weather_code.put("75", "71737577");
        weather_code.put("77", "71737577");
        weather_code.put("80", "808182");
        weather_code.put("81", "808182");
        weather_code.put("82", "808182");
        weather_code.put("80n", "808182n");
        weather_code.put("81n", "808182n");
        weather_code.put("82n", "808182n");
        weather_code.put("85", "8586");
        weather_code.put("86", "8586");
        weather_code.put("95", "95");
        weather_code.put("96", "95");
        weather_code.put("99", "99");

        return weather_code.get(code);

    }

    /*
        Code	Description
        0	Clear sky
        1, 2, 3	Mainly clear, partly cloudy, and overcast
        45, 48	Fog and depositing rime fog
        51, 53, 55	Drizzle: Light, moderate, and dense intensity
        56, 57	Freezing Drizzle: Light and dense intensity
        61, 63, 65	Rain: Slight, moderate and heavy intensity
        66, 67	Freezing Rain: Light and heavy intensity
        71, 73, 75	Snow fall: Slight, moderate, and heavy intensity
        77	Snow grains
        80, 81, 82	Rain showers: Slight, moderate, and violent
        85, 86	Snow showers slight and heavy
        95 *	Thunderstorm: Slight or moderate
        96, 99 *	Thunderstorm with slight and heavy hail

*/
}
