package com.proj.loja.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.loja.model.Usuario;
import com.proj.loja.service.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
public class ControllerTests {

    @MockBean
    private UsuarioServiceImpl usuarioService;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Check if name is null because of invalid cpf")
    @Test
    public void createAnInvalidUserThenCheckIfnameisNull(){
        Usuario user = new Usuario((long) 1, null, "Test", "email@email", "password", "21.23.-12");

        when(usuarioService.addUsuario(any(Usuario.class))).thenReturn(user);
        try {
            mockMvc.perform(post("/usuario")
            .content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andExpect(jsonPath("$.name").value(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}
