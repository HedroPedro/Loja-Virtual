package com.proj.loja.model;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TiposDeUsuariosTests {
    
    @Test
    public void checkIfClienteIsNotTwo(){
        assertNotEquals(2, TipoUsuario.CLIENTE.getId());
    }

}