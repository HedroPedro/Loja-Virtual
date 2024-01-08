package com.proj.loja.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proj.loja.model.Produto;
import com.proj.loja.model.Usuario;
import com.proj.loja.service.ProdutoServiceImpl;
import com.proj.loja.service.UsuarioServiceImpl;

@RestController

public class Controller {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ProdutoServiceImpl produtoService;
    
    @GetMapping(path = "/usuario")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class); 

    @PostMapping(path = "/usuario")
    public ResponseEntity addUsuario(@RequestBody Usuario usuario){
        if(usuarioService.addUsuario(usuario) == null)
            return new ResponseEntity<Usuario>(new Usuario(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping(path = "/usuario/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable long id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping(path = "/usuario")
    public ResponseEntity deleteUsuario(@RequestBody Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok("Usuario deletado");
    }

    @PatchMapping(path = "/usuario")
    public ResponseEntity updateUsuario(@RequestBody Usuario usuario){
        Usuario userUpdated = usuarioService.updateUsuario(usuario);
        if(userUpdated == null){
            LOGGER.info("Estou aqui");
            return new ResponseEntity<Usuario>(new Usuario(), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info(userUpdated.toString());
        return ResponseEntity.ok(userUpdated.getCPF());
    }

    @GetMapping(path = "/produto")
    public ResponseEntity<List<Produto>> getProdutos(){
        return ResponseEntity.ok(produtoService.getProdutos());
    }

    @GetMapping(path = "/produto/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.getProdutoById(id));
    }

    @PostMapping(path = "/produto")
    public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
        return ResponseEntity.ok(produtoService.addProduto(produto));
    }

    @PatchMapping(path = "/produto")
    public ResponseEntity updateProduto(@RequestBody Produto produto){
        Produto produtoUpdated = produtoService.updateProduto(produto);
        if(produtoUpdated == null)
            return ResponseEntity.badRequest().body("Id inválido");
        LOGGER.info("Estou aqui");
        return ResponseEntity.ok(produtoUpdated);
    }

    @DeleteMapping(path = "/produto/{id}")
    public ResponseEntity deleteProduto(@PathVariable Long id){
        produtoService.deleteProduto(id);
        return ResponseEntity.ok(id + " deletado");
    }
}
