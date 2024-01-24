package com.proj.loja.service;

import java.util.List;

import com.proj.loja.model.Pedido;

public interface PedidoService {
    List<Pedido> getPedidos();

    List<Pedido> getPedidosByUsuarioId(Long usuarioId);

    Pedido addPedido(Pedido pedido);

    Pedido getPedidoById(Long id);

    Pedido updatePedido(Pedido pedido);

    void deletePedidoById(Long id);
}
