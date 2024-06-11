package br.com.gerenciaautoeletrica.domain.usecase.servico.criar;

import br.com.gerenciaautoeletrica.domain.entity.entities.ClienteEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoEntity;
import br.com.gerenciaautoeletrica.domain.entity.entities.ServicoPecaEntity;
import br.com.gerenciaautoeletrica.domain.exception.generic.GenericValidationException;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IClienteDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoDataProvider;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IServicoPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.servico.criar.converter.CriarServicoOutputConverter;
import br.com.gerenciaautoeletrica.domain.validation.Validator;
import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
public class CriarServicoUseCase {

    private final IServicoDataProvider iServicoDataProvider;
    private final IServicoPecaDataProvider iServicoPecaDataProvider;
    private final IClienteDataProvider iClienteDataProvider;
    private final IPecaDataProvider iPecaDataProvider;
    private final CriarServicoOutputConverter criarServicoOutputConverter;

    public CriarServicoOutput executar(CriarServicoInput entrada){
        validarEntrada(entrada);

        ServicoEntity servicoEntity = montarServicoEntity(entrada);
        ServicoEntity servicoSalvo = criarServico(servicoEntity);

        List<ServicoPecaEntity> servicoPecaEntityList =  salvarServicoPecaEntity(servicoSalvo, entrada);

        return criarServicoOutputConverter.converter(servicoSalvo, servicoPecaEntityList);
    }

    private void validarEntrada(CriarServicoInput entrada){
        Validator.of(entrada)
                .validate(Objects.nonNull(entrada.getIdCliente()), "Ausência do id do cliente")
                .validate(Objects.nonNull(entrada.getValorServico()), "Ausência do valor do serviço")
                .get();
    }

    private ServicoEntity montarServicoEntity(CriarServicoInput entrada){
        return ServicoEntity.builder()
                .pago(Objects.nonNull(entrada.getPago()) ? entrada.getPago() : false)
                .data(Objects.nonNull(entrada.getData()) ? entrada.getData() : LocalDate.now())
                .cliente(buscarClientePorId(entrada.getIdCliente()))
                .valorServico(entrada.getValorServico())
                .valorTotal(calcularValorTotal(entrada))
                .build();
    }

    private ClienteEntity buscarClientePorId(Long idCliente){
        return iClienteDataProvider.buscarPorId(idCliente)
                .orElseThrow(() -> new GenericValidationException(String.format("Cliente de id %d não encontrado", idCliente)));
    }

    private Double calcularValorTotal(CriarServicoInput entrada){
        Double valorTotal = entrada.getValorServico();

        if(!entrada.getPecas().isEmpty()){
            for(CriarServicoInput.Peca peca : entrada.getPecas()){
                PecaEntity pecaEntity = buscarPecaPorId(peca.getIdPeca());

                valorTotal += pecaEntity.getPreco() * peca.getQuantidade();
            }
        }

        return valorTotal;
    }

    private PecaEntity buscarPecaPorId(Long idPeca){
        return iPecaDataProvider.buscarPorId(idPeca)
                .orElseThrow(() -> new GenericValidationException(String.format("Peça de id %d não encontrada.", idPeca)));
    }

    private ServicoEntity criarServico(ServicoEntity servicoEntity){
        return iServicoDataProvider.criar(servicoEntity);
    }

    private List<ServicoPecaEntity> salvarServicoPecaEntity(ServicoEntity servicoEntity, CriarServicoInput entrada){
        List<ServicoPecaEntity> servicoPecaEntityList = new ArrayList<>();

        if(!entrada.getPecas().isEmpty()){
            for(CriarServicoInput.Peca peca : entrada.getPecas()){
                PecaEntity pecaEntity = buscarPecaPorId(peca.getIdPeca());

                if(pecaEntity.getQuantidade() - peca.getQuantidade() < 0){
                    throw new GenericValidationException(String.format("A peça de id %d não possuí quantidade suficiente.", pecaEntity.getId()));
                }

                ServicoPecaEntity servicoPecaEntity = ServicoPecaEntity.builder()
                        .servico(servicoEntity)
                        .peca(pecaEntity)
                        .quantidade(peca.getQuantidade())
                        .precoPeca(pecaEntity.getPreco())
                        .build();

                servicoPecaEntityList.add(criarServicoPeca(servicoPecaEntity));
                atualizarPeca(servicoPecaEntity);
            }
        }

        return servicoPecaEntityList;
    }

    private ServicoPecaEntity criarServicoPeca(ServicoPecaEntity servicoPecaEntity){
        return iServicoPecaDataProvider.criar(servicoPecaEntity);
    }

    private void atualizarPeca(ServicoPecaEntity servicoPecaEntity) {
            PecaEntity pecaEntity = buscarPecaPorId(servicoPecaEntity.getPeca().getId());

            pecaEntity.setQuantidade(pecaEntity.getQuantidade() - servicoPecaEntity.getQuantidade());
            iPecaDataProvider.editar(pecaEntity);
    }

}
