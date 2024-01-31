package com.proj.loja.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.loja.exceptions.InvalidUsuarioException;
import com.proj.loja.exceptions.ProdutoNotFoundException;
import com.proj.loja.model.Produto;
import com.proj.loja.model.TipoUsuario;
import com.proj.loja.model.Usuario;
import com.proj.loja.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    @Autowired
    private ProdutoRepository repository;

    @Override
    public Produto getProdutoById(long id) {
        return repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto de id " + id + " não encontrado"));
    }

    @Override
    public Produto addProduto(Produto produto, Usuario usuario){
        if(!usuario.getTipoUsuario().equals(TipoUsuario.Vendendor))
            throw new InvalidUsuarioException("Usuario com nível de segurança errado");
        return repository.save(produto);
    }

    @Override
    public void deleteProduto(Long id){
        repository.deleteById(id);
    }

    @Override
    public Produto updateProduto(Produto produtoUpdated, TipoUsuario tipo){
        if(tipo.equals(TipoUsuario.Vendendor))
            throw new InvalidUsuarioException("Usuario nao vendendor");
        Produto prod = repository.findById(produtoUpdated.getId()).orElseThrow(() -> new ProdutoNotFoundException("Produto de id " + produtoUpdated.getId() + " não encontrado"));
        if(prod == null)
            return null;
        prod.setName(produtoUpdated.getName());
        prod.setPreco(produtoUpdated.getPreco());
        prod.setQuantd(produtoUpdated.getQuantd());
        return repository.save(prod);
    }

    @Override
    public List<Produto> getProdutos(){
        return repository.findAll();
    }

    public void updateProdutoQuantidade(Produto produto) {
        Produto prodToBeUpdated = repository.findById(produto.getId()).orElseThrow(() -> new ProdutoNotFoundException("Produto de id " + produto.getId() + " nao encontrado"));
        prodToBeUpdated.setQuantd(prodToBeUpdated.getQuantd() - produto.getQuantd());
        repository.save(prodToBeUpdated);
    }
    
}
