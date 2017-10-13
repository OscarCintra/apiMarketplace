package com.example.marketplace.api.entities;


public class ProdutoLista extends Produto {

	private String Tipo;
	private String Descricao;
	

	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	@Override
	public String toString() {
		return "ProdutoLista [Tipo=" + Tipo + ", Descricao=" + Descricao + ", getId()=" + getId() + ", getEan()="
				+ getEan() + ", getTitle()=" + getTitle() + ", getBrand()=" + getBrand() + ", getPrice()=" + getPrice()
				+ ", getStock()=" + getStock() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}



}




