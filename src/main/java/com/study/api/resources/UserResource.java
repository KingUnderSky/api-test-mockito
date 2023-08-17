package com.study.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    /**
     * Path variable para ID
     */
    private static final String ID = "/{id}";

    @Autowired
    private UserService service;

    @GetMapping(value = ID)
    public ResponseEntity<UserDTO> findById(@PathVariable final Integer id) {

        final User user = service.findById(id);
        final UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {

        final List<UserDTO> listDtos = new ArrayList<>();

        service.findAll().forEach(u -> {
            final UserDTO dto = new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getPassword());
            listDtos.add(dto);
        });

        return ResponseEntity.ok().body(listDtos);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody final UserDTO obj) {
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(service.createUser(obj).getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDTO> update(@PathVariable final Integer id, @RequestBody final UserDTO obj) {
        obj.setId(id);

        final User newObj = service.updateUser(obj);

        return ResponseEntity.ok()
                .body(new UserDTO(newObj.getId(), newObj.getName(), newObj.getEmail(), newObj.getPassword()));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UserDTO> delete(@PathVariable final Integer id) {
        service.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
