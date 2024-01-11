package com.proj.loja.service;

import java.util.List;

import com.proj.loja.model.Produto;

public interface ProdutoService {

     /**
     * Get all the produtos saved in the database
     * @return a list with all the produtos inside the databse 
     */
    public List<Produto> getProdutos();

     /**
     * Returns the desired produto inside the database by its id, if Produto is not found then returns null
     * @param id The id of the Produto
     * @return produto if found or null if not found
     */
    public Produto getProdutoById(long id);

     /**
     * Save the desired produto in the database
     * @param produto The produto to save in the database
     * @return the saved produto
     */
    public Produto addProduto(Produto produto);
    
    /**
     * Delete the produto by its id, if the id is invalid the request is ignored by the repository
     * @param id the id of the produto that will be deleted
     */
    public void deleteProduto(Long id);

     /**
     * Updates a produto in databse, if can't find the original produto by the databse then returns null
     * @param produtoUpdated the produto that will be updated in the database
     * @return the updated produto
     */
    public Produto updateProduto(Produto produtoUpdated);
}
