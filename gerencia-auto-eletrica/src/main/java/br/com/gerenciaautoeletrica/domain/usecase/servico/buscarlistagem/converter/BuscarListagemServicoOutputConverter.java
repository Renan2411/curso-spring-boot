package br.com.gerenciaautoeletrica.domain.usecase.servico.buscarlistagem.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarlistagem.BuscarListagemServicoOutput;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class BuscarListagemServicoOutputConverter {

    public BuscarListagemServicoOutput converter(List<ServicoEntity> servicoEntityList){
        List<BuscarListagemServicoOutput.Servico> servicos = new ArrayList<>();

        for(ServicoEntity servicoEntity :  servicoEntityList){
            servicos.add(BuscarListagemServicoOutput.Servico.builder()
                    .id(servicoEntity.getId())
                    .pago(servicoEntity.getPago())
                    .data(servicoEntity.getData())
                    .valorServico(servicoEntity.getValorServico())
                    .valorTotal(servicoEntity.getValorTotal())
                    .nomeCliente(servicoEntity.getCliente().getNome())
                    .build());
        }

        return BuscarListagemServicoOutput.builder().itens(servicos).build();
    }

}
