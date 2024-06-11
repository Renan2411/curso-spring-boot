package br.com.gerenciaautoeletrica.application.factory.servico;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.servico.criar.CriarServicoUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.servico.criar.converter.CriarServicoOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class CriarServicoUseCaseFactory {

    @Bean
    @DependsOn("criarServicoOutputConverter")
    public CriarServicoUseCase criarServicoUseCase(IServicoDataProvider iServicoDataProvider,
                                                   IServicoPecaDataProvider iServicoPecaDataProvider,
                                                   IClienteDataProvider iClienteDataProvider,
                                                   IPecaDataProvider iPecaDataProvider,
                                                   CriarServicoOutputConverter criarServicoOutputConverter){
        return CriarServicoUseCase.builder()
                .iServicoDataProvider(iServicoDataProvider)
                .iServicoPecaDataProvider(iServicoPecaDataProvider)
                .iClienteDataProvider(iClienteDataProvider)
                .iPecaDataProvider(iPecaDataProvider)
                .criarServicoOutputConverter(criarServicoOutputConverter)
                .build();
    }

    @Bean
    public CriarServicoOutputConverter criarServicoOutputConverter(){
        return CriarServicoOutputConverter.builder().build();
    }

}
