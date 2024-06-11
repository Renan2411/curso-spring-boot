package br.com.gerenciaautoeletrica.application.factory.servico;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid.BuscarServicoPorIdUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid.converter.BuscarServicoPorIdOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class BuscarServicoPorIdUseCaseFactory {

    @Bean
    @DependsOn("buscarServicoPorIdOutputConverter")
    public BuscarServicoPorIdUseCase buscarServicoPorIdUseCase(IServicoDataProvider iServicoDataProvider,
                                                               IServicoPecaDataProvider iServicoPecaDataProvider,
                                                               BuscarServicoPorIdOutputConverter buscarServicoPorIdOutputConverter){
        return BuscarServicoPorIdUseCase.builder()
                .iServicoDataProvider(iServicoDataProvider)
                .iServicoPecaDataProvider(iServicoPecaDataProvider)
                .buscarServicoPorIdOutputConverter(buscarServicoPorIdOutputConverter)
                .build();
    }

    @Bean
    public BuscarServicoPorIdOutputConverter buscarServicoPorIdOutputConverter(){
        return BuscarServicoPorIdOutputConverter.builder().build();
    }

}
