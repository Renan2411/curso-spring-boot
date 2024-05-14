package br.com.gerenciaautoeletrica.adapter.controller.clientes;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Clientes", tags = "Clientes", description = "Manutenção de Clientes da aplicação")
@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class CriarClienteController {
}
