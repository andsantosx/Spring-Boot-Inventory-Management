package com.example.demo.resources;

import com.example.demo.domain.Localizacao;
import com.example.demo.dtos.LocalizacaoDTO;
import com.example.demo.services.LocalizacaoService;
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
@RequestMapping(value = "/localizacoes")
@Tag(name = "Localizacao", description = "Endpoints para gerenciamento de localizações")
public class LocalizacaoResource {

    @Autowired
    private LocalizacaoService service;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca uma localização por ID")
    public ResponseEntity<Localizacao> findById(@PathVariable Integer id) {
        Localizacao obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    @Operation(summary = "Lista todas as localizações")
    public ResponseEntity<List<LocalizacaoDTO>> findAll() {
        List<LocalizacaoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Cria uma nova localização")
    public ResponseEntity<Localizacao> create(@Valid @RequestBody LocalizacaoDTO objDTO) {
        Localizacao newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza uma localização existente")
    public ResponseEntity<Localizacao> update(@PathVariable Integer id, @Valid @RequestBody LocalizacaoDTO objDTO) {
        Localizacao newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta uma localização")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
