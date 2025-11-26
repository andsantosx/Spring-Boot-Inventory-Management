package com.example.demo.mappers;

import com.example.demo.domain.Localizacao;
import com.example.demo.dtos.LocalizacaoDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface LocalizacaoMapper {
    Localizacao toEntity(LocalizacaoDTO dto);
    LocalizacaoDTO toDTO(Localizacao entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(LocalizacaoDTO dto, @MappingTarget Localizacao entity);
}
