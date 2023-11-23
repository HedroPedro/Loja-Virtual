package com.proj.loja.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.proj.loja.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    public void checkIfAFalseCPFIsValid(){
        assertFalse(usuarioService.isCPFCorreto("111.111.111-11\0"));
    }

    @Test
    public void checkIfACPFIsValid(){
        assertTrue(usuarioService.isCPFCorreto("641.769.970-60"));
    }
}
