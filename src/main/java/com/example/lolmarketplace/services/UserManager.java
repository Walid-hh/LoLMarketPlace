package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserManager {
    public User getUser(int id);
    public List<User> getUsers();
    public User addUser(User user);
    public Optional<User> updateUser(User user, int id);
}
