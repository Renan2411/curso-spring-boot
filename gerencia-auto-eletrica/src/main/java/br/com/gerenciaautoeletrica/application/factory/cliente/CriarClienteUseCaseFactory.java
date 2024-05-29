package br.com.gerenciaautoeletrica.application.factory.cliente;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.criar.CriarClienteUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.criar.converter.CriarClienteOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class CriarClienteUseCaseFactory {

    @Bean
    @DependsOn("criarClienteOutputConverter")
    public CriarClienteUseCase criarClienteUseCase(IClienteDataProvider iClienteDataProvider,
                                                   CriarClienteOutputConverter criarClienteOutputConverter){
        return CriarClienteUseCase.builder()
                .iClienteDataProvider(iClienteDataProvider)
                .criarClienteOutputConverter(criarClienteOutputConverter)
                .build();
    }

    @Bean
    public CriarClienteOutputConverter criarClienteOutputConverter(){
        return CriarClienteOutputConverter.builder().build();
    }

}
