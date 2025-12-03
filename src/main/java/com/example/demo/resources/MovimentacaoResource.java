package com.example.demo.resources;

import com.example.demo.domain.Movimentacao;
import com.example.demo.dtos.MovimentacaoDTO;
import com.example.demo.mappers.MovimentacaoMapper;
import com.example.demo.services.MovimentacaoService;
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
@RequestMapping(value = "/movimentacoes")
@Tag(name = "Movimentacao", description = "Endpoints para gerenciamento de movimentações")
public class MovimentacaoResource {

    @Autowired
    private MovimentacaoService service;

    @Autowired
    private MovimentacaoMapper mapper;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca uma movimentação por ID")
    public ResponseEntity<MovimentacaoDTO> findById(@PathVariable Integer id) {
        Movimentacao obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

    @GetMapping
    @Operation(summary = "Lista todas as movimentações")
    public ResponseEntity<List<MovimentacaoDTO>> findAll() {
        List<MovimentacaoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Cria uma nova movimentação")
    public ResponseEntity<MovimentacaoDTO> create(@Valid @RequestBody MovimentacaoDTO objDTO) {
        Movimentacao newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDTO(newObj));
    }
}
