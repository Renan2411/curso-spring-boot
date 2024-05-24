package br.com.gerenciaautoeletrica.usecase.cliente.editar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditarClienteOutput {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
