package br.com.gerenciaautoeletrica.adapter.gateway.dataprovider.peca;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PecaRepository extends JpaRepository<PecaEntity, Long> {
}
