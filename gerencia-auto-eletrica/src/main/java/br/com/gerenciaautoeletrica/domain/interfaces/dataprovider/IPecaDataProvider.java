package br.com.gerenciaautoeletrica.domain.interfaces.dataprovider;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;

import java.util.List;
import java.util.Optional;

public interface IPecaDataProvider {

    List<PecaEntity> buscarListagem();

    Optional<PecaEntity> buscarPorId(Long id);

    PecaEntity criar(PecaEntity pecaEntity);

    PecaEntity editar(PecaEntity pecaEntity);

}
