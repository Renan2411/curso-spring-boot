package br.com.gerenciaautoeletrica.domain.usecase.servico.editar.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoPecaEntity;
import br.com.gerenciaautoeletrica.domain.usecase.servico.criar.CriarServicoOutput;
import br.com.gerenciaautoeletrica.domain.usecase.servico.editar.EditarServicoInput;
import br.com.gerenciaautoeletrica.domain.usecase.servico.editar.EditarServicoOutput;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class EditarServicoOutputConverter {

    public EditarServicoOutput converter(ServicoEntity servicoEntity, List<ServicoPecaEntity> servicoPecaEntityList){

        List<EditarServicoOutput.Peca> pecas = new ArrayList<>();

        for(ServicoPecaEntity servicoPecaEntity : servicoPecaEntityList){
            pecas.add(EditarServicoOutput.Peca.builder()
                    .id(servicoPecaEntity.getPeca().getId())
                    .nome(servicoPecaEntity.getPeca().getNome())
                    .descricao(servicoPecaEntity.getPeca().getDescricao())
                    .marca(servicoPecaEntity.getPeca().getMarca())
                    .preco(servicoPecaEntity.getPrecoPeca())
                    .quantidade(servicoPecaEntity.getQuantidade())
                    .build());

        }

        return  EditarServicoOutput.builder()
                .id(servicoEntity.getId())
                .cliente(servicoEntity.getCliente())
                .pago(servicoEntity.getPago())
                .data(servicoEntity.getData())
                .valorServico(servicoEntity.getValorServico())
                .valorTotal(servicoEntity.getValorTotal())
                .pecas(pecas)
                .build();
    }

}
