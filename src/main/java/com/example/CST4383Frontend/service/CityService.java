package com.example.CST4383Frontend.service;

import com.example.CST4383Frontend.domain.*;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanout;


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

    public void requestReservation(
            String cityName,
            String level,
            String email) {
        String msg  = "{\"cityName\": \""+ cityName +
                "\" \"level\": \""+level+
                "\" \"email\": \""+email+"\"}" ;
        System.out.println("Sending message:"+msg);
        rabbitTemplate.convertSendAndReceive(
                fanout.getName(),
                "",   // routing key none.
                msg);
    }

}
