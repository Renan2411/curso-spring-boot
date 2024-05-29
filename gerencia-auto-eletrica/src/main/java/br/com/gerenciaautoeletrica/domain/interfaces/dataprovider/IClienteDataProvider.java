package br.com.gerenciaautoeletrica.domain.interfaces.dataprovider;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;

import java.util.List;
import java.util.Optional;

public interface IClienteDataProvider {
    Optional<ClienteEntity> buscarPorId(Long id);

    List<ClienteEntity> buscarListagem();

    ClienteEntity criar(ClienteEntity clienteEntity);

    ClienteEntity editar(ClienteEntity clienteEntity);

    Boolean existePorId(Long idCliente);
}
