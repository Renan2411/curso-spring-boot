package io.github.Renan2411.rest.controller;

import io.github.Renan2411.domain.entities.ItemPedido;
import io.github.Renan2411.domain.entities.Pedido;
import io.github.Renan2411.domain.services.PedidoService;
import io.github.Renan2411.domain.services.impl.PedidoServiceImpl;
import io.github.Renan2411.rest.dto.AtualizarStatusPedidoDTO;
import io.github.Renan2411.rest.dto.InformacaoItemPedidoDTO;
import io.github.Renan2411.rest.dto.InformacoesPedidoDTO;
import io.github.Renan2411.rest.dto.PedidoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoServiceImpl pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO pedidoDTO) {
        Pedido novoPedido = this.pedidoService.salvar(pedidoDTO);
        return novoPedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return this.pedidoService.obterPedidoCompleto(id)
                    .map(pedido -> {
                    return this.converter(pedido);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizarStatusPedidoDTO novoStatus) {
        this.pedidoService.atualizarStatus(id, novoStatus);
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .nomeCliente(pedido.getCliente().getNome())
                .cpfCliente(pedido.getCliente().getCpf())
                .total(pedido.getTotal())
                .dataPedido(pedido.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .status(pedido.getStatus().name())
                .itens(this.converterItensPedido(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converterItensPedido(List<ItemPedido> itensPedido) {
        if (itensPedido.isEmpty()) {
            return Collections.emptyList();
        }

        return itensPedido.stream()
                .map(itemPedido -> {
                    return InformacaoItemPedidoDTO
                            .builder()
                            .descricaoProduto(itemPedido.getProduto().getDescricao())
                            .valorUnitarioProduto(itemPedido.getProduto().getPreco())
                            .quantidade(itemPedido.getQuantidade())
                            .build();
                }).collect(Collectors.toList());
    }
}
