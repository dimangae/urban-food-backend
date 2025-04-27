package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.User;
import com.example.Urbanfood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Find a user by username.
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Save a new user (for registration, if needed).
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
