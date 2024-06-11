package br.com.gerenciaautoeletrica.domain.usecase.servico.buscarlistagem;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class BuscarListagemServicoOutput {

    private List<BuscarListagemServicoOutput.Servico> itens;

    @Data
    @Builder
    public static class Servico {
        private Long id;
        private Boolean pago;
        private LocalDate data;
        private Double valorTotal;
        private Double valorServico;
        private String nomeCliente;
    }

}
