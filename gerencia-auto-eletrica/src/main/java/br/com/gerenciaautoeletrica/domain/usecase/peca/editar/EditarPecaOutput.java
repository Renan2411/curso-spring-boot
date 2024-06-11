package br.com.gerenciaautoeletrica.domain.usecase.peca.editar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditarPecaOutput {

    private Long id;
    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
    private Integer quantidade;

}
