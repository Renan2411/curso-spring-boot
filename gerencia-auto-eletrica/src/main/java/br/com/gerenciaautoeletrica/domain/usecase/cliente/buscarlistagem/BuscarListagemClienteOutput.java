package br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarlistagem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BuscarListagemClienteOutput {

    private List<BuscarListagemClienteOutput.Cliente> clientes;

    @Data
    @Builder
    public static class Cliente{
        private Long id;
        private String nome;
        private String cpf;
        private String email;
        private String telefone;
    }

}
