package com.proj.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.loja.model.Usuario;
import com.proj.loja.service.UsuarioServiceImpl;

@RestController

public class Controller {
    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    @GetMapping(path = "/usuario")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        List<Usuario> usuarioList = usuarioService.getUsuarios();
        return ResponseEntity.ok(usuarioList);
    }

    @PostMapping(path = "/usuario")
    public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario){
        if(!usuarioService.addUsuario(usuario))
            return new ResponseEntity<String>("CPF inválido", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(usuario.toString() + " adicionado");
    }

    @GetMapping(path = "/usuario/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable long id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }
}
