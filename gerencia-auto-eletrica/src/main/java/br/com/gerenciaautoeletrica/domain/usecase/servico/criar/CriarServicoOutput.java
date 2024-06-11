package br.com.gerenciaautoeletrica.domain.usecase.servico.criar;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
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
public class CriarServicoOutput {

    private Long id;
    private ClienteEntity cliente;
    private Boolean pago;
    private LocalDate data;
    private Double valorServico;
    private Double valorTotal;
    private List<CriarServicoOutput.Peca> pecas;

    @Data
    @Builder
    public static class Peca{
        private Long id;
        private String nome;
        private String descricao;
        private String marca;
        private Double preco;
        private Integer quantidade;
    }

}
