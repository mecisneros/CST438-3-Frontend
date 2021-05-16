package com.example.CST4383Frontend.controller;

import com.example.CST4383Frontend.domain.CityInfo;
import com.example.CST4383Frontend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CityController {

    @Autowired
    CityService cityService;

    private static final String[] levels = new String[]{"Luxury $1000","Very Good $500","Good $350"};

    @GetMapping("/cities/{city}")
    public String getCityInfo(@PathVariable("city") String cityName,
                              Model model) {
        CityInfo cityInfo = cityService.getCityInfo(cityName);

        if(cityInfo == null){
            return "ErrorPage";
        }

        model.addAttribute("cityInfo",cityInfo);
        model.addAttribute("levels",levels);
        return "CityDetails";
    }

    @PostMapping("/cities/reservation")
    public String createReservation(
            @RequestParam("city") String cityName,
            @RequestParam("level") String level,
            @RequestParam("email") String email,
            Model model) {

        model.addAttribute("city", cityName);
        model.addAttribute("level", level);
        model.addAttribute("email", email);
        cityService.requestReservation(cityName, level, email);
        return "RequestReservation";
    }

}
