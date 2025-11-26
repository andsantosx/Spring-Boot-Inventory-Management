package com.example.demo.mappers;

import com.example.demo.domain.Movimentacao;
import com.example.demo.dtos.MovimentacaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovimentacaoMapper {

    @Mapping(source = "produtoId", target = "produto.id")
    @Mapping(source = "usuarioId", target = "usuario.id")
    Movimentacao toEntity(MovimentacaoDTO dto);

    @Mapping(source = "produto.id", target = "produtoId")
    @Mapping(source = "usuario.id", target = "usuarioId")
    MovimentacaoDTO toDTO(Movimentacao entity);
}
