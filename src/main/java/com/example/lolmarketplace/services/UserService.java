package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.User;
import com.example.lolmarketplace.dao.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserManager {

    private UserRepository userRepository;
    @Override
    public User getUser(int id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User addUser( User user) {

        user = userRepository.save(user);
        return user;

    }

    @Override
    public Optional<User> updateUser(User user, int id) {
        Optional<User> user1 = Optional.of(userRepository.findById(id).get());
        if (user1.isPresent()){
        user1 = Optional.of(userRepository.save((user)));
        return user1;}
        else {return null; }

    }
}
