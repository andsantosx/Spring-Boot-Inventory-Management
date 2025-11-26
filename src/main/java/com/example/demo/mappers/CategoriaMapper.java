package com.example.demo.mappers;

import com.example.demo.domain.Categoria;
import com.example.demo.dtos.CategoriaDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toEntity(CategoriaDTO dto);
    CategoriaDTO toDTO(Categoria entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(CategoriaDTO dto, @MappingTarget Categoria entity);
}
