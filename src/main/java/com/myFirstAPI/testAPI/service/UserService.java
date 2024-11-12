package com.myFirstAPI.testAPI.service;

import com.myFirstAPI.testAPI.entity.User;
import com.myFirstAPI.testAPI.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(List.of("USER"));
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error occurred while saving user : {}", user.getUserName(), e);
            log.debug("Error occurred while saving user : {}", user.getUserName(), e);
            log.warn("Error occurred while saving user : {}", user.getUserName(), e);
            log.info("Error occurred while saving user : {}", user.getUserName(), e);
            throw new RuntimeException(e);
        }

    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(List.of("USER", "ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllEntry(){
        return userRepository.findAll();
    }

    public Optional<User> findEntryById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
