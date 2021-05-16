package com.example.CST4383Frontend.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TempAndTimeHelper {

    public static double kelvinToFahrenheit(double temp){
        return (temp - 273.15) * 9.0/5.0 + 32.0;
    }

    public static String getTemperatureInFahrenheit(double temp){
        return String.format("%.2f",kelvinToFahrenheit(temp))+" °F";
    }

    public static String localTime(long time, int timezone){
        Date date = Date.from(new Date(time*1000).toInstant().plusSeconds(timezone));
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        return dateFormat.format(date);
    }

}
