package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.servico;

import br.com.gerenciaautoeletrica.domain.usecase.servico.editar.EditarServicoInput;
import br.com.gerenciaautoeletrica.domain.usecase.servico.editar.EditarServicoOutput;
import br.com.gerenciaautoeletrica.domain.usecase.servico.editar.EditarServicoUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Serviços", tags = "Serviços")
@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("api/servivos/{id}")
public class EditarServicoController {

    private EditarServicoUseCase editarServicoUseCase;

    @ApiOperation("Editar um serviço")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Serviço editado com sucesso")
    })
    @PutMapping
    public ResponseEntity<EditarServicoOutput> editarServico(@PathVariable("id") Long idServico, @RequestBody EditarServicoInput servico) {
        EditarServicoOutput output = editarServicoUseCase.executar(idServico, servico);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
