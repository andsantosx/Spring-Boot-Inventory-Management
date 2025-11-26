package com.example.demo.services;

import com.example.demo.domain.Movimentacao;
import com.example.demo.dtos.MovimentacaoDTO;
import com.example.demo.mappers.MovimentacaoMapper;
import com.example.demo.repositories.MovimentacaoRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private MovimentacaoMapper mapper;

    @Transactional(readOnly = true)
    public Movimentacao findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public Movimentacao create(MovimentacaoDTO objDTO) {
        objDTO.setData(LocalDate.now());
        Movimentacao newObj = mapper.toEntity(objDTO);
        return repository.save(newObj);
    }
}
