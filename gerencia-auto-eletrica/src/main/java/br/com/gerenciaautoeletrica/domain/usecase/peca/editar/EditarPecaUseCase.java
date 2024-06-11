package br.com.gerenciaautoeletrica.domain.usecase.peca.editar;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.exception.generic.GenericValidationException;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.peca.editar.converter.EditarPecaOutputConverter;
import br.com.gerenciaautoeletrica.domain.validation.Validator;
import lombok.Builder;

import java.util.Objects;

@Builder
public class EditarPecaUseCase {

    private final IPecaDataProvider iPecaDataProvider;
    private final EditarPecaOutputConverter editarPecaOutputConverter;

    public EditarPecaOutput executar(Long id, EditarPecaInput entrada){
        validarEntrada(id, entrada);

        PecaEntity pecaEntity = buscarPeca(id);

       atualizarDadosPeca(pecaEntity, entrada);

       PecaEntity pecaEntityAtualizada = editar(pecaEntity);

       return editarPecaOutputConverter.converter(pecaEntityAtualizada);
    }

    private void validarEntrada(Long id, EditarPecaInput entrada){
        if(Objects.isNull(id)){
            throw new GenericValidationException("Ausência do id da peça");
        }

        Validator.of(entrada)
                .validate(Objects.nonNull(entrada.getNome()), "O nome é obrigatório")
                .validate(Objects.nonNull(entrada.getMarca()), "A marca é obrigatório")
                .validate(Objects.nonNull(entrada.getPreco()), "O preço é obrigatório")
                .validate(Objects.nonNull(entrada.getQuantidade()), "A quantidade é obrigatória")
                .validate(Objects.nonNull(entrada.getPreco()), "O preço é obrigatŕoio")
                .get();
    }

    private PecaEntity buscarPeca(Long id){
        return iPecaDataProvider.buscarPorId(id)
                .orElseThrow(() -> new GenericValidationException(String.format("Peça com id %d não encontrada", id)));
    }

    private void atualizarDadosPeca(PecaEntity pecaEntity, EditarPecaInput editarPecaInput){
        pecaEntity.setNome(editarPecaInput.getNome());
        pecaEntity.setMarca(editarPecaInput.getMarca());
        pecaEntity.setPreco(editarPecaInput.getPreco());
        pecaEntity.setQuantidade(editarPecaInput.getQuantidade());
        pecaEntity.setDescricao(editarPecaInput.getDescricao());
    }

    private PecaEntity editar(PecaEntity pecaEntity){
        return iPecaDataProvider.editar(pecaEntity);
    }

}
