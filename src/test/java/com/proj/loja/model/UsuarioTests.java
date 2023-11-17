package com.proj.loja.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UsuarioTests {
    
    @Test
    public void whenCreatingClienteAssertIfNameIsTest(){
        Usuario cliente = new Usuario((long) 1, TipoUsuario.CLIENTE, "Test", "email@gmail.com", "password", "123456789");
        assertEquals("Test", cliente.getName());
    }

}
