package br.com.gerenciaautoeletrica.adapter.entrypoint.controller.peca;

import br.com.gerenciaautoeletrica.domain.usecase.peca.criar.CriarPecaInput;
import br.com.gerenciaautoeletrica.domain.usecase.peca.criar.CriarPecaOutput;
import br.com.gerenciaautoeletrica.domain.usecase.peca.criar.CriarPecaUseCase;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pecas")
public class CriarPecaController {

    private final CriarPecaUseCase criarPecaUseCase;

    @ApiResponses({
            @ApiResponse(code = 201, message = "Peça criada com sucesso"),
            @ApiResponse(code = 400, message = "A entrada dos parâmetros para criação da peça está incorreta")
    })
    @PostMapping
    public ResponseEntity<CriarPecaOutput> criarPeca(@RequestBody CriarPecaInput entrada){
        CriarPecaOutput output = criarPecaUseCase.executar(entrada);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }
}
