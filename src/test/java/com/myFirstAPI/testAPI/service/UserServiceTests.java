package com.myFirstAPI.testAPI.service;

import com.myFirstAPI.testAPI.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Component
public class UserServiceTests {

    @Autowired
    JournalEntryService journalEntryService;

    @Test
    public void testFindByUsername(){

        String name = "ABC";
        assertNotNull(name);
    }
}
