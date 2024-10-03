package com.myFirstAPI.testAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthCheck")
public class HealthCheck {

    @GetMapping
    public String healthCheck(){
        return "I am Good";
    }
}
