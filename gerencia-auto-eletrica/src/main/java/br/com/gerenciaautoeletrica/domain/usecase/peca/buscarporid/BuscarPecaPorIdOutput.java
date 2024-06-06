package br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuscarPecaPoridOutput {

    private Long id;
    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
    private Integer quantidade;

}
