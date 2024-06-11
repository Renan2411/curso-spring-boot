package br.com.gerenciaautoeletrica.domain.usecase.peca.editar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditarPecaInput {

    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
    private Integer quantidade;

}
