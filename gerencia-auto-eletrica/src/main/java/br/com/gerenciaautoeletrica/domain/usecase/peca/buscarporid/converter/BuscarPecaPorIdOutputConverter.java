package br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid.BuscarPecaPorIdOutput;
import lombok.Builder;

@Builder
public class BuscarPecaPorIdOutputConverter {

    public BuscarPecaPorIdOutput converter(PecaEntity pecaEntity){
        return BuscarPecaPorIdOutput.builder()
                .id(pecaEntity.getId())
                .nome(pecaEntity.getNome())
                .preco(pecaEntity.getPreco())
                .descricao(pecaEntity.getDescricao())
                .marca(pecaEntity.getMarca())
                .quantidade(pecaEntity.getQuantidade())
                .build();
    }

}
