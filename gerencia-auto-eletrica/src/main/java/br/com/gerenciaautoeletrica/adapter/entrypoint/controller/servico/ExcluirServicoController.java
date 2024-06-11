package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.servico;

import br.com.gerenciaautoeletrica.domain.usecase.servico.excluir.ExcluirServicoUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Serviços", tags = "Serviços")
@RestController
@Transactional
@AllArgsConstructor
@RequestMapping("api/servicos/{id}")
public class ExcluirServicoController {

    private final ExcluirServicoUseCase excluirServicoUseCase;

    @ApiOperation("Deletar um serviço")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Serviço excluido com sucesso")
    })
    @DeleteMapping
    public ResponseEntity<Void> excluir(@PathVariable("id") Long idServico){
        excluirServicoUseCase.executar(idServico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
