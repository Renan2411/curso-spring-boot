package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.servico;

import br.com.gerenciaautoeletrica.domain.usecase.servico.criar.CriarServicoInput;
import br.com.gerenciaautoeletrica.domain.usecase.servico.criar.CriarServicoOutput;
import br.com.gerenciaautoeletrica.domain.usecase.servico.criar.CriarServicoUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Serviços", tags = "Serviços")
@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("api/servicos")
public class CriarServicoController {

    private final CriarServicoUseCase criarServicoUseCase;

    @ApiOperation(value = "Cria um serviço")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Serviço criado com sucesso"),
            @ApiResponse(code = 400, message = "A entrada do parâmetros para a criação de um serviço está incorreta")
    })
    @PostMapping
    public ResponseEntity<CriarServicoOutput> criarServico(@RequestBody CriarServicoInput entrada){
        CriarServicoOutput output = criarServicoUseCase.executar(entrada);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

}
