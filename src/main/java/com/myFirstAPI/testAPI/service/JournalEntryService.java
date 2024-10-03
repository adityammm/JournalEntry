package com.myFirstAPI.testAPI.service;

import com.myFirstAPI.testAPI.entity.JournalEntry;
import com.myFirstAPI.testAPI.entity.User;
import com.myFirstAPI.testAPI.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }
        catch (Exception e){
            throw new RuntimeException("Error occured while saving entry");
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntry(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findEntryById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean isRemoved = false;
        try{
            User user = userService.findByUserName(userName);
            isRemoved = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(isRemoved){
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
            }
        }
        catch (Exception e){
            throw new RuntimeException("An error occurred while deleting Journal Entry");
        }
        return isRemoved;

    }
}
