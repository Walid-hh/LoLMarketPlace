package com.example.lolmarketplace.controllers;

import com.example.lolmarketplace.dao.entities.User;
import com.example.lolmarketplace.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }
    @PostMapping("/user")
    public  User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
