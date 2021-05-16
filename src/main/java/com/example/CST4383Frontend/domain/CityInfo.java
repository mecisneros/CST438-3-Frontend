package com.example.CST4383Frontend.domain;

public class CityInfo {
    private final long id;
    private final String name;
    private final String countryCode;
    private final String countryName;
    private final String district;
    private final int population;
    private final String localTemperature;
    private final String localTime;

    public CityInfo(City city, Country country, TempAndTime tempAndTime){
        id = city.getId();
        name = city.getName();
        countryCode = country.getCode();
        countryName = country.getName();
        district = city.getDistrict();
        population = city.getPopulation();
        localTemperature = TempAndTimeHelper.getTemperatureInFahrenheit(tempAndTime.temp);
        localTime = TempAndTimeHelper.localTime(tempAndTime.time, tempAndTime.timezone);
    }

    public CityInfo(long id,
                    String name,
                    String countryCode,
                    String countryName,
                    String district,
                    int population,
                    String localTemperature,
                    String localTime) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.district = district;
        this.population = population;
        this.localTemperature = localTemperature;
        this.localTime = localTime;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    public String getLocalTemperature() {
        return localTemperature;
    }

    public String getLocalTime() {
        return localTime;
    }
}
