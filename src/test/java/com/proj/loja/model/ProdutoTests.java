package com.proj.loja.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProdutoTests {

    @Test
    public void AfterCreatingProdutoAssertIfItsNameIsTest(){
        Produto produto = new Produto((long) 1, "Test", 12.5f, 4);
        assertEquals("Test", produto.getName());
    }
}
