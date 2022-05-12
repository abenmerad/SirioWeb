package com.sirioitalia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sirioitalia.api.model.User;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);
}
