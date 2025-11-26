package com.example.demo.resources;

import com.example.demo.domain.Produto;
import com.example.demo.dtos.ProdutoDTO;
import com.example.demo.mappers.ProdutoMapper;
import com.example.demo.services.ProdutoService;
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
@RequestMapping(value = "/produtos")
@Tag(name = "Produto", description = "Endpoints para gerenciamento de produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoMapper mapper;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um produto por ID")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id) {
        Produto obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtos ativos")
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<ProdutoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Cria um novo produto")
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO objDTO) {
        Produto newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDTO(newObj));
    }

    @PatchMapping(value = "/{id}")
    @Operation(summary = "Atualiza parcialmente um produto existente")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Integer id, @RequestBody ProdutoDTO objDTO) {
        Produto newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(mapper.toDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Inativa um produto (Soft Delete)")
    public ResponseEntity<Void> softDelete(@PathVariable Integer id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
