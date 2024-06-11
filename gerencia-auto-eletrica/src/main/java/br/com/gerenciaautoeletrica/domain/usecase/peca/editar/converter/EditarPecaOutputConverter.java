package br.com.gerenciaautoeletrica.domain.usecase.peca.editar.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.usecase.peca.editar.EditarPecaOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class EditarPecaOutputConverter {

    public EditarPecaOutput converter(PecaEntity pecaEntity){
        return EditarPecaOutput.builder()
                .id(pecaEntity.getId())
                .preco(pecaEntity.getPreco())
                .nome(pecaEntity.getNome())
                .descricao(pecaEntity.getDescricao())
                .quantidade(pecaEntity.getQuantidade())
                .marca(pecaEntity.getMarca())
                .build();
    }

}
