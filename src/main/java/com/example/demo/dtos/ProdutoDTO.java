package com.example.demo.dtos;

import com.example.demo.domain.Produto;
import com.example.demo.domain.enums.StatusProduto;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String sku;
    private String nome;
    private BigDecimal precoCusto;
    private StatusProduto status;
    private Integer categoriaId;
    private Integer localizacaoId;
    private Integer saldo;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto obj) {
        this.id = obj.getId();
        this.sku = obj.getSku();
        this.nome = obj.getNome();
        this.precoCusto = obj.getPrecoCusto();
        this.status = obj.getStatus();
        this.categoriaId = obj.getCategoria().getId();
        this.localizacaoId = obj.getLocalizacao().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public StatusProduto getStatus() {
        return status;
    }

    public void setStatus(StatusProduto status) {
        this.status = status;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getLocalizacaoId() {
        return localizacaoId;
    }

    public void setLocalizacaoId(Integer localizacaoId) {
        this.localizacaoId = localizacaoId;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }
}
