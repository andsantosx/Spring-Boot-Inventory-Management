package com.example.demo.mappers;

import com.example.demo.domain.Movimentacao;
import com.example.demo.domain.Produto;
import com.example.demo.domain.Usuario;
import com.example.demo.dtos.MovimentacaoDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-03T19:35:33-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Ubuntu)"
)
@Component
public class MovimentacaoMapperImpl implements MovimentacaoMapper {

    @Override
    public Movimentacao toEntity(MovimentacaoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setProduto( movimentacaoDTOToProduto( dto ) );
        movimentacao.setUsuario( movimentacaoDTOToUsuario( dto ) );
        movimentacao.setId( dto.getId() );
        movimentacao.setTipo( dto.getTipo() );
        movimentacao.setQuantidade( dto.getQuantidade() );
        movimentacao.setData( dto.getData() );

        return movimentacao;
    }

    @Override
    public MovimentacaoDTO toDTO(Movimentacao entity) {
        if ( entity == null ) {
            return null;
        }

        MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();

        movimentacaoDTO.setProdutoId( entityProdutoId( entity ) );
        movimentacaoDTO.setUsuarioId( entityUsuarioId( entity ) );
        movimentacaoDTO.setId( entity.getId() );
        movimentacaoDTO.setTipo( entity.getTipo() );
        movimentacaoDTO.setQuantidade( entity.getQuantidade() );
        movimentacaoDTO.setData( entity.getData() );

        return movimentacaoDTO;
    }

    protected Produto movimentacaoDTOToProduto(MovimentacaoDTO movimentacaoDTO) {
        if ( movimentacaoDTO == null ) {
            return null;
        }

        Produto produto = new Produto();

        produto.setId( movimentacaoDTO.getProdutoId() );

        return produto;
    }

    protected Usuario movimentacaoDTOToUsuario(MovimentacaoDTO movimentacaoDTO) {
        if ( movimentacaoDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( movimentacaoDTO.getUsuarioId() );

        return usuario;
    }

    private Integer entityProdutoId(Movimentacao movimentacao) {
        if ( movimentacao == null ) {
            return null;
        }
        Produto produto = movimentacao.getProduto();
        if ( produto == null ) {
            return null;
        }
        Integer id = produto.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer entityUsuarioId(Movimentacao movimentacao) {
        if ( movimentacao == null ) {
            return null;
        }
        Usuario usuario = movimentacao.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Integer id = usuario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
