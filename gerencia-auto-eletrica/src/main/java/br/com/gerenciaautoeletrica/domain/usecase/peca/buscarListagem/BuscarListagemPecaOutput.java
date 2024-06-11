package br.com.gerenciaautoeletrica.domain.usecase.peca.buscarListagem;

import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarListagem.converter.BuscarListagemPecaOutputConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BuscarListagemPecaOutput {

    private List<Peca> itens;

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
