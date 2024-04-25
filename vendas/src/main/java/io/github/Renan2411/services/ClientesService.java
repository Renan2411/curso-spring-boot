package io.github.Renan2411.services;

import io.github.Renan2411.model.Cliente;
import io.github.Renan2411.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    //Desse jeito o SB já realiza a instanciação ao usar essa anotation
    @Autowired
    private ClienteRepository clienteRepository;


    //Com as anotations o SprinBoot já realiza a injeção de dependencia no construtor da classe
//    @Autowired
//    public ClientesService(ClienteRepository clienteRepository){
//        this.clienteRepository = clienteRepository;
//    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);

        //Repositories ou clases que acessam serviços externos são muito pesadas para serem instanciadas toda hora;
        //ClienteRepository clienteRepository = new ClienteRepository();
        clienteRepository.criar(cliente);
    }

    public void validarCliente(Cliente cliente){
        //aplica validações
    }

}
