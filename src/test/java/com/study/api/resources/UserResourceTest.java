package com.study.api.resources;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.api.domain.User;
import com.study.api.domain.dto.UserDTO;
import com.study.api.services.impl.UserServiceImpl;

@SpringBootTest
public class UserResourceTest {

    /**
     *
     */
    private static final int INDEX = 0;

    /**
     * VARIÁVEL ID PARA INICIAR OS OBJETOS
     */
    private static final Integer ID = 1;

    /**
     * VARIÁVEL NAME PARA INICIAR OS OBJETOS
     */
    private static final String NAME = "Teste";

    /**
     * VARIÁVEL EMAIL PARA INICIAR OS OBJETOS
     */
    private static final String EMAIL = "teste@teste.com";

    /**
     * VARIÁVEL PASSWORD PARA INICIAR OS OBJETOS
     */
    private static final String PASSWORD = "123";

    /**
     * Mensagem de usuário não encontrado
     */
    private static final String USUÁRIO_NAO_ENCONTRADO = "Usuário não encontrado.";

    /**
     * Mensagem de email não encontrado
     */
    private static final String EMAIL_NAO_ENCONTRADO = "E-mail já cadastrado no sistema.";

    private User user;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void testCreate() {

    }

    @Test
    void testDelete() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFindById() {

    }

    @Test
    void testUpdate() {

    }
    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}
