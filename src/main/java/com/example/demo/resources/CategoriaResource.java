package com.example.demo.resources;

import com.example.demo.domain.Categoria;
import com.example.demo.dtos.CategoriaDTO;
import com.example.demo.services.CategoriaService;
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
@RequestMapping(value = "/categorias")
@Tag(name = "Categoria", description = "Endpoints para gerenciamento de categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca uma categoria por ID")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        Categoria obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    @Operation(summary = "Lista todas as categorias")
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Cria uma nova categoria")
    public ResponseEntity<Categoria> create(@Valid @RequestBody CategoriaDTO objDTO) {
        Categoria newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza uma categoria existente")
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO objDTO) {
        Categoria newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta uma categoria")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
