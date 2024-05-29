package br.com.gerenciaautoeletrica.adapter.controller.cliente;

import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarlistagem.BuscarListagemClienteOutput;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarlistagem.BuscarListagemClienteUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Clientes", tags = "Clientes", description = "Manutenção de Clientes")
@RestController
@AllArgsConstructor
@RequestMapping("/api/clientes")
public class BuscarListagemClienteController {

    private final BuscarListagemClienteUseCase buscarListagemClienteUseCase;

    @ApiOperation("Listar Clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente criado"),
            @ApiResponse(code = 400, message = "A entrada dos parâmetros da criação do cliente está incorreta"),
    })
    @GetMapping
    public ResponseEntity<BuscarListagemClienteOutput> buscarListagem(){
        BuscarListagemClienteOutput output = buscarListagemClienteUseCase.executar();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
