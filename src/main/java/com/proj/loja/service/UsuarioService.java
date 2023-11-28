package com.proj.loja.service;

import java.util.List;

import com.proj.loja.model.Usuario;

public interface UsuarioService {
    List<Usuario> getUsuarios();

    Usuario getUsuarioById(long id);

    Usuario addUsuario(Usuario usuario);

    boolean isCPFCorreto(String CPF);

    boolean isEmailInUseOrInvalid(String email);
}
