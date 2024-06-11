package br.com.gerenciaautoeletrica.application.factory.peca;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid.BuscarPecaPorIdUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid.converter.BuscarPecaPorIdOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class BuscarPecaPorIdUseCaseFactory {

    @Bean
    @DependsOn("buscarPecaPorIdOutputConverter")
    public BuscarPecaPorIdUseCase buscarPecaPorIdUseCase(IPecaDataProvider iPecaDataProvider, BuscarPecaPorIdOutputConverter buscarPecaPorIdOutputConverter){
        return BuscarPecaPorIdUseCase.builder()
                .iPecaDataProvider(iPecaDataProvider)
                .buscarPecaPorIdOutputConverter(buscarPecaPorIdOutputConverter)
                .build();
    }

    @Bean
    public BuscarPecaPorIdOutputConverter buscarPecaPorIdOutputConverter(){
        return BuscarPecaPorIdOutputConverter.builder().build();
    }

}
