package com.myFirstAPI.testAPI.scheduler;

import com.myFirstAPI.testAPI.cache.AppCache;
import com.myFirstAPI.testAPI.entity.JournalEntry;
import com.myFirstAPI.testAPI.entity.User;
import com.myFirstAPI.testAPI.repository.UserRepositoryImpl;
import com.myFirstAPI.testAPI.service.EmailService;
import com.myFirstAPI.testAPI.service.SentimentAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysis sentimentAnalysis;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepository.getAllUsersForSA();

        for (User user: users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredJournalEntry = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String join = String.join(" ", filteredJournalEntry);
            String sentiment = sentimentAnalysis.getSentiment(join);
            emailService.sendEmail(user.getEmail(), "Sentiment for 7 days", sentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * 1/1 * ?")
    public void scheduledInit(){
        appCache.init();
    }

}
