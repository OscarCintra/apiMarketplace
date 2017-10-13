package com.example.marketplace.api.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.marketplace.api.Similaridade;
import com.example.marketplace.api.entities.Produto;
import com.example.marketplace.api.entities.ProdutoGrupo;
import com.example.marketplace.api.entities.ProdutoLista;
import com.example.marketplace.api.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;	

	public void deleteAll() {
		this.produtoRepository.deleteAll();		
	}
	
	public Produto salvarProduto(Produto produto) {
		return this.produtoRepository.save(produto);		
	}	
	
	public List<Produto> buscarProduto( String filter, String order ) {
		List<Produto> lista = this.produtoRepository.findAll();
		
		lista = filtrarListaProduto(lista, filter);
		lista = ordenarListaProduto(lista, order);		
		return lista;
	}
		
	private List<Produto> filtrarListaProduto(List<Produto> lista, String filter) {
		try {

			if (filter == null || filter.isEmpty())
				return lista;

			String campo[] = filter.split(":");

			switch (campo[0].toLowerCase()) {

			case "id":
				return lista.stream().filter(u -> u.getId().equals(campo[1])).collect(Collectors.toList());

			case "brand":
				return lista.stream().filter(u -> u.getBrand().contains(campo[1])).collect(Collectors.toList());

			case "ean":
				return lista.stream().filter(u -> u.getEan().equals(campo[1])).collect(Collectors.toList());

			case "title":
				return lista.stream().filter(u -> u.getTitle().contains(campo[1])).collect(Collectors.toList());

			case "price":
				return lista.stream().filter(u -> u.getPrice().equals(new BigDecimal(campo[1])))
						.collect(Collectors.toList());

			case "stock":
				return lista.stream().filter(u -> u.getStock() == Integer.parseInt(campo[1]))
						.collect(Collectors.toList());

			default:
				return lista;
			}

		} catch (Exception ex) {
			throw ex;
		}
	}

	private List<Produto> ordenarListaProduto(List<Produto> lista, String order) {

		try {
			if (order == null || order.isEmpty()) {
				Collections.sort(lista, Collections.reverseOrder(new Comparator<Produto>() {

					@Override
					public int compare(Produto p1, Produto p2) {
						int result = (int) (p1.getStock() - p2.getStock());

						return result == 0 ? p2.getPrice().compareTo(p1.getPrice()) : result;
					}
				}));

				return lista;
			}

			if (order.toLowerCase().contains("desc")) {
				Collections.sort(lista, Collections.reverseOrder(new Comparator<Produto>() {

					@Override
					public int compare(Produto p1, Produto p2) {

						return returnCompare(p1, p2, order);

					}
				}));
			} else {
				Collections.sort(lista, new Comparator<Produto>() {

					@Override
					public int compare(Produto p1, Produto p2) {
						return returnCompare(p1, p2, order);
					}
				});
			}

			return lista;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	private int returnCompare(Produto p1, Produto p2, String order) {
		try {
			switch (order.toLowerCase()) {
			case "id":
				return p1.getId().compareTo(p2.getId());
			case "ean":
				return p1.getEan().compareTo(p2.getEan());
			case "title":
				return p1.getTitle().compareTo(p2.getTitle());
			case "brand":
				return p1.getBrand().compareTo(p2.getBrand());
			case "price":
				return p1.getPrice().compareTo(p2.getPrice());
			case "stock":
				return (int) (p1.getStock() - p2.getStock());
			default:
				return (int) (p1.getStock() - p2.getStock());

			}
		} catch (Exception ex) {
			throw ex;
		}
	}
	public List<ProdutoGrupo> organizarListaProduto(List<Produto> produtos)  {
		
		Produto produto;
		ProdutoLista produtoL;
		ProdutoGrupo produtosG;
		List<Produto> listaP =  new ArrayList<>();
		List<ProdutoGrupo> listaG =  new ArrayList<>();
		List<ProdutoLista> listaL =  new ArrayList<>();
		
		
		for (int i = 0; i < produtos.size(); i++) {
			if (verificarListaPorEan(produtos, i)) { 
				produtoL = new ProdutoLista();
				produtoL.setTipo("3-EAN");
				produtoL.setDescricao(produtos.get(i).getEan());
				produtoL.setId(produtos.get(i).getId());
				produtoL.setEan(produtos.get(i).getEan());
				produtoL.setTitle(produtos.get(i).getTitle());
				produtoL.setBrand(produtos.get(i).getBrand());
				produtoL.setPrice(produtos.get(i).getPrice());
				produtoL.setStock(produtos.get(i).getStock());
				listaL.add(produtoL);
			}
		}
		
		for (int i = 0; i < produtos.size(); i++) {
			String titulo =verificarListaPorTitle(produtos, i); 
			if (titulo.length()>0) { 
				produtoL = new ProdutoLista();
				produtoL.setTipo("2-TITULO");
				produtoL.setDescricao(titulo);
				produtoL.setId(produtos.get(i).getId());
				produtoL.setEan(produtos.get(i).getEan());
				produtoL.setTitle(produtos.get(i).getTitle());
				produtoL.setBrand(produtos.get(i).getBrand());
				produtoL.setPrice(produtos.get(i).getPrice());
				produtoL.setStock(produtos.get(i).getStock());
				listaL.add(produtoL);
			}
		}
		
		for (int i = 0; i < produtos.size(); i++) {
			if (verificarListaPorBrand(produtos, i)) { 
				produtoL = new ProdutoLista();
				produtoL.setTipo("1-BRAND");
				produtoL.setDescricao(produtos.get(i).getBrand());
				produtoL.setId(produtos.get(i).getId());
				produtoL.setEan(produtos.get(i).getEan());
				produtoL.setTitle(produtos.get(i).getTitle());
				produtoL.setBrand(produtos.get(i).getBrand());
				produtoL.setPrice(produtos.get(i).getPrice());
				produtoL.setStock(produtos.get(i).getStock());
				listaL.add(produtoL);
			}
		}

		for (int i = 0; i < produtos.size(); i++) {
			if (!verificarListaGPorId(listaL, i)) { 
				produtoL = new ProdutoLista();
				produtoL.setTipo("0-GERAL");
				produtoL.setDescricao("Geral");
				produtoL.setId(produtos.get(i).getId());
				produtoL.setEan(produtos.get(i).getEan());
				produtoL.setTitle(produtos.get(i).getTitle());
				produtoL.setBrand(produtos.get(i).getBrand());
				produtoL.setPrice(produtos.get(i).getPrice());
				produtoL.setStock(produtos.get(i).getStock());
				listaL.add(produtoL);
			}
		}
		
		Collections.sort(listaL, new Comparator<ProdutoLista>() {
	        public int compare(ProdutoLista list2, ProdutoLista list1)
	        {
	            return  list1.getTipo().compareTo(list2.getTipo());
	        }
	    });
		
		produtosG = new ProdutoGrupo();
		String tipo="";
		String descricao ="";
		
		for (int i = 0; i < listaL.size(); i++) {
			
			tipo = listaL.get(i).getTipo();
			descricao = listaL.get(i).getDescricao();
		
			produto = new Produto();
			
			produto.setId(listaL.get(i).getId());
			produto.setEan(listaL.get(i).getEan());
			produto.setTitle(listaL.get(i).getTitle());
			produto.setBrand(listaL.get(i).getBrand());
			produto.setPrice(listaL.get(i).getPrice());
			produto.setStock(listaL.get(i).getStock());
			listaP.add(produto);
			
			
			if (i+1==listaL.size() || !tipo.equals(listaL.get(i+1).getTipo()) || !descricao.equals(listaL.get(i+1).getDescricao())) {
				
				produtosG = new ProdutoGrupo();
				produtosG.setDescription(listaL.get(i).getDescricao());		
				produtosG.setItens(listaP);		
				listaG.add(produtosG);
				
				listaP = new ArrayList<>();
			}
		}
					
		return listaG;
		
	}
	
	
	
	private boolean verificarListaPorEan(List<Produto> lista, int posicao) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getEan().equals(lista.get(posicao).getEan()) && i != posicao)
				return true;				
		}
		return false;
	}
	
	private boolean verificarListaPorBrand(List<Produto> lista, int posicao) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getBrand().equals(lista.get(posicao).getBrand()) && i != posicao)
				return true;				
		}
		return false;
	}
	
	private String verificarListaPorTitle(List<Produto> lista, int posicao) {
		Similaridade simil = new Similaridade();
		for (int i = 0; i < lista.size(); i++) {
			if(simil.checkSimilarity(lista.get(i).getTitle().toString(),lista.get(posicao).getTitle().toString())  >.6  && i != posicao)					
				return (i > posicao) ? lista.get(posicao).getTitle().toString() : lista.get(i).getTitle().toString();  
		}
		return "";
	}	
	
	
	private boolean verificarListaGPorId(List<ProdutoLista> lista, int posicao) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(lista.get(posicao).getId()))
				return true;				
		}
		return false;
	}
}