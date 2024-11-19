package com.myFirstAPI.testAPI.cache;

import com.myFirstAPI.testAPI.entity.ConfigJournalAppEntity;
import com.myFirstAPI.testAPI.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    public Map<String, String> APP_CACHE;

    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct // Used to call the method whenever an instance of this class is created
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all){
            APP_CACHE.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
