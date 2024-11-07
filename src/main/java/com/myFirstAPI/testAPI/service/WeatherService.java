package com.myFirstAPI.testAPI.service;

import com.myFirstAPI.testAPI.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private static final String apiKey = "f152a5b53945467d8c155645240711";

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
