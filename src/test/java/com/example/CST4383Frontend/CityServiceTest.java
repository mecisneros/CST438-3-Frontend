package com.example.CST4383Frontend;

import com.example.CST4383Frontend.domain.*;
import com.example.CST4383Frontend.service.CityService;
import com.example.CST4383Frontend.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = Cst4383FrontendApplication.class)
@AutoConfigureMockMvc
public class CityServiceTest {

    @MockBean
    CityRepository cityRepository;

    @MockBean
    CountryRepository countryRepository;

    @MockBean
    WeatherService weatherService;


    @Test
    void contextLoads() {}

    @Test
    void testValidCityName() {

        /**
         * Prepare mocking data
         */
        String sampleCityName = "sampleCity";
        String sampleCountryCode = "SAM";
        City sampleCity = new City(0,sampleCityName, sampleCountryCode,"",0);
        List<City> sampleCityList = new ArrayList<>();
        sampleCityList.add(sampleCity);
        String sampleCountryName = "Sample Country";
        Country sampleCountry = new Country(sampleCountryCode,sampleCountryName);

        /**
         * Add mocks
         */
        Mockito.when(cityRepository.findByName(sampleCityName)).thenReturn(sampleCityList);
        Mockito.when(countryRepository.findByCode(sampleCountryCode)).thenReturn(sampleCountry);

        Mockito.when(weatherService.getTempAndTime(any())).thenReturn(new TempAndTime(0,0,0));

        /**
         * Create city service and execute
         */
        CityService cityService = new CityService(cityRepository,countryRepository,weatherService);
        CityInfo cityInfo = cityService.getCityInfo(sampleCityName);

        /**
         * Assert statements
         */
        Assertions.assertNotNull(cityInfo);
        Assertions.assertEquals(sampleCityName,cityInfo.getName());
        Assertions.assertEquals(sampleCountryCode,cityInfo.getCountryCode());
    }


    @Test
    void testInvalidCityName() {

        /**
         * Prepare mocking data
         */
        String sampleCityName = "sampleCity";
        String sampleCountryCode = "SAM";
        City sampleCity = new City(0,sampleCityName, sampleCountryCode,"",0);
        List<City> sampleCityList = new ArrayList<>();
        sampleCityList.add(sampleCity);
        String sampleCountryName = "Sample Country";
        Country sampleCountry = new Country(sampleCountryCode,sampleCountryName);

        /**
         * Add mocks
         */
        Mockito.when(cityRepository.findByName(sampleCityName)).thenReturn(sampleCityList);
        Mockito.when(countryRepository.findByCode(sampleCountryCode)).thenReturn(sampleCountry);

        Mockito.when(weatherService.getTempAndTime(any())).thenReturn(new TempAndTime(0,0,0));

        /**
         * Create city service and execute, with invalid city name
         */
        CityService cityService = new CityService(cityRepository,countryRepository,weatherService);
        CityInfo cityInfo = cityService.getCityInfo("");

        /**
         * Assert null because city is not valid
         */
        Assertions.assertNull(cityInfo);
    }

    @Test
    void testMultipleCities(){
        /**
         * Prepare mocking data, multiple cities
         */
        String sampleCityNameCommon = "sampleCity";
        String sampleCountryCode1 = "CT1";
        String sampleCountryCode2 = "CT2";
        City sampleCity1 = new City(1, sampleCityNameCommon, sampleCountryCode1,"",0);
        City sampleCity2 = new City(2, sampleCityNameCommon, sampleCountryCode2,"",0);
        List<City> sampleCityList = new ArrayList<>();
        sampleCityList.add(sampleCity1);
        sampleCityList.add(sampleCity2);

        String sampleCountryName1 = "Sample Country 1";
        String sampleCountryName2 = "Sample Country 2";

        Country sampleCountry1 = new Country(sampleCountryCode1,sampleCountryName1);
        Country sampleCountry2 = new Country(sampleCountryCode2,sampleCountryName2);

        /**
         * Add mocks
         */
        Mockito.when(cityRepository.findByName(sampleCityNameCommon)).thenReturn(sampleCityList);

        Mockito.when(countryRepository.findByCode(sampleCountryCode1)).thenReturn(sampleCountry1);
        Mockito.when(countryRepository.findByCode(sampleCountryCode2)).thenReturn(sampleCountry2);

        Mockito.when(weatherService.getTempAndTime(any())).thenReturn(new TempAndTime(0,0,0));

        /**
         * Create city service and execute, with invalid city name
         */
        CityService cityService = new CityService(cityRepository,countryRepository,weatherService);
        CityInfo cityInfo = cityService.getCityInfo(sampleCityNameCommon);

        /**
         * Assert the city either belongs to country 1 or country 2
         */
        Assertions.assertTrue(cityInfo.getCountryCode().equals(sampleCountryCode1)
                                         || cityInfo.getCountryCode().equals(sampleCountryCode2) );
    }


}
