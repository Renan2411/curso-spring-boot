package br.com.gerenciaautoeletrica.adapter.gateway.dataprovider.peca;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PecaDataProviderImpl implements IPecaDataProvider {

    private final PecaRepository pecaRepository;

    @Override
    public List<PecaEntity> buscarListagem() {
        return pecaRepository.findAll();
    }

    @Override
    public Optional<PecaEntity> buscarPorId(Long id) {
        return pecaRepository.findById(id);
    }

    @Override
    public PecaEntity criar(PecaEntity pecaEntity) {
        return pecaRepository.save(pecaEntity);
    }

    @Override
    public PecaEntity editar(PecaEntity pecaEntity) {
        return pecaRepository.save(pecaEntity);
    }
}
