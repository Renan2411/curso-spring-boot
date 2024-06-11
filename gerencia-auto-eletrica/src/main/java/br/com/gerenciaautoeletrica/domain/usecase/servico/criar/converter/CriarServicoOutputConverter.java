package br.com.gerenciaautoeletrica.domain.usecase.servico.criar.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoPecaEntity;
import br.com.gerenciaautoeletrica.domain.usecase.servico.criar.CriarServicoOutput;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class CriarServicoOutputConverter {

    public CriarServicoOutput converter(ServicoEntity servicoEntity, List<ServicoPecaEntity> servicoPecaEntityList){

        List<CriarServicoOutput.Peca> pecas = new ArrayList<>();

        for(ServicoPecaEntity servicoPecaEntity : servicoPecaEntityList){
            pecas.add(CriarServicoOutput.Peca.builder()
                    .id(servicoPecaEntity.getPeca().getId())
                    .nome(servicoPecaEntity.getPeca().getNome())
                    .descricao(servicoPecaEntity.getPeca().getDescricao())
                    .marca(servicoPecaEntity.getPeca().getMarca())
                    .preco(servicoPecaEntity.getPrecoPeca())
                    .quantidade(servicoPecaEntity.getQuantidade())
                    .build());

        }

        return  CriarServicoOutput.builder()
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
