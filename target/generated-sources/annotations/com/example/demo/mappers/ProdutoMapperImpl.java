package com.example.demo.mappers;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Localizacao;
import com.example.demo.domain.Produto;
import com.example.demo.dtos.ProdutoDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-03T12:52:45-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Ubuntu)"
)
@Component
public class ProdutoMapperImpl implements ProdutoMapper {

    @Override
    public Produto toEntity(ProdutoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Produto produto = new Produto();

        produto.setCategoria( produtoDTOToCategoria( dto ) );
        produto.setLocalizacao( produtoDTOToLocalizacao( dto ) );
        produto.setId( dto.getId() );
        produto.setSku( dto.getSku() );
        produto.setNome( dto.getNome() );
        produto.setPrecoCusto( dto.getPrecoCusto() );
        produto.setStatus( dto.getStatus() );

        return produto;
    }

    @Override
    public ProdutoDTO toDTO(Produto entity) {
        if ( entity == null ) {
            return null;
        }

        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setCategoriaId( entityCategoriaId( entity ) );
        produtoDTO.setLocalizacaoId( entityLocalizacaoId( entity ) );
        produtoDTO.setId( entity.getId() );
        produtoDTO.setSku( entity.getSku() );
        produtoDTO.setNome( entity.getNome() );
        produtoDTO.setPrecoCusto( entity.getPrecoCusto() );
        produtoDTO.setStatus( entity.getStatus() );

        return produtoDTO;
    }

    @Override
    public void updateEntityFromDTO(ProdutoDTO dto, Produto entity) {
        if ( dto == null ) {
            return;
        }

        if ( entity.getCategoria() == null ) {
            entity.setCategoria( new Categoria() );
        }
        produtoDTOToCategoria1( dto, entity.getCategoria() );
        if ( entity.getLocalizacao() == null ) {
            entity.setLocalizacao( new Localizacao() );
        }
        produtoDTOToLocalizacao1( dto, entity.getLocalizacao() );
        if ( dto.getSku() != null ) {
            entity.setSku( dto.getSku() );
        }
        if ( dto.getNome() != null ) {
            entity.setNome( dto.getNome() );
        }
        if ( dto.getPrecoCusto() != null ) {
            entity.setPrecoCusto( dto.getPrecoCusto() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
    }

    protected Categoria produtoDTOToCategoria(ProdutoDTO produtoDTO) {
        if ( produtoDTO == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setId( produtoDTO.getCategoriaId() );

        return categoria;
    }

    protected Localizacao produtoDTOToLocalizacao(ProdutoDTO produtoDTO) {
        if ( produtoDTO == null ) {
            return null;
        }

        Localizacao localizacao = new Localizacao();

        localizacao.setId( produtoDTO.getLocalizacaoId() );

        return localizacao;
    }

    private Integer entityCategoriaId(Produto produto) {
        if ( produto == null ) {
            return null;
        }
        Categoria categoria = produto.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        Integer id = categoria.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer entityLocalizacaoId(Produto produto) {
        if ( produto == null ) {
            return null;
        }
        Localizacao localizacao = produto.getLocalizacao();
        if ( localizacao == null ) {
            return null;
        }
        Integer id = localizacao.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected void produtoDTOToCategoria1(ProdutoDTO produtoDTO, Categoria mappingTarget) {
        if ( produtoDTO == null ) {
            return;
        }

        if ( produtoDTO.getCategoriaId() != null ) {
            mappingTarget.setId( produtoDTO.getCategoriaId() );
        }
    }

    protected void produtoDTOToLocalizacao1(ProdutoDTO produtoDTO, Localizacao mappingTarget) {
        if ( produtoDTO == null ) {
            return;
        }

        if ( produtoDTO.getLocalizacaoId() != null ) {
            mappingTarget.setId( produtoDTO.getLocalizacaoId() );
        }
    }
}
