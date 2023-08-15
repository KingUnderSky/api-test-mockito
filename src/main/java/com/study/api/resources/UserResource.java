package com.study.api.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.api.domain.User;
import com.study.api.domain.dto.UserDTO;
import com.study.api.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {

        User user = service.findById(id);
        UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {

        List<UserDTO> listDtos = new ArrayList<>();

        service.findAll().forEach(u -> {
            UserDTO dto = new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getPassword());
            listDtos.add(dto);
        });

        return ResponseEntity.ok().body(listDtos);
    }
}
