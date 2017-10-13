package com.example.marketplace.api.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.marketplace.api.entities.Produto;
import com.example.marketplace.api.repositories.ProdutoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProdutoServiceTest {

	@MockBean
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;

	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.produtoRepository.save(Mockito.any(Produto.class))).willReturn(new Produto());
	}

	
	@Test
	public void testSalvarProduto() {
		Produto produto = this.produtoService.salvarProduto(new Produto());

		assertNotNull(produto);
	}
	

}
