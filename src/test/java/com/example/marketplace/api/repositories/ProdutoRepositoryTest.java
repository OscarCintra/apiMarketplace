package com.example.marketplace.api.repositories;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.marketplace.api.entities.Produto;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProdutoRepositoryTest {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Before
	public void setUp() throws Exception {
		this.produtoRepository.deleteAll();
		
		Produto produto = new Produto();
		produto.setBrand("marca Teste");
		produto.setEan("7892");
		produto.setId("99977");
		this.produtoRepository.save(produto);
	}
	
	@After
    public final void tearDown() { 
		this.produtoRepository.deleteAll();
	}

	@Test
	public void testBuscarProduto() {
		List<Produto> produto = this.produtoRepository.findAll() ;			
		assertTrue(!produto.isEmpty());
	}
	
	
}

