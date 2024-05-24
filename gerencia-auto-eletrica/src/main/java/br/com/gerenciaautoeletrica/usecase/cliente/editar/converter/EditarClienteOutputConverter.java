package br.com.gerenciaautoeletrica.usecase.cliente.editar.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.usecase.cliente.editar.EditarClienteOutput;
import lombok.Builder;

@Builder
public class EditarClienteOutputConverter {

    public EditarClienteOutput converter(ClienteEntity clienteEntity){
        return EditarClienteOutput.builder()
                .id(clienteEntity.getId())
                .nome(clienteEntity.getNome())
                .telefone(clienteEntity.getTelefone())
                .cpf(clienteEntity.getCpf())
                .email(clienteEntity.getEmail())
                .build();
    }

}
