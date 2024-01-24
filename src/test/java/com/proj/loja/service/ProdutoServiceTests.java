package com.proj.loja.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proj.loja.model.Produto;
import com.proj.loja.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTests {
    @Mock
    private ProdutoRepository repo;
    
    @InjectMocks
    private ProdutoServiceImpl service;

    private Produto produto;

    @BeforeEach
    public void setUp(){
        produto = new Produto(Long.valueOf(0), "Produto", 12.0f, 2);
    }

    /*@Test
    public void checkForAnProdutoThenAssertAnThrow(){
        assertThrows(NoSuchElementException.class, () -> service.getProdutoById((long) 10));
    }

    @Test
    public void saveProdutoThenAssertIfTheyAreTheSame(){
        when(repo.save(any(Produto.class))).thenReturn(produto);
        Produto prodRepo = service.addProduto(produto);
        assertEquals(produto, prodRepo);
    }

    @Test
    public void deleteProdutoThenAssertNull(){
        service.deleteProduto((long) 0);
        assertNull(service.getProdutoById((long) 0));
    }

    @Test
    public void updateProdutoThenAssertTheNotEqualPrices(){
        when(repo.save(any(Produto.class))).thenReturn(produto);
        when(repo.findById(anyLong())).thenReturn(Optional.of(new Produto((long) 0, "Prod", 12f, 2)));
        Produto prod = service.addProduto(produto);
        produto.setPreco(11f);
        service.updateProduto(produto);
        produto = service.getProdutoById(produto.getId());
        assertNotEquals(prod.getPreco(), produto.getPreco());
    }*/

}