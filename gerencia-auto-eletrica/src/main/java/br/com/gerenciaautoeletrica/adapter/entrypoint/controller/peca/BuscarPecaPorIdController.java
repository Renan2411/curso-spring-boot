package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.peca;

import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid.BuscarPecaPorIdOutput;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid.BuscarPecaPorIdUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Peças", tags = "Peças", description = "Manutenção de Peças")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/pecas/{id}")
public class BuscarPecaPorIdController {

    private final BuscarPecaPorIdUseCase buscarPecaPorIdUseCase;

    @ApiOperation("Buscar Peça por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sucesso ao buscar peça por id"),
            @ApiResponse(code = 404, message = "Peça não encontrada")
    })
    @GetMapping
    public ResponseEntity<BuscarPecaPorIdOutput> buscarPorId(@PathVariable("id") Long idPeca){
        BuscarPecaPorIdOutput output = buscarPecaPorIdUseCase.executar(idPeca);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
