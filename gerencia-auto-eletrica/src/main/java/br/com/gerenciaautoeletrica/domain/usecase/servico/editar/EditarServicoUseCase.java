package br.com.gerenciaautoeletrica.domain.usecase.servico.editar;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoPecaEntity;
import br.com.gerenciaautoeletrica.domain.exception.generic.GenericValidationException;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.servico.editar.converter.EditarServicoOutputConverter;
import br.com.gerenciaautoeletrica.domain.validation.Validator;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
public class EditarServicoUseCase {

    private final IServicoDataProvider iServicoDataProvider;
    private final IServicoPecaDataProvider iServicoPecaDataProvider;
    private final IClienteDataProvider iClienteDataProvider;
    private final IPecaDataProvider iPecaDataProvider;
    private final EditarServicoOutputConverter editarServicoOutputConverter;

    public EditarServicoOutput executar(Long idServico, EditarServicoInput entrada) {
        validarEntrada(idServico, entrada);

        ServicoEntity servicoEntity = buscarServico(idServico);
        atualizarDadosServico(servicoEntity, entrada);
        ServicoEntity servicoEditado = editarServico(servicoEntity);
        List<ServicoPecaEntity> servicoPecaEntityListAutualizado = atualizarServicoPecaEntity(servicoEditado, entrada);

        return editarServicoOutputConverter.converter(servicoEditado, servicoPecaEntityListAutualizado);
    }

    private void validarEntrada(Long idServico, EditarServicoInput entrada) {
        if (Objects.isNull(idServico)) {
            throw new GenericValidationException("Ausência do id do serviço");
        }

        Validator.of(entrada)
                .validate(Objects.nonNull(entrada.getIdCliente()), "Ausência do id do cliente")
                .validate(Objects.nonNull(entrada.getValorServico()), "Ausência do valor do serviço")
                .validate(Objects.nonNull(entrada.getPecas()), "Ausência da lista de peças")
                .validate(Objects.nonNull(entrada.getData()), "Ausência da data do serviço")
                .get();
    }

    private ServicoEntity buscarServico(Long idServico) {
        return iServicoDataProvider.buscarPorId(idServico)
                .orElseThrow(() -> new GenericValidationException(String.format("Serviço com id %d não encontrado", idServico)));
    }

    private void atualizarDadosServico(ServicoEntity servicoEntity, EditarServicoInput entrada) {
        servicoEntity.setPago(Objects.nonNull(entrada.getPago()) ? entrada.getPago() : false);
        servicoEntity.setCliente(buscarCliente(entrada.getIdCliente()));
        servicoEntity.setData(entrada.getData());
        servicoEntity.setValorServico(entrada.getValorServico());
        servicoEntity.setValorTotal(calcularValorTotal(entrada));

    }

    private ClienteEntity buscarCliente(Long idCliente) {
        return iClienteDataProvider.buscarPorId(idCliente)
                .orElseThrow(() -> new GenericValidationException(String.format("Cliente de id %d não encontrado", idCliente)));
    }

    private Double calcularValorTotal(EditarServicoInput entrada) {
        Double valorTotal = 0.0;

        for (EditarServicoInput.Peca peca : entrada.getPecas()) {
            PecaEntity pecaEntity = buscarPeca(peca.getIdPeca());

            valorTotal += pecaEntity.getPreco() * peca.getQuantidade();
        }

        return valorTotal;
    }

    private PecaEntity buscarPeca(Long idPeca) {
        return iPecaDataProvider.buscarPorId(idPeca)
                .orElseThrow(() -> new GenericValidationException(String.format("Peça de id %d não encontadada", idPeca)));
    }

    private ServicoEntity editarServico(ServicoEntity servicoEntity) {
        return iServicoDataProvider.editar(servicoEntity);
    }

    private List<ServicoPecaEntity> atualizarServicoPecaEntity(ServicoEntity servicoEntity, EditarServicoInput entrada) {
        List<ServicoPecaEntity> servicoPecaEntityList = new ArrayList<>();

        for (EditarServicoInput.Peca servicoPeca : entrada.getPecas()) {
            PecaEntity pecaEntity = buscarPeca(servicoPeca.getIdPeca());

            if (pecaEntity.getQuantidade() - servicoPeca.getQuantidade() < 0) {
                throw new GenericValidationException(String.format("A quantidade da peça de id %d não é suficiente", pecaEntity.getId()));
            }

            ServicoPecaEntity servicoPecaEntityAtualizado;

            if (Objects.nonNull(servicoPeca.getId())) {
                ServicoPecaEntity servicoPecaEntity = buscarServicoPeca(servicoPeca.getId());
                servicoPecaEntityAtualizado = editarServicoPeca(servicoEntity, pecaEntity, servicoPecaEntity, servicoPeca);
            } else {
                verificarSePecaJaEstaCadastrada(pecaEntity.getId(), servicoEntity.getId());
                servicoPecaEntityAtualizado = criarServicoPeca(servicoEntity, pecaEntity, servicoPeca);
            }

            servicoPecaEntityList.add(servicoPecaEntityAtualizado);
            atualizarPeca(pecaEntity, servicoPecaEntityAtualizado);
        }

        return servicoPecaEntityList;
    }

    private ServicoPecaEntity buscarServicoPeca(Long idServicoPeca) {
        return iServicoPecaDataProvider.buscarPorId(idServicoPeca)
                .orElseThrow(() -> new GenericValidationException(String.format("Peça para o serviço não encontrada")));
    }

    private ServicoPecaEntity editarServicoPeca(ServicoEntity servicoEntity, PecaEntity pecaEntity, ServicoPecaEntity servicoPecaEntity, EditarServicoInput.Peca servicoPeca) {
        servicoPecaEntity.setQuantidade(servicoPeca.getQuantidade());
        servicoPecaEntity.setPeca(pecaEntity);
        servicoPecaEntity.setServico(servicoEntity);

        return iServicoPecaDataProvider.editar(servicoPecaEntity);
    }

    private void verificarSePecaJaEstaCadastrada(Long idPeca, Long idServico){
        if(iServicoPecaDataProvider.ehPecaJaCadastrada(idPeca, idServico)){
            throw new GenericValidationException(String.format("Peça de id %d já cadastrada para o serviço de id %d", idPeca, idServico));
        }
    }

    private ServicoPecaEntity criarServicoPeca(ServicoEntity servicoEntity, PecaEntity pecaEntity, EditarServicoInput.Peca servicoPeca) {
        ServicoPecaEntity servicoPecaEntity = ServicoPecaEntity
                .builder()
                .precoPeca(pecaEntity.getPreco())
                .quantidade(servicoPeca.getQuantidade())
                .servico(servicoEntity)
                .peca(pecaEntity)
                .build();

        return iServicoPecaDataProvider.criar(servicoPecaEntity);
    }

    private void atualizarPeca(PecaEntity pecaEntity, ServicoPecaEntity servicoPecaEntity){
        pecaEntity.setQuantidade(pecaEntity.getQuantidade() - servicoPecaEntity.getQuantidade());
        iPecaDataProvider.editar(pecaEntity);
    }

}
