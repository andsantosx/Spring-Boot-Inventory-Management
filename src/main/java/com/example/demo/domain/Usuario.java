package com.example.demo.domain;

import com.example.demo.domain.enums.Perfil;
import com.example.demo.domain.enums.StatusUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String login;
    private String nome;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @Enumerated(EnumType.STRING)
    private StatusUsuario status;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Movimentacao> movimentacoes = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Integer id, String login, String nome, String senha, Perfil perfil, StatusUsuario status) {
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.perfil = perfil;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }



    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
