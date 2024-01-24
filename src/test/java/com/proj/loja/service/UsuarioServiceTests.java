package com.proj.loja.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proj.loja.exceptions.UsuarioNotFoundException;
import com.proj.loja.model.Usuario;
import com.proj.loja.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();

    private Usuario usuario;

    @BeforeEach
    public void setUp(){
        usuario = new Usuario((long) 0, null, "Test", "email@email.com", "password", "641.769.970-60", "abc");
    }

    @Test
    public void checkIfAFalseCPFIsValid(){
        assertFalse(usuarioService.isCPFCorreto("111.111.111-11"));
    }

    @Test
    public void checkIfACPFIsValid(){
        assertTrue(usuarioService.isCPFCorreto("641.769.970-60"));
    }

    @Test
    public void checkForAnUsuairoThenThrowException(){
        assertThrows(UsuarioNotFoundException.class, () -> {
            usuarioService.getUsuarioById(10);
        });
    }

    @Test
    public void saveUsuarioThenAssertName(){
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario user = usuarioService.addUsuario(usuario);

        assertNotNull(user.getName());
    }

}
