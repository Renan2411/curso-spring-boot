package br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarporid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuscarClientePorIdOutput {

    private Long id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;

}
