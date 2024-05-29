package br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarporid.conveter;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarporid.BuscarClientePorIdOutput;
import lombok.Builder;

@Builder
public class BuscarClientePorIdOutputConverter {

    public BuscarClientePorIdOutput converter(ClienteEntity clienteEntity){
        return BuscarClientePorIdOutput
                .builder()
                .id(clienteEntity.getId())
                .nome(clienteEntity.getNome())
                .cpf(clienteEntity.getCpf())
                .telefone(clienteEntity.getTelefone())
                .email(clienteEntity.getEmail())
                .build();
    }

}
