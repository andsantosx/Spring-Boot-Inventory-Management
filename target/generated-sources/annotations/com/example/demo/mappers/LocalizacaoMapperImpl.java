package com.example.demo.mappers;

import com.example.demo.domain.Localizacao;
import com.example.demo.dtos.LocalizacaoDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-26T08:04:04-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Ubuntu)"
)
@Component
public class LocalizacaoMapperImpl implements LocalizacaoMapper {

    @Override
    public Localizacao toEntity(LocalizacaoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Localizacao localizacao = new Localizacao();

        localizacao.setId( dto.getId() );
        localizacao.setNome( dto.getNome() );

        return localizacao;
    }

    @Override
    public LocalizacaoDTO toDTO(Localizacao entity) {
        if ( entity == null ) {
            return null;
        }

        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();

        localizacaoDTO.setId( entity.getId() );
        localizacaoDTO.setNome( entity.getNome() );

        return localizacaoDTO;
    }

    @Override
    public void updateEntityFromDTO(LocalizacaoDTO dto, Localizacao entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setNome( dto.getNome() );
    }
}
