package br.com.gerenciaautoeletrica.domain.interfaces.dataprovider;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;

import java.util.Optional;

public interface IClienteDataProvider {
    Optional<ClienteEntity> buscarPorId(Long id);

    ClienteEntity criar(ClienteEntity clienteEntity);

    ClienteEntity editar(ClienteEntity clienteEntity);
}
