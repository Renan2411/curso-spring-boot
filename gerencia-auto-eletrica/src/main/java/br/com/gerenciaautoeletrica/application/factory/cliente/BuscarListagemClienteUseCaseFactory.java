package br.com.gerenciaautoeletrica.application.factory.cliente;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarlistagem.BuscarListagemClienteUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarlistagem.converter.BuscarListagemClienteOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class BuscarListagemClienteUseCaseFactory {

    @Bean
    @DependsOn("buscarListagemClienteOutputConverter")
    public BuscarListagemClienteUseCase buscarListagemClienteUseCase(IClienteDataProvider iClienteDataProvider,
                                                                             BuscarListagemClienteOutputConverter buscarListagemClienteOutputConverter){
        return BuscarListagemClienteUseCase.builder()
                .iClienteDataProvider(iClienteDataProvider)
                .buscarListagemClienteOutputConverter(buscarListagemClienteOutputConverter)
                .build();
    }

    @Bean
    public BuscarListagemClienteOutputConverter buscarListagemClienteOutputConverter(){
        return BuscarListagemClienteOutputConverter.builder().build();
    }

}
