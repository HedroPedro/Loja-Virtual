package com.proj.loja.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.proj.loja.model.Produto;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(value = TxType.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProdutoRepositoryTests {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private Produto produto;

    @BeforeEach
    public void setUp(){
        produto = new Produto(null, "Alvejante", 2.5f, 10);
        entityManager.persistAndFlush(produto);
    }

    @Test
    public void checkIfCanGetProdutoThenAssertIfNamesAreEquals(){
        Produto prod = repository.findAll().get(0);
        LOGGER.info(prod.getName() + " : " + prod.getId());
        assertTrue(() -> prod.getName().equals(produto.getName()));
    }

    @Test
    public void checkIfProdutoIsUpdateThenAssertNewName(){
        Produto prod = repository.findAll().get(0);
        prod.setName("Janela");
        repository.save(prod);
        Produto novoProduto = repository.findById(prod.getId()).get();
        assertEquals(novoProduto.getName(), prod.getName());
    }

    @Test
    public void checkIfThrowsExceptionAfterDeletingProduto(){
        Produto prod = repository.findAll().get(0);
        repository.delete(prod);
        assertThrows(NoSuchElementException.class, () -> repository.findById((long) 0).get());
    }
}
