package br.com.gerenciaautoeletrica.application.factory.peca;

import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.peca.editar.EditarPecaUseCase;
import br.com.gerenciaautoeletrica.domain.usecase.peca.editar.converter.EditarPecaOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class EditarPecaUseCaseFactory {

    @Bean
    @DependsOn("editarPecaOutputConverter")
    public EditarPecaUseCase editarPecaUseCase(IPecaDataProvider iPecaDataProvider, EditarPecaOutputConverter editarPecaOutputConverter){
        return EditarPecaUseCase.builder()
                .iPecaDataProvider(iPecaDataProvider)
                .editarPecaOutputConverter(editarPecaOutputConverter)
                .build();
    }

    @Bean
    public EditarPecaOutputConverter editarPecaOutputConverter(){
        return EditarPecaOutputConverter.builder().build();
    }

}
