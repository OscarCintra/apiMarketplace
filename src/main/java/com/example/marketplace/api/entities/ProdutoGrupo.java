package com.example.marketplace.api.entities;

import java.util.List;


public class ProdutoGrupo {
	private String description;
	private List<Produto> itens;

	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProdutoGrupo [description=" + description + ", itens=" + itens + "]";
	}
	
}
