package com.example.demo.mappers;

import com.example.demo.domain.Usuario;
import com.example.demo.dtos.UsuarioCreateDTO;
import com.example.demo.dtos.UsuarioDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-03T19:35:33-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Ubuntu)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setLogin( dto.getLogin() );
        usuario.setNome( dto.getNome() );
        usuario.setSenha( dto.getSenha() );
        usuario.setPerfil( dto.getPerfil() );

        return usuario;
    }

    @Override
    public UsuarioDTO toDTO(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( entity.getId() );
        usuarioDTO.setLogin( entity.getLogin() );
        usuarioDTO.setNome( entity.getNome() );
        usuarioDTO.setPerfil( entity.getPerfil() );
        usuarioDTO.setStatus( entity.getStatus() );

        return usuarioDTO;
    }

    @Override
    public void updateEntityFromDTO(UsuarioDTO dto, Usuario entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getLogin() != null ) {
            entity.setLogin( dto.getLogin() );
        }
        if ( dto.getNome() != null ) {
            entity.setNome( dto.getNome() );
        }
        if ( dto.getPerfil() != null ) {
            entity.setPerfil( dto.getPerfil() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
    }
}
