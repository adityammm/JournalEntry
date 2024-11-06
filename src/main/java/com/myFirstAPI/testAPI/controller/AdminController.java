package com.myFirstAPI.testAPI.controller;

import com.myFirstAPI.testAPI.entity.User;
import com.myFirstAPI.testAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/allUser")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAllEntry();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createUser")
    public void saveUser(@RequestBody User user){
        userService.saveAdmin(user);
    }
}
