package br.com.gerenciaautoeletrica.domain.usecase.peca.buscarListagem.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarListagem.BuscarListagemPecaOutput;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class BuscarListagemPecaOutputConverter {

    public BuscarListagemPecaOutput converter(List<PecaEntity> pecaEntityList){

        if (pecaEntityList.isEmpty()){
            return BuscarListagemPecaOutput.builder().itens(new ArrayList<>()).build();
        }

        List<BuscarListagemPecaOutput.Peca> pecas = new ArrayList<>();

        for (PecaEntity pecaEntity : pecaEntityList){
           pecas.add(BuscarListagemPecaOutput.Peca.builder()
                   .id(pecaEntity.getId())
                   .marca(pecaEntity.getMarca())
                   .preco(pecaEntity.getPreco())
                   .nome(pecaEntity.getNome())
                   .quantidade(pecaEntity.getQuantidade())
                   .descricao(pecaEntity.getDescricao())
                   .build());
        }

        return BuscarListagemPecaOutput.builder().itens(pecas).build();
    }

}
