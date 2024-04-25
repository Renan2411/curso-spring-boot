package io.github.Renan2411;

import io.github.Renan2411.annotions.Cachorro;
import io.github.Renan2411.annotions.Gato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication //Anotation para indicar que é a classe inicial que roda a aplicação
@RestController  //Diz que essa classe será um controllador rest
public class VendasApplication {
    //Inicializando o Spring Boot

    //Com bean
//    @Autowired
//    @Qualifier("applicationName")
//    private String applicationName;

    @Cachorro
    private Animal animal;

    @Bean
    public CommandLineRunner executar2(){
        return args -> {
            this.animal.fazerBarulho();
        };
    }

    @Value("${application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String helloWorld(){
        return applicationName;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
