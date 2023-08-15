package com.study.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.study.api.domain.User;
import com.study.api.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB() {
        User u1 = new User(null, "Ettore", "ettorerollo@hotmail.com", "123");
        User u2 = new User(null, "Luiz", "luizvicios123@hotmail.com", "123");

        repository.saveAll(List.of(u1, u2));

    }
}
