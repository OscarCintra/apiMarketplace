package com.example.marketplace.api.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.marketplace.api.entities.Produto;
import com.example.marketplace.api.entities.ProdutoGrupo;
import com.example.marketplace.api.response.Response;
import com.example.marketplace.api.services.ProdutoService;


@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")

public class ProdutoController {


	@Autowired
	private ProdutoService produtoService;
	

	@RequestMapping(path = "/produtos", method = RequestMethod.POST)
	public ResponseEntity<Response<List<ProdutoGrupo>>> postProdutos(@RequestBody List<Produto> lista,
			@RequestParam(value = "order_by", required = false) final String order,
			@RequestParam(value = "filter", required = false) final String filter,		
			BindingResult result) throws Exception  {

		Response<List<ProdutoGrupo>> response = new Response<List<ProdutoGrupo>>();

		if (result.hasErrors()) {			
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.produtoService.deleteAll();
		
		for (Produto produto : lista) {
			this.produtoService.salvarProduto(produto);	
		}
		
		lista = this.produtoService.buscarProduto (filter, order) ;
		
		response.setData(this.produtoService.organizarListaProduto(lista));
		return ResponseEntity.ok(response);
	}
		
}
