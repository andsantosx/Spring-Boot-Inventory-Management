package com.example.demo.dtos;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.enums.Perfil;
import com.example.demo.domain.enums.StatusUsuario;
import java.io.Serializable;

public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String login;
    private String nome;
    private Perfil perfil;
    private StatusUsuario status;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.login = obj.getLogin();
        this.nome = obj.getNome();
        this.perfil = obj.getPerfil();
        this.status = obj.getStatus();
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
}
