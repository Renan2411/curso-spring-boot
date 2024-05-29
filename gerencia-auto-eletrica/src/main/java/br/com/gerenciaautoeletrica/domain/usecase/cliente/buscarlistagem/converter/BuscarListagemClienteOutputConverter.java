package br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarlistagem.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarlistagem.BuscarListagemClienteOutput;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class BuscarListagemClienteOutputConverter {

    public BuscarListagemClienteOutput converter(List<ClienteEntity> clienteEntityList){

        List<BuscarListagemClienteOutput.Cliente> clientes = new ArrayList<>();

        for(ClienteEntity cliente : clienteEntityList){
            clientes.add(
                    BuscarListagemClienteOutput.Cliente.builder()
                            .cpf(cliente.getCpf())
                            .email(cliente.getEmail())
                            .nome(cliente.getNome())
                            .id(cliente.getId())
                            .telefone(cliente.getTelefone())
                    .build());
        }

        return BuscarListagemClienteOutput.builder().clientes(clientes).build();
    }

}
