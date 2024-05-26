package com.java.spring.security.examples.authentication.controller.security.repository;

import com.java.spring.security.examples.authentication.controller.security.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "user_repository")
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String userName);
}
