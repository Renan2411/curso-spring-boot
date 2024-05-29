package br.com.gerenciaautoeletrica.application.factory.cliente;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarporid.BuscarClientePorIdUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.cliente.buscarporid.conveter.BuscarClientePorIdOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class BuscarClientePorIdUseCaseFactory {

    @Bean
    @DependsOn("buscarClientePorIdOutputConverter")
    public BuscarClientePorIdUseCase buscarClientePorIdUseCase(IClienteDataProvider iClienteDataProvider,
                                                               BuscarClientePorIdOutputConverter buscarClientePorIdOutputConverter){
        return BuscarClientePorIdUseCase
                .builder()
                .iClienteDataProvider(iClienteDataProvider)
                .buscarClientePorIdOutputConverter(buscarClientePorIdOutputConverter)
                .build();
    }

    @Bean
    public BuscarClientePorIdOutputConverter buscarClientePorIdOutputConverter(){
        return BuscarClientePorIdOutputConverter.builder().build();
    }

}
