package com.example.demo.repositories;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.enums.StatusUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByLogin(String login);
    List<Usuario> findByStatus(StatusUsuario status);
}
