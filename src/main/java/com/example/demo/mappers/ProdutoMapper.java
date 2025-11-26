package com.example.demo.mappers;

import com.example.demo.domain.Produto;
import com.example.demo.dtos.ProdutoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(source = "categoriaId", target = "categoria.id")
    @Mapping(source = "localizacaoId", target = "localizacao.id")
    Produto toEntity(ProdutoDTO dto);

    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "localizacao.id", target = "localizacaoId")
    ProdutoDTO toDTO(Produto entity);

    @Mapping(source = "categoriaId", target = "categoria.id")
    @Mapping(source = "localizacaoId", target = "localizacao.id")
    void updateEntityFromDTO(ProdutoDTO dto, @MappingTarget Produto entity);
}
