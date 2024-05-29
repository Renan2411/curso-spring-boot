package br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarlistagem;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarlistagem.converter.BuscarListagemClienteOutputConverter;
import lombok.Builder;

import java.util.List;

@Builder
public class BuscarListagemClienteUseCase {

    private IClienteDataProvider iClienteDataProvider;
    private BuscarListagemClienteOutputConverter buscarListagemClienteOutputConverter;

    public BuscarListagemClienteOutput executar(){
        List<ClienteEntity> clientes = buscarClientes();

        return buscarListagemClienteOutputConverter.converter(clientes);
    }

    public List<ClienteEntity> buscarClientes(){
        return iClienteDataProvider.buscarListagem();
    }

}
