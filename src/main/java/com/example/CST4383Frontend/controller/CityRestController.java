package com.example.CST4383Frontend.controller;

import com.example.CST4383Frontend.domain.CityInfo;
import com.example.CST4383Frontend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @GetMapping("/api/cities/{city}")
    public CityInfo getWeather(@PathVariable("city") String cityName) {
        return cityService.getCityInfo(cityName);
    }

    /**
     * For testing purposes
     */
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }
}
