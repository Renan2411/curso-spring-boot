package br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.exception.generic.GenericValidationException;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.peca.buscarporid.converter.BuscarPecaPorIdOutputConverter;
import lombok.Builder;

import java.util.Objects;

@Builder
public class BuscarPecaPorIdUseCase {

    private final IPecaDataProvider iPecaDataProvider;
    private final BuscarPecaPorIdOutputConverter buscarPecaPorIdOutputConverter;

    public BuscarPecaPorIdOutput executar(Long idPeca){
        validarEntrada(idPeca);

        PecaEntity pecaEntity = buscarPeca(idPeca);

        return buscarPecaPorIdOutputConverter.converter(pecaEntity);
    }

    private void validarEntrada(Long idPeca){
        if(Objects.isNull(idPeca)){
            throw new GenericValidationException("Ausência do id da peça");
        }
    }

    private PecaEntity buscarPeca(Long idPeca){
        return iPecaDataProvider.buscarPorId(idPeca)
                .orElseThrow(() -> new GenericValidationException(String.format("Peça de id %d não encontrada", idPeca)));
    }

}
