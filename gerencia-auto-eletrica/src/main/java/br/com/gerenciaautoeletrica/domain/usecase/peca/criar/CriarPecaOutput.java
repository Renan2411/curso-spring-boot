package br.com.gerenciaautoeletrica.domain.usecase.peca.criar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriarPecaOutput {

    private Long id;
    private String nome;
    private String descricao;
    private String marca;
    private Integer quantidade;
    private Double preco;

}
