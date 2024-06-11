package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.servico;

import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid.BuscarPecaPorIdOutput;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid.BuscarPecaPorIdUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid.BuscarServicoPorIdOutput;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid.BuscarServicoPorIdUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid.converter.BuscarServicoPorIdOutputConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Serviços", tags = "Serviços")
@RestController
@AllArgsConstructor
@RequestMapping("api/servicos/{id}")
public class BuscarServicoPorIdController {

    private final BuscarServicoPorIdUseCase buscarServicoPorIdUseCase;

    @ApiOperation(value = "Buscar um serviço por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Serviço encontrado"),
            @ApiResponse(code = 401, message = "Ausência do id do serviço"),
            @ApiResponse(code = 404, message = "Serviço não encontrado")
    })
    @GetMapping
    public ResponseEntity<BuscarServicoPorIdOutput> buscarPorId(@PathVariable("id") Long idServico){
        BuscarServicoPorIdOutput output = buscarServicoPorIdUseCase.executar(idServico);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
