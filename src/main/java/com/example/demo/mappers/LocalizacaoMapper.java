package com.example.demo.mappers;

import com.example.demo.domain.Localizacao;
import com.example.demo.dtos.LocalizacaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LocalizacaoMapper {
    Localizacao toEntity(LocalizacaoDTO dto);
    LocalizacaoDTO toDTO(Localizacao entity);
    void updateEntityFromDTO(LocalizacaoDTO dto, @MappingTarget Localizacao entity);
}
