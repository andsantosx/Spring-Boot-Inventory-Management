package com.example.demo.dtos;

import com.example.demo.domain.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "O campo SKU é requerido.")
    private String sku;

    @NotBlank(message = "O campo NOME é requerido.")
    private String nome;

    @NotNull(message = "O campo PREÇO DE CUSTO é requerido.")
    private BigDecimal precoCusto;

    @NotNull(message = "O campo CATEGORIA é requerido.")
    private Integer categoriaId;

    @NotNull(message = "O campo LOCALIZAÇÃO é requerido.")
    private Integer localizacaoId;

    private Integer saldo;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto obj) {
        this.id = obj.getId();
        this.sku = obj.getSku();
        this.nome = obj.getNome();
        this.precoCusto = obj.getPrecoCusto();
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
