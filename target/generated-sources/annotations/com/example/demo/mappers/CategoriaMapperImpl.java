package com.example.demo.mappers;

import com.example.demo.domain.Categoria;
import com.example.demo.dtos.CategoriaDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-26T08:04:04-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Ubuntu)"
)
@Component
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public Categoria toEntity(CategoriaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setId( dto.getId() );
        categoria.setNome( dto.getNome() );

        return categoria;
    }

    @Override
    public CategoriaDTO toDTO(Categoria entity) {
        if ( entity == null ) {
            return null;
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setId( entity.getId() );
        categoriaDTO.setNome( entity.getNome() );

        return categoriaDTO;
    }

    @Override
    public void updateEntityFromDTO(CategoriaDTO dto, Categoria entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setNome( dto.getNome() );
    }
}
