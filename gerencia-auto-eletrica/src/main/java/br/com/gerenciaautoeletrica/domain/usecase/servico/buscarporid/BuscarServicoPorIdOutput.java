package br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class BuscarServicoPorIdOutput {

    private Long id;
    private ClienteEntity cliente;
    private Boolean pago;
    private LocalDate data;
    private Double valorServico;
    private Double valorTotal;
    private List<BuscarServicoPorIdOutput.Peca> pecas;

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
