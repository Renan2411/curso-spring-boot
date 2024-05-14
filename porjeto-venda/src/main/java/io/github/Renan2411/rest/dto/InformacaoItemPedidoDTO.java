package io.github.Renan2411.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoItemPedidoDTO {
    public String descricaoProduto;
    public BigDecimal valorUnitarioProduto;
    public Integer quantidade;
}
