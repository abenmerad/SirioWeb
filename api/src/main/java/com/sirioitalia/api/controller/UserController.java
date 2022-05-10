package com.sirioitalia.api.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sirioitalia.api.model.User;
import com.sirioitalia.api.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<Collection<User>> getAllUsers() {
        Collection<User> users = userService.getAllUsers();
        return new ResponseEntity<Collection<User>>(users, HttpStatus.FOUND);
    }

    @PostMapping(value = "/users")
    @Transactional
    public ResponseEntity<User> saveUser(@RequestBody User user) {

        User userSaved = userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userUpdated = userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users")
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "id", required = true) Long id) throws BusinessResourceException {

        userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }

    @PostMapping(value = "/users/login")
    public ResponseEntity<User> findUserByEmailAndPassword(@RequestBody User user) {
        Optional<User> userFound = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return new ResponseEntity<User>(userFound.get(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long id) {
        Optional<User> userFound = userService.findUserById(id);
        return new ResponseEntity<User>(userFound.get(), HttpStatus.FOUND);
    }
}