package com.example.lolmarketplace.controllers;

import com.example.lolmarketplace.dao.entities.User;
import com.example.lolmarketplace.dao.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final UserRepository userRepository;

    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Authentication authentication, HttpSession session) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();
        if (user != null) {
            String name = user.getUsername();
            session.setAttribute("username", name);
        }
        session.setAttribute("username", username);
        return "index";
    }
}