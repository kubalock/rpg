/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grzegorz
 */
@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping("/user/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public User getUserByUsername(@PathVariable(value = "id") String username) {
        User user = userRepository.getUserByUsername(username);
        return user;
    }
    
    
    @GetMapping("/userEmail/{email}")
    @CrossOrigin(origins = "http://localhost:4200")
    public User getUserByEmail(@PathVariable(value = "email") String email) {
        User user = userRepository.getUserByEmail(email);
        return user;
    }
    
    @PostMapping("/newUser/{username}/{password}/{email}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void newUser(@PathVariable(value = "username") String username,
            @PathVariable(value = "password") String password,
            @PathVariable(value = "email") String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userRepository.save(user);
    }
}
