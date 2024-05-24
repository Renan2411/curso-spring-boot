package br.com.gerenciaautoeletrica.usecase.cliente.criar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Saída do cadastro do cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriarClienteOutput {

    @ApiModelProperty(value = "id do cliente", required = true)
    private Long id;

    @ApiModelProperty(value = "Cpf do cliente", required = true)
    private String cpf;

    @ApiModelProperty(value = "Telefone do cliente", required = true)
    private String telefone;

    @ApiModelProperty(value = "Email do cliente", required = true)
    private String email;

    @ApiModelProperty(value = "Nome do cliente", required = true)
    private String nome;

}
