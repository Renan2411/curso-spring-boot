package br.com.gerenciaautoeletrica.adapter.gateway.dataprovider.servicopeca;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoPecaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoPecaRepository extends JpaRepository<ServicoPecaEntity, Long> {

    Boolean existsByPecaIdAndServicoId(Long idPeca, Long idServico);

    List<ServicoPecaEntity> findByServicoId(Long idServico);

}
