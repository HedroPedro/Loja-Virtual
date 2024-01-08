package com.proj.loja.service;

import java.util.List;

import com.proj.loja.model.Produto;

public interface ProdutoService {
    public List<Produto> getProdutos();

    public Produto getProdutoById(long id);

    public Produto addProduto(Produto produto);

    public void deleteProduto(Long id);

    public Produto updateProduto(Produto produtoUpdated);
}
