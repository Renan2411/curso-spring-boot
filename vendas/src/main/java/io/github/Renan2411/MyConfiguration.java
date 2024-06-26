package io.github.Renan2411;

import io.github.Renan2411.annotions.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

//@Configuration
//@Profile("development")
@Development
public class MyConfiguration {

    @Bean(name = "applicationName")
    public String applicationName(){
        return "Sistema de Vendas para aprendizado do Spring Boot";
    }

    //Funções BEAN desse tipo são executadas assim que o sistema SB é iniciado
    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("RODANDO A CONFIGURAÇÃO DE DESENVOLVIMENTO");
        };
    }

}
