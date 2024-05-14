package io.github.Renan2411.rest.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiErros {

    private List<String> errors;

    public ApiErros(String mensagem){
        this.errors = Arrays.asList(mensagem);
    }

    public ApiErros(List<String> mensagens){
        this.errors = mensagens;
    }
}
