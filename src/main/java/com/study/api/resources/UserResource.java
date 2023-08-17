package com.study.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.createUser(obj).getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO obj) {
        obj.setId(id);

        User newObj = service.updateUser(obj);

        return ResponseEntity.ok()
                .body(new UserDTO(newObj.getId(), newObj.getName(), newObj.getEmail(), newObj.getPassword()));
    }
}
