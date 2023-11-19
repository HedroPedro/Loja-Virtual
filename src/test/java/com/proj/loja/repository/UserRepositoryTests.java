package com.proj.loja.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.proj.loja.model.TipoUsuario;
import com.proj.loja.model.Usuario;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@DataJpaTest
@Transactional(value = TxType.NOT_SUPPORTED)
public class UserRepositoryTests { //Tentar fazer isso depois :), estou coringando
    @Autowired
    private UsuarioRepository repository;

    private Usuario usuario;

    @BeforeEach
    public void setUp(){
        usuario = new Usuario((long) 1, TipoUsuario.VISITANTE, "Test", "email@email.com", "password", "333333-33");
    }

    @Test
    public void afterSavingCheckifId0HasNameTest(){
        repository.save(usuario);
        Usuario usuarioTest = repository.getReferenceById((long) 1);
        assertEquals(usuario.getName(), usuarioTest.getName());
    }
}
