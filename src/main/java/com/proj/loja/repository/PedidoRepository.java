package com.proj.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proj.loja.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    @Query("select u from Tb_Pedidos u where u.usuario_id = ?1")
    List<Pedido> findAllByUsuarioId(Long usuarioId);
    
}
