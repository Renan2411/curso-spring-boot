package io.github.Renan2411.domain.services;

import io.github.Renan2411.domain.entities.Pedido;
import io.github.Renan2411.rest.dto.AtualizarStatusPedidoDTO;
import io.github.Renan2411.rest.dto.InformacoesPedidoDTO;
import io.github.Renan2411.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    public Pedido salvar (PedidoDTO pedidoDTO);
    public Optional<Pedido> obterPedidoCompleto(Integer id);
    public void atualizarStatus(Integer id, AtualizarStatusPedidoDTO status);
}
