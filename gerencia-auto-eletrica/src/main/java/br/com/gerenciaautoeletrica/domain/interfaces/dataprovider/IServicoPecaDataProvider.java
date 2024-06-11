package br.com.gerenciaautoeletrica.domain.interfaces.dataprovider;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoPecaEntity;

import java.util.List;
import java.util.Optional;

public interface IServicoPecaDataProvider {

    Optional<ServicoPecaEntity> buscarPorId(Long idServicoPeca);

    List<ServicoPecaEntity> buscarPorIdServico(Long idServico);

    ServicoPecaEntity criar(ServicoPecaEntity servicoPecaEntity);

    ServicoPecaEntity editar(ServicoPecaEntity servicoPecaEntity);

    Boolean ehPecaJaCadastrada(Long idPeca, Long idServico);

    void excluir(ServicoPecaEntity servicoPecaEntity);

}
