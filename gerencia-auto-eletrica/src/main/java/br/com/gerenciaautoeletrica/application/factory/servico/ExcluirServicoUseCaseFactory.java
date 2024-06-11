package br.com.gerenciaautoeletrica.application.factory.servico;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.servico.excluir.ExcluirServicoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcluirServicoUseCaseFactory {

    @Bean
    public ExcluirServicoUseCase excluirServicoUseCase(IServicoDataProvider iServicoDataProvider,
                                                       IServicoPecaDataProvider iServicoPecaDataProvider,
                                                       IPecaDataProvider iPecaDataProvider){
        return ExcluirServicoUseCase.builder()
                .iServicoDataProvider(iServicoDataProvider)
                .iServicoPecaDataProvider(iServicoPecaDataProvider)
                .iPecaDataProvider(iPecaDataProvider)
                .build();
    }

}
