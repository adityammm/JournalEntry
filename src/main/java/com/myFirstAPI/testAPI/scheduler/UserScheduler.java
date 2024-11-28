package com.myFirstAPI.testAPI.scheduler;

import com.myFirstAPI.testAPI.cache.AppCache;
import com.myFirstAPI.testAPI.entity.JournalEntry;
import com.myFirstAPI.testAPI.entity.User;
import com.myFirstAPI.testAPI.enums.Sentiment;
import com.myFirstAPI.testAPI.repository.UserRepositoryImpl;
import com.myFirstAPI.testAPI.service.EmailService;
import com.myFirstAPI.testAPI.service.SentimentAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

//    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepository.getAllUsersForSA();

        for (User user: users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCount = new HashMap<>();

            for(Sentiment sentiment : sentiments){
                sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;

            for (Map.Entry<Sentiment, Integer> entry : sentimentCount.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            mostFrequentSentiment = Sentiment.HAPPY;

            if(mostFrequentSentiment != null){
                emailService.sendEmail(user.getEmail(), "Sentiment for 7 days", mostFrequentSentiment.toString());
            }

        }
    }

    @Scheduled(cron = "0 0/10 * 1/1 * ?")
    public void scheduledInit(){
        appCache.init();
    }

}
