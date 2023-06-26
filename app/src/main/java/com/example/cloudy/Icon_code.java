package com.example.cloudy;

import java.util.HashMap;

public class Icon_code {
     HashMap<String,String>weather_code=new HashMap<>();
    Icon_code(){

    }
    String initIconCode(String code){
        weather_code.put("0", "0");
        weather_code.put("1", "1");
        weather_code.put("2", "2");
        weather_code.put("0n", "0n");
        weather_code.put("1n", "1n");
        weather_code.put("2n", "2n");
        weather_code.put("3", "3");
        weather_code.put("45", "4546");
        weather_code.put("48", "4546");
        weather_code.put("51", "616365");
        weather_code.put("53", "616365");
        weather_code.put("55", "616365");
        weather_code.put("56", "51535556576667");
        weather_code.put("57", "51535556576667");
        weather_code.put("61", "616365");
        weather_code.put("63", "616365");
        weather_code.put("65", "616365");
        weather_code.put("66", "51535556576667");
        weather_code.put("67", "51535556576667");
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
        weather_code.put("96", "99");
        weather_code.put("99", "99");

        weather_code.put("3n", "3");
        weather_code.put("45n", "4546");
        weather_code.put("48n", "4546");
        weather_code.put("51n", "616365");
        weather_code.put("53n", "616365");
        weather_code.put("55n", "616365");
        weather_code.put("56n", "51535556576667");
        weather_code.put("57n", "51535556576667");
        weather_code.put("61n", "616365");
        weather_code.put("63n", "616365");
        weather_code.put("65n", "616365");
        weather_code.put("66n", "51535556576667");
        weather_code.put("67n", "51535556576667");
        weather_code.put("71n", "71737577");
        weather_code.put("73n", "71737577");
        weather_code.put("75n", "71737577");
        weather_code.put("77n", "71737577");
        weather_code.put("85n", "8586");
        weather_code.put("86n", "8586");
        weather_code.put("95n", "95");
        weather_code.put("96n", "99");
        weather_code.put("99n", "99");

        return weather_code.get(code);

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

}
