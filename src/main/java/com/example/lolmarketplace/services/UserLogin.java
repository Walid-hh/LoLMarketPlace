package com.example.lolmarketplace.services;


import com.example.lolmarketplace.dao.entities.SecurityUser;
import com.example.lolmarketplace.dao.repositories.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserLogin implements UserDetailsService {

    private final UserRepository userRepository;

    public UserLogin(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found" + username));
    }
}

