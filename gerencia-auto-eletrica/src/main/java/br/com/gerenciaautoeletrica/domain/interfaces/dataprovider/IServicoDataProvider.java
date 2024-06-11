package br.com.gerenciaautoeletrica.domain.interfaces.dataprovider;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;

import java.util.List;
import java.util.Optional;

public interface IServicoDataProvider  {

    List<ServicoEntity> buscarListagem();

    Optional<ServicoEntity> buscarPorId(Long idServico);

    ServicoEntity criar(ServicoEntity servicoEntity);

    ServicoEntity editar(ServicoEntity servicoEntity);

    void excluir(ServicoEntity servicoEntity);

}
