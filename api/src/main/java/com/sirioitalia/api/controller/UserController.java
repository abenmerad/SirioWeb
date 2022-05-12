package com.sirioitalia.api.controller;


import com.sirioitalia.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sirioitalia.api.model.User;
import com.sirioitalia.api.service.UserService;
import com.sirioitalia.api.exception.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Read - Get all users
     *
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

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {

        Optional<User> user = userService.getUser(id);
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userService.getUser(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ id));

        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        user.setPassword(userDetails.getPassword());
        user.setAddress(userDetails.getAddress());
        user.setCity(userDetails.getCity());
        user.setCountry(userDetails.getCountry());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setPostCode(userDetails.getPostCode());
        final User updatedUser = userService.saveUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/register")
    public User createUser(@Valid @RequestBody User user ) {
        return userService.saveUser(user);
    }

}

