package com.example.demo.services;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.enums.StatusUsuario;
import com.example.demo.dtos.UsuarioCreateDTO;
import com.example.demo.dtos.UsuarioDTO;
import com.example.demo.mappers.UsuarioMapper;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.exceptions.DataIntegrityViolationException;
import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Usuario findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        return repository.findByStatus(StatusUsuario.ATIVO).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public Usuario create(UsuarioCreateDTO objDTO) {
        if (repository.findByLogin(objDTO.getLogin()).isPresent()) {
            throw new DataIntegrityViolationException("Login já cadastrado na base de dados!");
        }
        objDTO.setSenha(passwordEncoder.encode(objDTO.getSenha()));
        Usuario newObj = mapper.toEntity(objDTO);
        newObj.setStatus(StatusUsuario.ATIVO);
        return repository.save(newObj);
    }

    @Transactional
    public Usuario update(Integer id, UsuarioDTO objDTO) {
        Usuario obj = findById(id);
        if (repository.findByLogin(objDTO.getLogin()).isPresent() && !repository.findByLogin(objDTO.getLogin()).get().getId().equals(id)) {
            throw new DataIntegrityViolationException("Login já cadastrado na base de dados!");
        }
        mapper.updateEntityFromDTO(objDTO, obj);
        return repository.save(obj);
    }

    @Transactional
    public void softDelete(Integer id) {
        Usuario obj = findById(id);
        if (obj.getStatus() == StatusUsuario.INATIVO) {
            throw new DataIntegrityViolationException("Este usuário já está inativo.");
        }
        obj.setStatus(StatusUsuario.INATIVO);
        repository.save(obj);
    }
}
