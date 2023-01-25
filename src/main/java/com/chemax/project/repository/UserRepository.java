package com.chemax.project.repository;

import com.chemax.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {
    User findByUsername(String username);
}
