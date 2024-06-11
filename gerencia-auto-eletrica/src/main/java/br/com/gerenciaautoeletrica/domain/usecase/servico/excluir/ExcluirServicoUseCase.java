package br.com.gerenciaautoeletrica.domain.usecase.servico.excluir;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoPecaEntity;
import br.com.gerenciaautoeletrica.domain.exception.generic.GenericValidationException;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoPecaDataProvider;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
public class ExcluirServicoUseCase {

    private final IServicoDataProvider iServicoDataProvider;
    private final IServicoPecaDataProvider iServicoPecaDataProvider;
    private final IPecaDataProvider iPecaDataProvider;

    public void executar(Long idServico){
        validarEntrada(idServico);
        ServicoEntity servicoEntity = buscarServico(idServico);
        List<ServicoPecaEntity> servicoPecaEntityList = iServicoPecaDataProvider.buscarPorIdServico(servicoEntity.getId());
        atualizarPecas(servicoPecaEntityList);
        excluirServicosPecas(servicoPecaEntityList);
        excluirServico(servicoEntity);
    }

    private void validarEntrada(Long idServico){
        if(Objects.isNull(idServico)){
            throw new GenericValidationException("Ausência do id do Serviço");
        }
    }

    private ServicoEntity buscarServico(Long idServico){
        return iServicoDataProvider.buscarPorId(idServico)
                .orElseThrow(() -> new GenericValidationException(String.format("Serviço de id %d não encontrado", idServico)));
    }

    private void atualizarPecas(List<ServicoPecaEntity> servicoPecaEntityList){
        for(ServicoPecaEntity servicoPecaEntity : servicoPecaEntityList){
            PecaEntity pecaEntity = buscarPeca(servicoPecaEntity.getPeca().getId());

            pecaEntity.setQuantidade(pecaEntity.getQuantidade() + servicoPecaEntity.getQuantidade());
            editarPeca(pecaEntity);
        }

    }

    private PecaEntity buscarPeca(Long idPeca){
        return iPecaDataProvider.buscarPorId(idPeca)
                .orElseThrow(() -> new GenericValidationException(String.format("Peça de id %d não encontrada", idPeca)));
    }

    private void editarPeca(PecaEntity pecaEntity){
        iPecaDataProvider.editar(pecaEntity);
    }

    private void excluirServicosPecas(List<ServicoPecaEntity> servicoPecaEntityList){
        for (ServicoPecaEntity servicoPecaEntity : servicoPecaEntityList){
            iServicoPecaDataProvider.excluir(servicoPecaEntity);
        }
    }

    private void excluirServico(ServicoEntity servicoEntity){
        iServicoDataProvider.excluir(servicoEntity);
    }

}
