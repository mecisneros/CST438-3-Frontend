package com.example.CST4383Frontend.service;

import com.example.CST4383Frontend.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private WeatherService weatherService;

    public CityInfo getCityInfo(String cityName) {
        try {
            City city = cityRepository.findByName(cityName).get(0);
            System.out.println(city.toString());
            Country country = countryRepository.findByCode(city.getCountrycode());
            TempAndTime tempAndTime = weatherService.getTempAndTime(cityName);
            return new CityInfo(city, country, tempAndTime);
        } catch (Exception e) {
            return null;
        }
    }

    public CityService(CityRepository cityRepository,
                       CountryRepository countryRepository,
                       WeatherService weatherService) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.weatherService = weatherService;
    }

    public CityRepository getCityRepository() {
        return cityRepository;
    }

    public CountryRepository getCountryRepository() {
        return countryRepository;
    }

    public WeatherService getWeatherService() {
        return weatherService;
    }
}
