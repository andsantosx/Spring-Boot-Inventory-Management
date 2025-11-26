package com.example.demo.services;

import com.example.demo.domain.Localizacao;
import com.example.demo.dtos.LocalizacaoDTO;
import com.example.demo.mappers.LocalizacaoMapper;
import com.example.demo.repositories.LocalizacaoRepository;
import com.example.demo.services.exceptions.DataIntegrityViolationException;
import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository repository;

    @Autowired
    private LocalizacaoMapper mapper;

    @Transactional(readOnly = true)
    public Localizacao findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    @Transactional(readOnly = true)
    public List<LocalizacaoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public Localizacao create(LocalizacaoDTO objDTO) {
        Localizacao newObj = mapper.toEntity(objDTO);
        return repository.save(newObj);
    }

    @Transactional
    public Localizacao update(Integer id, LocalizacaoDTO objDTO) {
        Localizacao obj = findById(id);
        mapper.updateEntityFromDTO(objDTO, obj);
        return repository.save(obj);
    }

    @Transactional
    public void delete(Integer id) {
        findById(id);
        if (repository.getById(id).getProdutos().size() > 0) {
            throw new DataIntegrityViolationException("Localização possui produtos e não pode ser deletada!");
        }
        repository.deleteById(id);
    }
}
