package br.com.gerenciaautoeletrica.usecase.cliente.editar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditarClienteInput {
    private String nome;
    private String telefone;
    private String email;
}
