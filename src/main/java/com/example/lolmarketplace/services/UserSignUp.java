package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.User;
import com.example.lolmarketplace.dao.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSignUp {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSignUp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        // Encode the password before saving it to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        userRepository.save(user);
    }
}
