package com.sirioitalia.api.service;

import java.util.Collection;
import java.util.Optional;
import com.sirioitalia.api.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    Collection<User> getAllUsers();

    Optional<User> getUserById(Long id)  ;

    Optional<User> findByEmail(String email)  ;

    User saveOrUpdateUser(User user)  ;

    void deleteUser(Long id) ;

    Optional<User> findByEmailAndPassword(String email, String password);
}