package com.example.CST4383Frontend;

import com.example.CST4383Frontend.controller.CityRestController;
import com.example.CST4383Frontend.domain.City;
import com.example.CST4383Frontend.domain.CityInfo;
import com.example.CST4383Frontend.domain.Country;
import com.example.CST4383Frontend.domain.TempAndTime;
import com.example.CST4383Frontend.service.CityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = Cst4383FrontendApplication.class)
@AutoConfigureMockMvc
public class CityRestControllerTest {

    @MockBean
    CityService cityService;

    @Test
    void contextLoads() {

    }

    @Test
    void testValidCity(){

        /**
         * Prepare mocking data
         */
        String sampleCityName = "sampleCity";
        String sampleCountryCode = "SAM";
        City sampleCity = new City(0,sampleCityName, sampleCountryCode,"",0);
        String sampleCountryName = "Sample Country";
        Country sampleCountry = new Country(sampleCountryCode,sampleCountryName);
        CityInfo sampleCityInfo = new CityInfo(sampleCity,sampleCountry,new TempAndTime(0,0,0));

        /**
         * Add mocks
         */
        Mockito.when(cityService.getCityInfo(sampleCityName)).thenReturn(sampleCityInfo);

        /**
         * Create rest controller and execute
         */
        CityRestController cityRestController = new CityRestController(cityService);
        CityInfo result = cityRestController.getWeather(sampleCityName);

        /**
         * Assert statements
         */
        Assertions.assertEquals(sampleCityInfo, result);

    }

    @Test
    void testInvalidCity(){
        /**
         * Prepare mocking data
         */
        String sampleCityName = "sampleCity";
        String sampleCountryCode = "SAM";
        City sampleCity = new City(0,sampleCityName, sampleCountryCode,"",0);
        String sampleCountryName = "Sample Country";
        Country sampleCountry = new Country(sampleCountryCode,sampleCountryName);
        CityInfo sampleCityInfo = new CityInfo(sampleCity,sampleCountry,new TempAndTime(0,0,0));

        /**
         * Add mocks
         */
        Mockito.when(cityService.getCityInfo(sampleCityName)).thenReturn(sampleCityInfo);

        /**
         * Create rest controller and execute, with an invalid city name
         */
        CityRestController cityRestController = new CityRestController(cityService);
        CityInfo result = cityRestController.getWeather("");

        /**
         * Assert statements
         */
        Assertions.assertNull(result);
    }

    @Test
    void testMultipleCities(){
        /**
         * Prepare mocking data
         */
        String sampleCityNameCommon = "sampleCity";
        String sampleCountryCode1 = "CT1";
        String sampleCountryCode2 = "CT2";
        City sampleCity1 = new City(1, sampleCityNameCommon, sampleCountryCode1,"",0);
        City sampleCity2 = new City(2, sampleCityNameCommon, sampleCountryCode2,"",0);
        List<City> sampleCityList = new ArrayList<>();
        sampleCityList.add(sampleCity1); sampleCityList.add(sampleCity2);
        String sampleCountryName1 = "Sample Country 1";
        String sampleCountryName2 = "Sample Country 2";
        Country sampleCountry1 = new Country(sampleCountryCode1,sampleCountryName1);
        Country sampleCountry2 = new Country(sampleCountryCode2,sampleCountryName2);
        CityInfo sampleCityInfo1 = new CityInfo(sampleCity1,sampleCountry1,new TempAndTime(0,0,0));
        CityInfo sampleCityInfo2 = new CityInfo(sampleCity2,sampleCountry2,new TempAndTime(0,0,0));

        /**
         * Add mocks
         */
        Mockito.when(cityService.getCityInfo(sampleCityNameCommon)).thenReturn(sampleCityInfo1);

        /**
         * Create rest controller and execute, with an invalid city name
         */
        CityRestController cityRestController = new CityRestController(cityService);
        CityInfo result = cityRestController.getWeather(sampleCityNameCommon);

        /**
         * Assert statements
         */
        Assertions.assertTrue(result.equals(sampleCityInfo1)
                                        || result.equals(sampleCityInfo2));
    }

}
