package br.com.gerenciaautoeletrica.adapter.dataprovider.cliente;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ClienteDataProviderImpl implements IClienteDataProvider {

    private final ClienteRepository clienteRepository;

    @Override
    public Optional<ClienteEntity> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public ClienteEntity criar(ClienteEntity clienteEntity) {
        return clienteRepository.save(clienteEntity);
    }

    @Override
    public ClienteEntity editar(ClienteEntity clienteEntity) {
        return clienteRepository.save(clienteEntity);
    }
}
