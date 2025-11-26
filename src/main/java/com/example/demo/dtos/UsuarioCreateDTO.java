package com.example.demo.dtos;

import com.example.demo.domain.enums.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class UsuarioCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campo LOGIN é requerido.")
    private String login;

    @NotBlank(message = "O campo NOME é requerido.")
    private String nome;

    @NotBlank(message = "O campo SENHA é requerido.")
    private String senha;

    @NotNull(message = "O campo PERFIL é requerido.")
    private Perfil perfil;

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
}
