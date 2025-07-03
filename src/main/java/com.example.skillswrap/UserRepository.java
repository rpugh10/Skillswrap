package com.example.skillswap.repository;

import com.example.skillswap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
