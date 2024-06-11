package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.cliente;

import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarlistagem.BuscarListagemClienteOutput;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarporid.BuscarClientePorIdOutput;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarporid.BuscarClientePorIdUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Clientes", tags = "Clientes", description = "Manutenção de Clientes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes/{id}")
public class BuscarPorIdClienteController {

    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;

    @ApiOperation("Buscar um Cliente Por Id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cliente buscado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    @GetMapping
    public ResponseEntity<BuscarClientePorIdOutput> buscarPorId(@PathVariable("id") Long idCliente){
        BuscarClientePorIdOutput output = buscarClientePorIdUseCase.executar(idCliente);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
