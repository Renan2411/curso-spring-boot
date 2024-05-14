package io.github.Renan2411.rest.controller;

import io.github.Renan2411.domain.entities.Cliente;
import io.github.Renan2411.domain.repositories.ClienteRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes-versao-um")
public class ClienteControllerVersaoUm {

    private ClienteRepository clienteRepository;

    private ClienteControllerVersaoUm(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

//    @RequestMapping(
//            value = {"/hello/{nome}", "/"},
//            consumes = {"application/json", "application/xml"},
//            produces = {"application/json", "application/xml"}
//    ) //Esse request mappgin serve para indicar como esse método será acessado
//    @GetMapping
//    @ResponseBody //O @ResponseBody serve para indicar que o retorno do método será o corpo da resposta
//    public String helloCliente(@PathVariable("nome") String nome){ //O @PathVariable serve para indicar um parâmetro que será recebido pela URL
//        return String.format("Hello %s", nome);
//    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    //O ReponseEntity é um objeto que representa o corpo da resposta
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (!cliente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente.get()); //Requisição realizada com sucesso, não deu nenhum erro
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);

        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (!cliente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Cliente cliente) {

        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase() //Retira o case sensitive
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //Indica que uma string deve conter o valor passado na busca
        Example example = Example.of(filtro, matcher);

        List<Cliente> clientes = clienteRepository.findAll(example);

        return ResponseEntity.ok(clientes);
    }

}

