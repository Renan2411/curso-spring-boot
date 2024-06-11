package br.com.gerenciaautoeletrica.adapter.controller.cliente;

import br.com.gerenciaautoeletrica.domain.usecase.cliente.criar.CriarClienteInput;
import junit.framework.TestCase;
import org.junit.Test;

public class CriarClienteControllerTest {

    @Test
    public void deveCriarUmCliente(){
        CriarClienteInput input = criarClienteInput();
    }

    public CriarClienteInput criarClienteInput(){
        return CriarClienteInput.builder()
                .cpf("12345678909")
                .nome("Cliente Teste")
                .email("criar.cliente@gmail.com")
                .telefone("1123445324")
                .build();
    }

}