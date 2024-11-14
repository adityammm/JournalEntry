package com.myFirstAPI.testAPI.service;

import com.myFirstAPI.testAPI.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String url = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";

    @Autowired
    private RestTemplate restTemplate;

    public Weather getWeatherDetails(String city){
        String finalURL = url.replace("API_KEY", apiKey).replace("CITY", city);
        ResponseEntity<Weather> response = restTemplate.exchange(finalURL, HttpMethod.GET, null, Weather.class);
        Weather body = response.getBody();

        return body;
    }
}
