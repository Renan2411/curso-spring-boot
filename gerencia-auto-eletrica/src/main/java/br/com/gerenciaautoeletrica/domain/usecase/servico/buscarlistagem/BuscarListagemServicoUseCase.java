package br.com.gerenciaautoeletrica.domain.usecase.servico.buscarlistagem;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarlistagem.converter.BuscarListagemServicoOutputConverter;
import lombok.Builder;

import java.util.List;

@Builder
public class BuscarListagemServicoUseCase {

    private final IServicoDataProvider iServicoDataProvider;
    private final BuscarListagemServicoOutputConverter buscarListagemServicoOutputConverter;

    public BuscarListagemServicoOutput executar(){
        return buscarListagemServicoOutputConverter.converter(buscarServicos());
    }

    private List<ServicoEntity> buscarServicos(){
        return iServicoDataProvider.buscarListagem();
    }

}
