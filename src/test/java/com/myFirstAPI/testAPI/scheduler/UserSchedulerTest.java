package com.myFirstAPI.testAPI.scheduler;

import com.myFirstAPI.testAPI.cache.AppCache;
import com.myFirstAPI.testAPI.repository.UserRepositoryImpl;
import com.myFirstAPI.testAPI.service.EmailService;
import com.myFirstAPI.testAPI.service.SentimentAnalysis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;


public class UserSchedulerTest {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private EmailService emailService;

    @Mock
    private UserRepositoryImpl userRepository;

    @Mock
    private SentimentAnalysis sentimentAnalysis;

    @Mock
    private AppCache appCache;

    @InjectMocks
    private UserScheduler userScheduler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void fetchUsersAndSendSaMailTest(){
        userScheduler.fetchUsersAndSendSaMail();
    }
}
