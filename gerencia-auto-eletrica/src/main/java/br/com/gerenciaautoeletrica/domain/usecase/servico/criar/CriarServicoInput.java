package br.com.gerenciaautoeletrica.domain.usecase.servico.criar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CriarServicoInput {

    private Long idCliente;
    private Boolean pago;
    private LocalDate data;
    private Double valorServico;
    private List<Peca> pecas;

    @Data
    @Builder
    public static class Peca{
        private Long idPeca;
        private Integer quantidade;
    }

}
