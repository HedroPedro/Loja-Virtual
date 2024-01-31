package com.proj.loja.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.loja.model.Produto;
import com.proj.loja.model.TipoUsuario;
import com.proj.loja.model.Usuario;
import com.proj.loja.service.ProdutoServiceImpl;
import com.proj.loja.service.UsuarioServiceImpl;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(Controller.class)
public class ControllerTests {

    @MockBean
    private UsuarioServiceImpl usuarioService;

    @MockBean
    private ProdutoServiceImpl produtoServiceImpl;

    private ObjectMapper mapper = new ObjectMapper();

    private Usuario user;

    private Produto produto;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        user = new Usuario(1L, TipoUsuario.Cliente, "User", "email@email.com", "password", "111.111.111-11", "aaa");
        produto =  new Produto(0L, "Produto", 15f, 2);
    }

    @DisplayName("Check if name is null because of invalid cpf")
    @Test
    public void createAnInvalidUserThenCheckIfnameisNull(){
        when(usuarioService.addUsuario(any(Usuario.class))).thenReturn(user);
        try {
            mockMvc.perform(post("/usuario")
            .content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.name").value(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeletingAssertSucessMessage(){
        try{
            mockMvc.perform(delete("/usuario/0")
            .content(mapper.writeValueAsString(Long.valueOf(0)))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string("Usuario deletado"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenGettingUsuarioByIdAssertItsName(){
        when(usuarioService.getUsuarioById(anyLong())).thenReturn(new Usuario(0L, TipoUsuario.Cliente, "User", "email@email.com", "senha", "111.111.111-11", "aaa"));
        try{
            mockMvc.perform(get("/usuario/0")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("User"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenUpdatingAInvallidUsuarioCheckIfItsNameIsNull(){
        when(usuarioService.updateUsuario(any(Usuario.class))).thenReturn(null);
        try{
            mockMvc.perform(patch("/usuario").content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.name").value(null));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenGettingAProdutoAssertItsPreco(){
        when(produtoServiceImpl.getProdutoById(anyLong())).thenReturn(produto);
        try {
            mockMvc.perform(get("/produto/0")).andExpect(status().isOk())
            .andExpect(jsonPath("$.preco").value(15f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenCreatingAProdutoAssertItsName(){
        when(produtoServiceImpl.addProduto(any(Produto.class), any(Usuario.class))).thenReturn(produto);
        try{
            mockMvc.perform(post("/produto").content(mapper.writeValueAsString(produto)).content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Produto"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenUpdatingAProdutoAssertItsPreco(){
        produto.setPreco(18f);
        when(produtoServiceImpl.updateProduto(any(Produto.class), any(TipoUsuario.class))).thenReturn(produto);
        try {
            mockMvc.perform(patch("/produto").content(mapper.writeValueAsString(produto))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andExpect(jsonPath("$.preco").value(18f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeletingProdutoByIdAssertDeletingMessage(){
        try {
            mockMvc.perform(delete("/produto/0")).andExpect(status().isOk()).andExpect(content().string("0 deletado"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
