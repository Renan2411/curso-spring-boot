package io.github.Renan2411.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {

    public String nomeCliente;
    public String cpfCliente;
    public BigDecimal total;
    public String dataPedido;
    public String status;
    public List<InformacaoItemPedidoDTO> itens;


}