package br.com.gerenciaautoeletrica.usecase.cliente.criar.converter;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.usecase.cliente.criar.CriarClienteOutput;
import lombok.Builder;

@Builder
public class CriarClienteOutputConverter {

    public CriarClienteOutput converter(ClienteEntity cliente){
        return CriarClienteOutput.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();
    }

}
