package com.example.demo.dtos;

import com.example.demo.domain.Movimentacao;
import com.example.demo.domain.enums.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class MovimentacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "O campo TIPO é requerido.")
    private TipoMovimentacao tipo;

    @NotNull(message = "O campo QUANTIDADE é requerido.")
    private Integer quantidade;

    private LocalDate data;

    @NotNull(message = "O campo PRODUTO é requerido.")
    private Integer produtoId;

    @NotNull(message = "O campo USUÁRIO é requerido.")
    private Integer usuarioId;

    public MovimentacaoDTO() {
    }

    public MovimentacaoDTO(Movimentacao obj) {
        this.id = obj.getId();
        this.tipo = obj.getTipo();
        this.quantidade = obj.getQuantidade();
        this.data = obj.getData();
        this.produtoId = obj.getProduto().getId();
        this.usuarioId = obj.getUsuario().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
