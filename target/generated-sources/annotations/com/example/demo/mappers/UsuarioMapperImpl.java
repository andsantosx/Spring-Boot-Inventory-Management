package com.example.demo.mappers;

import com.example.demo.domain.Usuario;
import com.example.demo.dtos.UsuarioDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-26T08:04:04-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Ubuntu)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( dto.getId() );
        usuario.setNome( dto.getNome() );

        return usuario;
    }

    @Override
    public UsuarioDTO toDTO(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( entity.getId() );
        usuarioDTO.setNome( entity.getNome() );

        return usuarioDTO;
    }

    @Override
    public void updateEntityFromDTO(UsuarioDTO dto, Usuario entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setNome( dto.getNome() );
    }
}
