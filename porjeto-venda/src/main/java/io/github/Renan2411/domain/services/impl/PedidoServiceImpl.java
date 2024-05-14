package io.github.Renan2411.domain.services.impl;

import io.github.Renan2411.domain.entities.Cliente;
import io.github.Renan2411.domain.entities.ItemPedido;
import io.github.Renan2411.domain.entities.Pedido;
import io.github.Renan2411.domain.entities.Produto;
import io.github.Renan2411.domain.enuns.StatusPedido;
import io.github.Renan2411.domain.repositories.ClienteRepository;
import io.github.Renan2411.domain.repositories.ItemPedidoRepository;
import io.github.Renan2411.domain.repositories.PedidoRepository;
import io.github.Renan2411.domain.repositories.ProdutoRepository;
import io.github.Renan2411.domain.services.PedidoService;
import io.github.Renan2411.exception.PedidoNaoEncontradoExecption;
import io.github.Renan2411.exception.RegraNegocioException;
import io.github.Renan2411.rest.dto.AtualizarStatusPedidoDTO;
import io.github.Renan2411.rest.dto.InformacoesPedidoDTO;
import io.github.Renan2411.rest.dto.ItemPedidoDTO;
import io.github.Renan2411.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public Pedido save() {
        return new Pedido();
    }

    @Transactional
    @Override
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();

        pedido.setData(LocalDate.now());
        pedido.setCliente(this.getClienteById(pedidoDTO.getIdCliente()));
        pedido.setTotal(this.calcularTotal(pedidoDTO.getItens()));
        pedido.setStatus(StatusPedido.REALIZADO);

        pedidoRepository.save(pedido);

        List<ItemPedido> itensPedido = converterItens(pedido, pedidoDTO.getItens());
        itemPedidoRepository.saveAll(itensPedido);

        pedido.setItens(itensPedido);

        return pedido;
    }

    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return this.pedidoRepository.findByIdFetchItens(id);
    }

    @Transactional
    @Override
    public void atualizarStatus(Integer id, AtualizarStatusPedidoDTO status) {
        this.pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(StatusPedido.valueOf(status.getStatus()));
                    return this.pedidoRepository.save(pedido);
                })
                .orElseThrow(() -> new PedidoNaoEncontradoExecption());

    }

    private Cliente getClienteById(Integer id) {
        return this.clienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException(String.format("Código %d para cliente inválido", id)));
    }

    private BigDecimal calcularTotal(List<ItemPedidoDTO> itensPedido) {
        Double total = 0.0;

        for (ItemPedidoDTO itemPedido : itensPedido) {
            total += this.getProdutoById(itemPedido.getIdProduto()).getPreco().doubleValue() * itemPedido.getQuantidade();
        }

        return BigDecimal.valueOf(total);
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itemPedidoDTOs) {
        if (itemPedidoDTOs.isEmpty()) {
            throw new RegraNegocioException("Lista de itens não pode ser vazia");
        }

        return itemPedidoDTOs
                .stream()
                .map(itemPedidoDTO -> {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(this.getProdutoById(itemPedidoDTO.getIdProduto()));
                    return itemPedido;
                }).collect(Collectors.toList());
    }

    private Produto getProdutoById(Integer id) {
        return this.produtoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException(String.format("Código %d para produto inválido", id)));
    }
}
