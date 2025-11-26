package com.example.demo.repositories;

import com.example.demo.domain.Produto;
import com.example.demo.domain.enums.StatusProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findBySku(String sku);
    List<Produto> findByStatus(StatusProduto status);
}
