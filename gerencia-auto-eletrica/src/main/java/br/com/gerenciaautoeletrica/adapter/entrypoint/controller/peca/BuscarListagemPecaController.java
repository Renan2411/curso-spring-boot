package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.peca;

import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarListagem.BuscarListagemPecaOutput;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarListagem.BuscarListagemPecaUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Peças", tags = "Peças", description = "Manutenção de Peças")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/pecas")
public class BuscarListagemPecaController {

    private final BuscarListagemPecaUseCase buscarListagemPecaUseCase;

    @ApiOperation("Listar Peças")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem de peças buscada com sucesso")
    })
    @GetMapping
    public ResponseEntity<BuscarListagemPecaOutput> buscarListagem(){
        BuscarListagemPecaOutput output = buscarListagemPecaUseCase.executar();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
