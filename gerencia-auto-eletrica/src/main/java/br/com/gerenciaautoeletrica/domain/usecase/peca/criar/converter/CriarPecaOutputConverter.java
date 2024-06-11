package br.com.gerenciaautoeletrica.domain.usecase.peca.criar.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.usecase.peca.criar.CriarPecaOutput;
import lombok.Builder;

@Builder
public class CriarPecaOutputConverter {

    public CriarPecaOutput converter(PecaEntity pecaEntity){
        return CriarPecaOutput.builder()
                .id(pecaEntity.getId())
                .nome(pecaEntity.getNome())
                .descricao(pecaEntity.getDescricao())
                .quantidade(pecaEntity.getQuantidade())
                .marca(pecaEntity.getMarca())
                .preco(pecaEntity.getPreco())
                .build();
    }

}
