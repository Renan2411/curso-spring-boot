package br.com.gerenciaautoeletrica.domain.usecase.servico.editar;

import br.com.gerenciaautoeletrica.domain.usecase.servico.criar.CriarServicoInput;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class EditarServicoInput {

    private Long id;
    private Long idCliente;
    private Boolean pago;
    private LocalDate data;
    private Double valorServico;
    private List<EditarServicoInput.Peca> pecas;

    @Data
    @Builder
    public static class Peca {
        private Long id;
        private Long idPeca;
        private Integer quantidade;
    }

}
