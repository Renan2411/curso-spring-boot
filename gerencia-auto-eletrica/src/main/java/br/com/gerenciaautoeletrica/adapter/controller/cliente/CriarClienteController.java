package br.com.gerenciaautoeletrica.adapter.controller.cliente;

import br.com.gerenciaautoeletrica.usecase.cliente.criar.CriarClienteInput;
import br.com.gerenciaautoeletrica.usecase.cliente.criar.CriarClienteOutput;
import br.com.gerenciaautoeletrica.usecase.cliente.criar.CriarClienteUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Clientes", tags = "Clientes", description = "Manutenção de Clientes")
@RestController
@AllArgsConstructor
@RequestMapping("/api/clientes")
public class CriarClienteController {

    private final CriarClienteUseCase criarClienteUseCase;

    @ApiOperation("Criar um Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente criado"),
            @ApiResponse(code = 400, message = "A entrada dos parâmetros da criação do cliente está incorreta"),
    })
    @PostMapping
    public ResponseEntity<CriarClienteOutput> criarCliente(@RequestBody CriarClienteInput entrada) {
        CriarClienteOutput output = criarClienteUseCase.executar(entrada);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

}
