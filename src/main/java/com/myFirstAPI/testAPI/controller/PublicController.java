package com.myFirstAPI.testAPI.controller;

import com.myFirstAPI.testAPI.entity.User;
import com.myFirstAPI.testAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

}
