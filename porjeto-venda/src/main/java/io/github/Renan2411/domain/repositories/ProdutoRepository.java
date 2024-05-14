package io.github.Renan2411.domain.repositories;

import io.github.Renan2411.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
