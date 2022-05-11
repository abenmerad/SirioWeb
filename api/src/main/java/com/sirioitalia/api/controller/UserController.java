package com.sirioitalia.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sirioitalia.api.model.User;
import com.sirioitalia.api.service.UserService;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Read - Get all users
     * @return - An Iterable object of Employee full filled
     */
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable("id") final Long id) {
        return userService.getUser(id);
    }

}

