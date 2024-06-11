package br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid;

import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoPecaEntity;
import br.com.gerenciaautoeletrica.domain.exception.generic.GenericValidationException;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.servico.buscarporid.converter.BuscarServicoPorIdOutputConverter;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
public class BuscarServicoPorIdUseCase {

    private final IServicoDataProvider iServicoDataProvider;
    private final IServicoPecaDataProvider iServicoPecaDataProvider;
    private final BuscarServicoPorIdOutputConverter buscarServicoPorIdOutputConverter;

    public BuscarServicoPorIdOutput executar(Long idServico){
        validarEntrada(idServico);

        ServicoEntity servicoEntity = buscarPorId(idServico);
        List<ServicoPecaEntity> servicoPecaEntityList = buscarListaServicoPecaPorIdServico(idServico);

        return buscarServicoPorIdOutputConverter.converter(servicoEntity, servicoPecaEntityList);
    }

    private void validarEntrada(Long idServico){
        if(Objects.isNull(idServico)){
            throw new GenericValidationException("Ausência do id do serviço");
        }
    }

    private ServicoEntity buscarPorId(Long idServico){
        return iServicoDataProvider.buscarPorId(idServico)
                .orElseThrow(() -> new GenericValidationException(String.format("Serviço de id %d não encontrado", idServico)));
    }

    private List<ServicoPecaEntity> buscarListaServicoPecaPorIdServico(Long idServico){
        return iServicoPecaDataProvider.buscarPorIdServico(idServico);
    }

}
