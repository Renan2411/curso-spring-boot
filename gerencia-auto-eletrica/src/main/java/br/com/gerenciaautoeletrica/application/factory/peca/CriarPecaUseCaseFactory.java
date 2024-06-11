package br.com.gerenciaautoeletrica.application.factory.peca;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.peca.criar.CriarPecaUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.peca.criar.converter.CriarPecaOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class CriarPecaUseCaseFactory {

    @Bean
    @DependsOn("criarPecaOutputConverter")
    public CriarPecaUseCase criarPecaUseCase(IPecaDataProvider iPecaDataProvider, CriarPecaOutputConverter criarPecaOutputConverter){
        return CriarPecaUseCase.builder()
                .iPecaDataProvider(iPecaDataProvider)
                .criarPecaOutputConverter(criarPecaOutputConverter)
                .build();
    }

    @Bean
    public CriarPecaOutputConverter criarPecaOutputConverter(){
        return CriarPecaOutputConverter.builder().build();
    }

}
