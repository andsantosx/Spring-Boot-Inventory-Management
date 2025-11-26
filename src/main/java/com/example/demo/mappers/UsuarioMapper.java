package com.example.demo.mappers;

import com.example.demo.domain.Usuario;
import com.example.demo.dtos.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDTO dto);
    UsuarioDTO toDTO(Usuario entity);
    void updateEntityFromDTO(UsuarioDTO dto, @MappingTarget Usuario entity);
}
