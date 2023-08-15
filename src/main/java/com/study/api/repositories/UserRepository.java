package com.study.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.api.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
