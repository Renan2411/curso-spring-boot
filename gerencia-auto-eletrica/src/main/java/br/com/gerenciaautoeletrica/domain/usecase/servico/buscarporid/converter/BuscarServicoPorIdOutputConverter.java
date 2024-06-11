package br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoPecaEntity;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid.BuscarServicoPorIdOutput;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class BuscarServicoPorIdOutputConverter {

    public BuscarServicoPorIdOutput converter(ServicoEntity servicoEntity, List<ServicoPecaEntity> servicoPecaEntityList) {
        List<BuscarServicoPorIdOutput.Peca> pecas = new ArrayList<>();

        for (ServicoPecaEntity servicoPecaEntity : servicoPecaEntityList) {
            pecas.add(BuscarServicoPorIdOutput.Peca.builder()
                    .id(servicoPecaEntity.getPeca().getId())
                    .nome(servicoPecaEntity.getPeca().getNome())
                    .descricao(servicoPecaEntity.getPeca().getDescricao())
                    .marca(servicoPecaEntity.getPeca().getMarca())
                    .preco(servicoPecaEntity.getPrecoPeca())
                    .quantidade(servicoPecaEntity.getQuantidade())
                    .build());

        }

        return BuscarServicoPorIdOutput.builder()
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
