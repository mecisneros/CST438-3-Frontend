package com.example.CST4383Frontend.controller;

import com.example.CST4383Frontend.domain.CityInfo;
import com.example.CST4383Frontend.domain.PromotionForm;
import com.example.CST4383Frontend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping("/cities/{city}")
    public String getCityInfo(@PathVariable("city") String cityName,
                              Model model) {
        CityInfo cityInfo = cityService.getCityInfo(cityName);

        if(cityInfo == null){
            return "ErrorPage";
        }

        model.addAttribute("cityInfo",cityInfo);
        return "CityDetails";
    }
}
