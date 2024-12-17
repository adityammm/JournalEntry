package com.myFirstAPI.testAPI.service;

import com.myFirstAPI.testAPI.cache.AppCache;
import com.myFirstAPI.testAPI.constants.PlaceHolders;
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

//    private static final String url = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AppCache appCache;

    @Autowired
    RedisService redisService;

    public Weather getWeatherDetails(String city){
        Weather weather = redisService.get("weather_of_" + city, Weather.class);
        if(weather != null){
            return weather;
        }
        else{
            String finalURL = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.API_KEY, apiKey).replace(PlaceHolders.CITY, city);
            ResponseEntity<Weather> response = restTemplate.exchange(finalURL, HttpMethod.GET, null, Weather.class);
            Weather body = response.getBody();
            if(body != null){
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }
    }
}
