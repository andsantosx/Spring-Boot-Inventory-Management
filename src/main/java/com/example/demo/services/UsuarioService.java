package com.example.demo.services;

import com.example.demo.domain.Usuario;
import com.example.demo.dtos.UsuarioDTO;
import com.example.demo.mappers.UsuarioMapper;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.exceptions.DataIntegrityViolationException;
import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    @Transactional(readOnly = true)
    public Usuario findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public Usuario create(UsuarioDTO objDTO) {
        Usuario newObj = mapper.toEntity(objDTO);
        return repository.save(newObj);
    }

    @Transactional
    public Usuario update(Integer id, UsuarioDTO objDTO) {
        Usuario obj = findById(id);
        mapper.updateEntityFromDTO(objDTO, obj);
        return repository.save(obj);
    }

    @Transactional
    public void delete(Integer id) {
        findById(id);
        if (repository.getById(id).getMovimentacoes().size() > 0) {
            throw new DataIntegrityViolationException("Usuário possui movimentações e não pode ser deletado!");
        }
        repository.deleteById(id);
    }
}
