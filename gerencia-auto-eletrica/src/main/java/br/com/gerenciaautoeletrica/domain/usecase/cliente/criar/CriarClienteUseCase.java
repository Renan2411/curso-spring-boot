package br.com.gerenciaautoeletrica.domain.usecase.cliente.criar;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.criar.converter.CriarClienteOutputConverter;
import br.com.gerenciaautoeletrica.domain.validation.Validator;
import lombok.Builder;

import java.util.Objects;

@Builder
public class CriarClienteUseCase {

    private final IClienteDataProvider iClienteDataProvider;
    private final CriarClienteOutputConverter criarClienteOutputConverter;

    public CriarClienteOutput executar(CriarClienteInput entrada){
        validarEntrada(entrada);

        ClienteEntity clienteEntity = ClienteEntity.builder()
                .nome(entrada.getNome())
                .cpf(entrada.getCpf())
                .email(entrada.getEmail())
                .telefone(entrada.getTelefone())
                .build();

        ClienteEntity cliente = iClienteDataProvider.criar(clienteEntity);

        return criarClienteOutputConverter.converter(cliente);
    }

    private void validarEntrada(CriarClienteInput entrada){
        Validator.of(entrada)
                .validate(Objects.nonNull(entrada.getNome()), "Nome é obrigatório")
                .validate(Objects.nonNull(entrada.getCpf()), "Cnpj é obrigatório")
                .validate(Objects.nonNull(entrada.getEmail()), "Email é obrigatório")
                .validate(Objects.nonNull(entrada.getTelefone()), "Telefone é obrigatório")
                .get();
    }

}
