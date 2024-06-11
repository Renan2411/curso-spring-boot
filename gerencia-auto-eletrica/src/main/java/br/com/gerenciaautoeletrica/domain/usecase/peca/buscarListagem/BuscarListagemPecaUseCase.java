package br.com.gerenciaautoeletrica.domain.usecase.peca.buscarListagem;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarListagem.converter.BuscarListagemPecaOutputConverter;
import lombok.Builder;

import java.util.List;

@Builder
public class BuscarListagemPecaUseCase {

    private final IPecaDataProvider iPecaDataProvider;
    private final BuscarListagemPecaOutputConverter buscarListagemPecaOutputConverter;

    public BuscarListagemPecaOutput executar(){

        List<PecaEntity> pecaEntityList = buscarListagem();

        return buscarListagemPecaOutputConverter.converter(pecaEntityList);
    }

    private List<PecaEntity> buscarListagem(){
        return iPecaDataProvider.buscarListagem();
    }

}
