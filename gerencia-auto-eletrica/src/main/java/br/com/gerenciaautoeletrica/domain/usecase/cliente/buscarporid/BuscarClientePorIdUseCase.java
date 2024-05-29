package br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarporid;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.domain.exception.generic.GenericValidationException;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarporid.conveter.BuscarClientePorIdOutputConverter;
import lombok.Builder;

import java.util.Objects;

@Builder
public class BuscarClientePorIdUseCase {

    private IClienteDataProvider iClienteDataProvider;
    private BuscarClientePorIdOutputConverter buscarClientePorIdOutputConverter;

    public BuscarClientePorIdOutput executar(Long idCliente) {
        validarEntrada(idCliente);

        ClienteEntity cliente = buscarPorId(idCliente);

        return buscarClientePorIdOutputConverter.converter(cliente);
    }

    public void validarEntrada(Long idCliente) {
        if (Objects.isNull(idCliente)) {
            throw new GenericValidationException("Ausencia do id do cliente");
        }
    }

    public ClienteEntity buscarPorId(Long id) {
        return iClienteDataProvider.buscarPorId(id)
                .orElseThrow(() -> new GenericValidationException(String.format("Cliente não encontrado para id %d", id)));
    }

}
