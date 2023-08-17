package com.study.api.services;

import java.util.List;

import com.study.api.domain.User;
import com.study.api.domain.dto.UserDTO;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User createUser(UserDTO obj);

    User updateUser(UserDTO obj);

    void deleteUser(Integer id);

}
