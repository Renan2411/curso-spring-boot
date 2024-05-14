package io.github.Renan2411.domain.repositories;

import io.github.Renan2411.domain.entities.Cliente;
import io.github.Renan2411.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    public List<Pedido> findByCliente(Cliente cliente);

    @Query("SELECT p FROM Pedido p left join fetch p.itens WHERE p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
