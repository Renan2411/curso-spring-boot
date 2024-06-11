package br.com.gerenciaautoeletrica.adapter.gateway.dataprovider.cliente;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public List<ClienteEntity> buscarListagem() {
        return clienteRepository.findAll();
    }

    @Override
    public ClienteEntity criar(ClienteEntity clienteEntity) {
        return clienteRepository.save(clienteEntity);
    }

    @Override
    public ClienteEntity editar(ClienteEntity clienteEntity) {
        return clienteRepository.save(clienteEntity);
    }

    @Override
    public Boolean existePorId(Long idCliente) {
        return clienteRepository.existsById(idCliente);
    }
}
