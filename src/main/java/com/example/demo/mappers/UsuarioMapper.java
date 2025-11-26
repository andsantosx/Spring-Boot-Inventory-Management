package com.example.demo.mappers;

import com.example.demo.domain.Usuario;
import com.example.demo.dtos.UsuarioCreateDTO;
import com.example.demo.dtos.UsuarioDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioCreateDTO dto);
    UsuarioDTO toDTO(Usuario entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    void updateEntityFromDTO(UsuarioDTO dto, @MappingTarget Usuario entity);
}
