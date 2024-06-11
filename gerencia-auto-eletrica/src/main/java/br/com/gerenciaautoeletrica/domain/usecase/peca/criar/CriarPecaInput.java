package br.com.gerenciaautoeletrica.domain.usecase.peca.criar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarPecaInput {

    private String nome;
    private String descricao;
    private String marca;
    private Integer quantidade;
    private Double preco;

}
