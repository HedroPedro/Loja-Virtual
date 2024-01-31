package com.proj.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.loja.exceptions.InvalidUsuarioException;
import com.proj.loja.exceptions.PedidoNotFoundException;
import com.proj.loja.model.Pedido;
import com.proj.loja.model.TipoUsuario;
import com.proj.loja.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository repository;

    @Override
    public List<Pedido> getPedidos(){
        return repository.findAll();
    }

    @Override
    public List<Pedido> getPedidosByUsuarioId(Long usuarioId){
        return repository.findByUsuarioId(usuarioId);
    }

    @Override
    public Pedido getPedidoById(Long id){
        return repository.findById(id).orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado"));
    }

    @Override
    public Pedido addPedido(Pedido pedido){
        if(!pedido.getUsuario().getTipoUsuario().equals(TipoUsuario.Vendendor)){
            throw new InvalidUsuarioException("Usuario não vendendedor tentou inserir um produto");
        }
        return repository.save(pedido);
    }

    @Override
    public Pedido updatePedido(Pedido pedido) {
        Pedido pedidoToBeUpdated = repository.findById(pedido.getId()).orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado"));
        pedidoToBeUpdated.setDataEntrega(pedido.getDataEntrega());
        pedidoToBeUpdated.setDataCriação(pedido.getDataCriação());
        pedidoToBeUpdated.setProdutos(pedido.getProdutos());
        //pedidoToBeUpdated.setUsuario(pedido.getUsuario());
        return repository.save(pedidoToBeUpdated);
    }

    @Override
    public void deletePedidoById(Long id) {
        repository.deleteById(id);
    }
    
}
