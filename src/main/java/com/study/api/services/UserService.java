package com.study.api.services;

import com.study.api.domain.User;

public interface UserService {
    
    User findById(Integer id);
}
