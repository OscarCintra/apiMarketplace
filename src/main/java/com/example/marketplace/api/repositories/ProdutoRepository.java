package com.example.marketplace.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.marketplace.api.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {



}
