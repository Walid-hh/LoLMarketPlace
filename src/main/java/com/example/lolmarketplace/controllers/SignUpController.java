package com.example.lolmarketplace.controllers;

import com.example.lolmarketplace.dao.entities.User;
import com.example.lolmarketplace.services.UserSignUp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String signUpUser(@ModelAttribute("user") User user,@RequestParam("confirmpassword") String confirmPassword, Model model) {
        if (userSignUp.isUsernameAlreadyExists(user.getUsername())) {
            model.addAttribute("errorMessage", "Username already exists. Please choose a different username.");
            return "signuppage";
        }
        if (user.getPassword().length() < 8) {
            model.addAttribute("errorMessage", "Password must be at least 8 characters long.");
            return "signuppage";
        }
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match.");
            return "signuppage";
        }
        userSignUp.registerUser(user);
        return "loginpage";
    }
}
