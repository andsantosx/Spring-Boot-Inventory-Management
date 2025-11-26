package com.example.demo.resources;

import com.example.demo.domain.Usuario;
import com.example.demo.dtos.UsuarioCreateDTO;
import com.example.demo.dtos.UsuarioDTO;
import com.example.demo.mappers.UsuarioMapper;
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

    @Autowired
    private UsuarioMapper mapper;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um usuário por ID")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
        Usuario obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários ativos")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Cria um novo usuário")
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioCreateDTO objDTO) {
        Usuario newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDTO(newObj));
    }

    @PatchMapping(value = "/{id}")
    @Operation(summary = "Atualiza parcialmente um usuário existente")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody UsuarioDTO objDTO) {
        Usuario newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(mapper.toDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Inativa um usuário (Soft Delete)")
    public ResponseEntity<Void> softDelete(@PathVariable Integer id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
