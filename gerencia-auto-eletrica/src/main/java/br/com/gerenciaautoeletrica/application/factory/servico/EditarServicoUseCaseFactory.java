package br.com.gerenciaautoeletrica.application.factory.servico;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.servico.editar.EditarServicoUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.servico.editar.converter.EditarServicoOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class EditarServicoUseCaseFactory {

    @Bean
    @DependsOn("buscarServicoOutputConverter")
    public EditarServicoUseCase editarServicoUseCase(IServicoDataProvider iServicoDataProvider,
                                                     IServicoPecaDataProvider iServicoPecaDataProvider,
                                                     IClienteDataProvider iClienteDataProvider,
                                                     IPecaDataProvider iPecaDataProvider,
                                                     EditarServicoOutputConverter editarServicoOutputConverter){
        return EditarServicoUseCase.builder()
                .iServicoDataProvider(iServicoDataProvider)
                .iServicoPecaDataProvider(iServicoPecaDataProvider)
                .iClienteDataProvider(iClienteDataProvider)
                .iPecaDataProvider(iPecaDataProvider)
                .editarServicoOutputConverter(editarServicoOutputConverter)
                .build();
    }

    @Bean
    public EditarServicoOutputConverter buscarServicoOutputConverter(){
        return EditarServicoOutputConverter.builder().build();
    }

}
