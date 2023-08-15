package com.study.api.services;

import java.util.List;

import com.study.api.domain.User;

public interface UserService {
    
    User findById(Integer id);

    List<User> findAll();
    
}
