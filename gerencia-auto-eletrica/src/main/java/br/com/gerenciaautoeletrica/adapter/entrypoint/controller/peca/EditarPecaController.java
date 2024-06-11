package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.peca;

import br.com.gerenciaautoeletrica.domain.usecase.peca.editar.EditarPecaInput;
import br.com.gerenciaautoeletrica.domain.usecase.peca.editar.EditarPecaOutput;
import br.com.gerenciaautoeletrica.domain.usecase.peca.editar.EditarPecaUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pecas/{id}")
public class EditarPecaController {

    private final EditarPecaUseCase editarPecaUseCase;

    @ApiOperation("Editar um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Peça editada"),
            @ApiResponse(code = 400, message = "A entrada dos parâmetros da edição da peça está incorreta"),
    })
    @PutMapping
    public ResponseEntity<EditarPecaOutput> editar(@PathVariable("id") Long idPeca, @RequestBody EditarPecaInput entrada){
        EditarPecaOutput output = editarPecaUseCase.executar(idPeca, entrada);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
