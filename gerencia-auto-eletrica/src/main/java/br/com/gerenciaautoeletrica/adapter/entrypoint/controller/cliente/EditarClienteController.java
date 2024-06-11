package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.cliente;

import br.com.gerenciaautoeletrica.domain.usecase.cliente.editar.EditarClienteInput;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.editar.EditarClienteOutput;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.editar.EditarClienteUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Clientes", tags = "Clientes", description = "Manutenção de Clientes")
@RestController
@AllArgsConstructor
@RequestMapping("/api/clientes/{id}")
public class EditarClienteController {

    private final EditarClienteUseCase editarClienteUseCase;

    @ApiOperation("Editar um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente editado"),
            @ApiResponse(code = 400, message = "A entrada dos parâmetros da criação do cliente está incorreta"),
    })
    @PutMapping
    public ResponseEntity<EditarClienteOutput> editarCliente(@PathVariable("id") Long idCliente, @RequestBody EditarClienteInput entrada){
        EditarClienteOutput output = editarClienteUseCase.executar(idCliente, entrada);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
