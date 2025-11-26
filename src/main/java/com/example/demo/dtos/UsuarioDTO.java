package com.example.demo.dtos;

import com.example.demo.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "O campo NOME é requerido.")
    private String nome;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
