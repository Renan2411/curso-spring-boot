package br.com.gerenciaautoeletrica.adapter.gateway.dataprovider.servico;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ServicoDataProviderImpl implements IServicoDataProvider {

    private final ServicoRepository servicoRepository;

    @Override
    public List<ServicoEntity> buscarListagem() {
        return servicoRepository.findAll();
    }

    @Override
    public Optional<ServicoEntity> buscarPorId(Long idServico) {
        return servicoRepository.findById(idServico);
    }

    @Override
    public ServicoEntity criar(ServicoEntity servicoEntity) {
        return servicoRepository.save(servicoEntity);
    }

    @Override
    public ServicoEntity editar(ServicoEntity servicoEntity) {
        return servicoRepository.save(servicoEntity);
    }

    @Override
    public void excluir(ServicoEntity servicoEntity) {
        servicoRepository.delete(servicoEntity);
    }
}
