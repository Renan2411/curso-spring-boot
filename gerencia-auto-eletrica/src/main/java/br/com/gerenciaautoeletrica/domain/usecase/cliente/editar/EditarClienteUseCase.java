package br.com.gerenciaautoeletrica.domain.usecase.cliente.editar;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.domain.exception.generic.GenericValidationException;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.validation.Validator;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.editar.converter.EditarClienteOutputConverter;
import lombok.Builder;

import java.util.Objects;

@Builder
public class EditarClienteUseCase {

    private final IClienteDataProvider iClienteDataProvider;
    private final EditarClienteOutputConverter editarClienteOutputConverter;

    public EditarClienteOutput executar(Long idCliente, EditarClienteInput entrada) {
        validarEntrada(entrada);

        ClienteEntity clienteEntity = buscarCliente(idCliente);

        atualizarDadosCliente(entrada, clienteEntity);
        ClienteEntity clienteSalvo = editar(clienteEntity);

        return editarClienteOutputConverter.converter(clienteSalvo);
    }


    public void validarEntrada(EditarClienteInput entrada) {
        Validator.of(entrada)
                .validate(Objects.nonNull(entrada.getNome()), "Nome é obrigatório")
                .validate(Objects.nonNull(entrada.getEmail()), "Email é obrigatório")
                .validate(Objects.nonNull(entrada.getTelefone()), "Telefone é obrigatório")
                .get();
    }

    public ClienteEntity buscarCliente(Long idCliente) {
        return iClienteDataProvider.buscarPorId(idCliente)
                .orElseThrow(() -> new GenericValidationException("Cliente não encontrado"));
    }

    public void atualizarDadosCliente(EditarClienteInput entrada, ClienteEntity clienteEntity){
        clienteEntity.setNome(entrada.getNome());
        clienteEntity.setTelefone(entrada.getTelefone());
        clienteEntity.setEmail(entrada.getEmail());
    }

    public ClienteEntity editar(ClienteEntity clienteEntity){
        return iClienteDataProvider.editar(clienteEntity);
    }

}
