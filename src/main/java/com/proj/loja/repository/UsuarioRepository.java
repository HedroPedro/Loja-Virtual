package com.proj.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.proj.loja.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByName(String name);

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByCPF(String cpf);
}
