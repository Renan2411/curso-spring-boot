package io.github.Renan2411.rest.controller;

import io.github.Renan2411.exception.PedidoNaoEncontradoExecption;
import io.github.Renan2411.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    //Toda vez que o projeto lançar uma RegraNegocioExecption, este método irá tratá-la
    @ExceptionHandler({RegraNegocioException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException exception) {
        String mensagemErro = exception.getMessage();
        return new ApiErros(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoExecption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlerPedidoNaoEncontradoException(PedidoNaoEncontradoExecption exception) {
        return new ApiErros(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handlerMethodArgumentNotValidException(MethodArgumentNotValidException execption){
        return new ApiErros(execption.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList()));
    }

}
