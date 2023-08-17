package com.study.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.api.domain.User;
import com.study.api.domain.dto.UserDTO;
import com.study.api.repositories.UserRepository;
import com.study.api.services.UserService;
import com.study.api.services.exceptions.DataIntegratyViolationException;
import com.study.api.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User createUser(UserDTO dto) {
        findByEmail(dto);
        User user = new User(null, dto.getName(), dto.getEmail(), dto.getPassword());

        return repository.save(user);
    }

    @Override
    public User updateUser(UserDTO dto) {
        findByEmail(dto);
        User user = new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword());

        return repository.save(user);
    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = repository.findByEmail(obj.getEmail());

        if (user.isPresent() && !user.get().getId().equals(obj.getId())) {
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema.");
        }
    }
}
