package com.example.lolmarketplace.controllers;

import com.example.lolmarketplace.dao.entities.User;
import com.example.lolmarketplace.services.UserSignUp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private final UserSignUp userSignUp;

    public SignUpController(UserSignUp userSignUp) {
        this.userSignUp = userSignUp;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signuppage";
    }

    @PostMapping("/UserSignUp")
    public String signUpUser(@ModelAttribute("user") User user) {
        userSignUp.registerUser(user);
        return "loginpage";
    }
}
