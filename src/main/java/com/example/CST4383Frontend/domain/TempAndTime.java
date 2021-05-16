package com.example.CST4383Frontend.domain;

public class TempAndTime {
    public double temp;
    public long time;
    public int timezone;

    public TempAndTime(double temp, long time, int timezone){
        this.temp = temp;
        this.time = time;
        this.timezone = timezone;
    }
}
