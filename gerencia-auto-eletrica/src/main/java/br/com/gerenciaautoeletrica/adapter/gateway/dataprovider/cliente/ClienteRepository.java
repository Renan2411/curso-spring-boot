package br.com.gerenciaautoeletrica.adapter.gateway.dataprovider.cliente;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
