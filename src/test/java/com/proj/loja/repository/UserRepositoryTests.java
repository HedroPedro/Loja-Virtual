package com.proj.loja.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.proj.loja.model.Usuario;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(value = TxType.NOT_SUPPORTED)
public class UserRepositoryTests { //Tentar fazer isso depois :), estou coringando

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Usuario usuario;

    @BeforeEach
    public void setUp(){
        usuario = new Usuario(null, null, "Test", "email@email.com", "password", "333333-33");
        entityManager.persistAndFlush(usuario);
    }

    @Test
    public void getUserFromDatabaseThenCheckIfNamesEraEqual(){
        Usuario u = usuarioRepository.findAll().get(0);
        assertEquals(u.getName(), usuario.getName());
    }

    @Test
    public void deleteFromDatabaseThenCheckIfThrowsException(){
        usuarioRepository.delete(usuario);
        assertThrows(NoSuchElementException.class, () -> usuarioRepository.findById(Long.valueOf(1)).get());
    }
}
