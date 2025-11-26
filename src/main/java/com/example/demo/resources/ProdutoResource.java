package com.example.demo.resources;

import com.example.demo.domain.Produto;
import com.example.demo.dtos.ProdutoDTO;
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

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um produto por ID")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {
        Produto obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtos")
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<ProdutoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Cria um novo produto")
    public ResponseEntity<Produto> create(@Valid @RequestBody ProdutoDTO objDTO) {
        Produto newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza um produto existente")
    public ResponseEntity<Produto> update(@PathVariable Integer id, @Valid @RequestBody ProdutoDTO objDTO) {
        Produto newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta um produto")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
