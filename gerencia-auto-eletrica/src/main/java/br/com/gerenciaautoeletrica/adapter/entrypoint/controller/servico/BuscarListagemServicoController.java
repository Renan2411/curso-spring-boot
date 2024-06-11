package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.servico;

import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarlistagem.BuscarListagemServicoOutput;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarlistagem.BuscarListagemServicoUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid.BuscarServicoPorIdOutput;
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

@Api(value = "Serviços", tags = "Serviços")
@RestController
@AllArgsConstructor
@RequestMapping("api/servicos")
public class BuscarListagemServicoController {

    private final BuscarListagemServicoUseCase buscarListagemServicoUseCase;

    @ApiOperation("Buscar listagem de serviços")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Serviços encontrados com sucesso")
    })
    @GetMapping
    public ResponseEntity<BuscarListagemServicoOutput> buscarListagem(){
        BuscarListagemServicoOutput output = buscarListagemServicoUseCase.executar();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
