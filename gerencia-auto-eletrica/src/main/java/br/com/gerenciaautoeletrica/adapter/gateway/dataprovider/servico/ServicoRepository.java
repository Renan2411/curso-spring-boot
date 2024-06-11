package br.com.gerenciaautoeletrica.adapter.gateway.dataprovider.servico;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {
}
