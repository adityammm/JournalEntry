package com.myFirstAPI.testAPI.controller;

import com.myFirstAPI.testAPI.entity.JournalEntry;
import com.myFirstAPI.testAPI.entity.User;
import com.myFirstAPI.testAPI.service.JournalEntryService;
import com.myFirstAPI.testAPI.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntryOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntry = user.getJournalEntries();
        if(!journalEntry.isEmpty()){
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addAllEntries(@RequestBody JournalEntry entries){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(entries, userName);
            return new ResponseEntity<JournalEntry>(entries, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{inputId}")
    public  ResponseEntity<?>getEntryById(@PathVariable ObjectId inputId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(inputId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findEntryById(inputId);
            if(journalEntry.isPresent()){
                new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{inputId}/{userName}")
    public ResponseEntity<?> updateOrInsertEntry(
            @PathVariable ObjectId inputId,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName)
    {
        JournalEntry oldEntry = journalEntryService.findEntryById(inputId).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<JournalEntry>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{inputId}/{userName}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId inputId, @PathVariable String userName){
        journalEntryService.deleteById(inputId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
