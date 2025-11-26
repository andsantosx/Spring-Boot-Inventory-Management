package com.example.demo.resources;

import com.example.demo.domain.Usuario;
import com.example.demo.dtos.UsuarioDTO;
import com.example.demo.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
@Tag(name = "Usuario", description = "Endpoints para gerenciamento de usuários")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um usuário por ID")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
        Usuario obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Cria um novo usuário")
    public ResponseEntity<Usuario> create(@Valid @RequestBody UsuarioDTO objDTO) {
        Usuario newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza um usuário existente")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @Valid @RequestBody UsuarioDTO objDTO) {
        Usuario newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta um usuário")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
