package com.example.cloudy;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datetime {
    Datetime(){}
    String getDayName(String input) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat inFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=inFormat.parse(input);
        Calendar calendar = Calendar.getInstance();

        assert date != null;
        calendar.setTime(date);

        String[] days = new String[] {  "SUN", "MON", "TUE", "WED", "THU","FRI", "SAT" };

        return days[calendar.get(Calendar.DAY_OF_WEEK)-1];
    }
    String getHour(String input) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("H");
        Date d = null;
        try {
            d = sdf.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert d != null;

        return output.format(d);
    }
    String getHourTime(String input) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("H");
        Date d = null;
        try {
            d = sdf.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert d != null;

        return output.format(d);
    }
    String getTime(String input) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = sdf.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert d != null;

        return output.format(d);
    }

    String getDate(String input,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        SimpleDateFormat output = new SimpleDateFormat("MM-dd");
        Date d = null;
        try {
            d = sdf.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert d != null;
        return output.format(d);

    }

}

