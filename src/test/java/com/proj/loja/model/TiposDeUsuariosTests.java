package com.proj.loja.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TiposDeUsuariosTests {
    
    @Test
    public void createATipoUsuario(){
        assertEquals(1, TipoUsuario.Visitante.getId());
    }

}