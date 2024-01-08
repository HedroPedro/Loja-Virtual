package com.proj.loja.service;

import java.util.List;

import com.proj.loja.model.Usuario;

public interface UsuarioService {
    List<Usuario> getUsuarios();

    Usuario getUsuarioById(long id);

    Usuario addUsuario(Usuario usuario);

    void deleteUsuario(Long id);

    boolean isCPFCorreto(String cpf);

    public boolean isCPFInUse(String cpf);

    boolean isEmailInUseOrInvalid(String email);

    Usuario updateUsuario(Usuario usuario);
}
