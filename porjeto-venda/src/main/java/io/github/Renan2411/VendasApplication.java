package io.github.Renan2411;

import io.github.Renan2411.domain.entities.Cliente;
import io.github.Renan2411.domain.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clientesRepository) {
        return args -> {
            System.out.println("Salvando clientes");
            clientesRepository.save(new Cliente("Renan Monteiro"));
            clientesRepository.save(new Cliente("Laísa Monteiro"));
            clientesRepository.save(new Cliente("Ana Paula"));

            System.out.println();

            System.out.println("BUscando");
            Boolean existe = clientesRepository.existsByNome("Renan Monteiro");

            System.out.println("Existe cliente chamado Renan Monteiro " + existe);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
