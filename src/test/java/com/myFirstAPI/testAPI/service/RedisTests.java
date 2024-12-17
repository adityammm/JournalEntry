package com.myFirstAPI.testAPI.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RedisTests {

    @InjectMocks
    private RedisTemplate redisTemplate; // EmailService will have the mock injected

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void sendEmailTest(){
        redisTemplate.opsForValue().set("email", "email@email.com");
        Object email = redisTemplate.opsForValue().get("email");


    }
}
