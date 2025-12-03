package com.example.demo.services;

import com.example.demo.domain.Produto;
import com.example.demo.domain.enums.StatusProduto;
import com.example.demo.domain.enums.TipoMovimentacao;
import com.example.demo.dtos.ProdutoDTO;
import com.example.demo.mappers.ProdutoMapper;
import com.example.demo.repositories.ProdutoRepository;
import com.example.demo.services.exceptions.DataIntegrityViolationException;
import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoMapper mapper;

    @Transactional(readOnly = true)
    public Produto findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll() {
        return repository.findByStatus(StatusProduto.ATIVO).stream().map(produto -> {
            ProdutoDTO dto = mapper.toDTO(produto);
            dto.setSaldo(getSaldo(produto));
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public Produto create(ProdutoDTO objDTO) {
        if (repository.findBySku(objDTO.getSku()).isPresent()) {
            throw new DataIntegrityViolationException("SKU já cadastrado na base de dados!");
        }
        objDTO.setStatus(StatusProduto.ATIVO);
        Produto newObj = mapper.toEntity(objDTO);
        return repository.save(newObj);
    }

    @Transactional
    public Produto update(Integer id, ProdutoDTO objDTO) {
        Produto obj = findById(id);
        if (repository.findBySku(objDTO.getSku()).isPresent() && !repository.findBySku(objDTO.getSku()).get().getId().equals(id)) {
            throw new DataIntegrityViolationException("SKU já cadastrado na base de dados!");
        }
        mapper.updateEntityFromDTO(objDTO, obj);
        return repository.save(obj);
    }

    @Transactional
    public void softDelete(Integer id) {
        Produto obj = findById(id);
        if (obj.getStatus() == StatusProduto.INATIVO) {
            throw new DataIntegrityViolationException("Este produto já está inativo.");
        }
        obj.setStatus(StatusProduto.INATIVO);
        repository.save(obj);
    }

    private Integer getSaldo(Produto produto) {
        int entradas = produto.getMovimentacoes().stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.ENTRADA)
                .mapToInt(m -> m.getQuantidade())
                .sum();
        int saidas = produto.getMovimentacoes().stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.SAIDA)
                .mapToInt(m -> m.getQuantidade())
                .sum();
        return entradas - saidas;
    }
}
