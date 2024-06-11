package br.com.gerenciaautoeletrica.application.factory.peca;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarListagem.BuscarListagemPecaUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarListagem.converter.BuscarListagemPecaOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class BuscarListagemPecaUseCaseFactory {

    @Bean
    @DependsOn("buscarListagemPecaOutputConverter")
    public BuscarListagemPecaUseCase buscarListagemPecaUseCase(IPecaDataProvider iPecaDataProvider, BuscarListagemPecaOutputConverter buscarListagemPecaOutputConverter){
        return BuscarListagemPecaUseCase.builder()
                .iPecaDataProvider(iPecaDataProvider)
                .buscarListagemPecaOutputConverter(buscarListagemPecaOutputConverter)
                .build();
    }

    @Bean
    public BuscarListagemPecaOutputConverter buscarListagemPecaOutputConverter(){
        return BuscarListagemPecaOutputConverter.builder().build();
    }

}
