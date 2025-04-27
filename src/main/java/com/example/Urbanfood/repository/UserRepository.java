package com.example.Urbanfood.repository;

import com.example.Urbanfood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find a user by username.
    User findByUsername(String username);
}
