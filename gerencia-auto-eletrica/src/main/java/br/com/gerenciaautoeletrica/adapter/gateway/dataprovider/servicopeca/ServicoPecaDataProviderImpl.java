package br.com.gerenciaautoeletrica.adapter.gateway.dataprovider.servicopeca;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoPecaEntity;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoPecaDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ServicoPecaDataProviderImpl implements IServicoPecaDataProvider {

    private final ServicoPecaRepository servicoPecaRepository;

    @Override
    public Optional<ServicoPecaEntity> buscarPorId(Long idServicoPeca) {
        return servicoPecaRepository.findById(idServicoPeca);
    }

    @Override
    public List<ServicoPecaEntity> buscarPorIdServico(Long idServico) {
        return servicoPecaRepository.findByServicoId(idServico);
    }

    @Override
    public ServicoPecaEntity criar(ServicoPecaEntity servicoPecaEntity) {
        return servicoPecaRepository.save(servicoPecaEntity);
    }

    @Override
    public ServicoPecaEntity editar(ServicoPecaEntity servicoPecaEntity) {
        return servicoPecaRepository.save(servicoPecaEntity);
    }

    @Override
    public Boolean ehPecaJaCadastrada(Long idPeca, Long idServico) {
        return servicoPecaRepository.existsByPecaIdAndServicoId(idPeca, idServico);
    }

    @Override
    public void excluir(ServicoPecaEntity servicoPecaEntity) {
        servicoPecaRepository.delete(servicoPecaEntity);
    }
}
