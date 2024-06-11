package br.com.gerenciaautoeletrica.application.factory.servico;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarlistagem.BuscarListagemServicoUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarlistagem.converter.BuscarListagemServicoOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class BuscarListagemServicoUseCaseFactory {

    @Bean
    @DependsOn("buscarListagemServicoOutputConverter")
    public BuscarListagemServicoUseCase buscarListagemServicoUseCase(IServicoDataProvider iServicoDataProvider,
                                                                     BuscarListagemServicoOutputConverter buscarListagemServicoOutputConverter){
        return BuscarListagemServicoUseCase.builder()
                .iServicoDataProvider(iServicoDataProvider)
                .buscarListagemServicoOutputConverter(buscarListagemServicoOutputConverter)
                .build();
    }

    @Bean
    public BuscarListagemServicoOutputConverter buscarListagemServicoOutputConverter(){
        return BuscarListagemServicoOutputConverter.builder().build();
    }

}
