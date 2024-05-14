package io.github.Renan2411.rest.dto;

import io.github.Renan2411.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    public Integer idCliente;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    public List<ItemPedidoDTO> itens;
}
