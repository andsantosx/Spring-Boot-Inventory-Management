package com.example.demo.services;

import com.example.demo.domain.Categoria;
import com.example.demo.dtos.CategoriaDTO;
import com.example.demo.mappers.CategoriaMapper;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.services.exceptions.DataIntegrityViolationException;
import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private CategoriaMapper mapper;

    @Transactional(readOnly = true)
    public Categoria findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public Categoria create(CategoriaDTO objDTO) {
        Categoria newObj = mapper.toEntity(objDTO);
        return repository.save(newObj);
    }

    @Transactional
    public Categoria update(Integer id, CategoriaDTO objDTO) {
        Categoria obj = findById(id);
        mapper.updateEntityFromDTO(objDTO, obj);
        return repository.save(obj);
    }

    @Transactional
    public void delete(Integer id) {
        findById(id);
        if (repository.getById(id).getProdutos().size() > 0) {
            throw new DataIntegrityViolationException("Categoria possui produtos e não pode ser deletada!");
        }
        repository.deleteById(id);
    }
}
