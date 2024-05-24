package br.com.gerenciaautoeletrica.application.factory.cliente;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.usecase.cliente.editar.EditarClienteUseCase;
import br.com.gerenciaautoeletrica.usecase.cliente.editar.converter.EditarClienteOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class EditarClienteUseCaseFactory {

    @Bean
    @DependsOn("editarClienteOutputConverter")
    public EditarClienteUseCase editarClienteUseCase(IClienteDataProvider iClienteDataProvider,
                                                     EditarClienteOutputConverter editarClienteOutputConverter){
        return EditarClienteUseCase.builder()
                .iClienteDataProvider(iClienteDataProvider)
                .editarClienteOutputConverter(editarClienteOutputConverter)
                .build();
    }

    @Bean
    public EditarClienteOutputConverter editarClienteOutputConverter(){
        return EditarClienteOutputConverter.builder().build();
    }

}
