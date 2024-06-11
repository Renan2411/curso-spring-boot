package br.com.gerenciaautoeletrica.domain.usecase.peca.criar;

import br.com.gerenciaautoeletrica.domain.entity.entities.PecaEntity;
import br.com.gerenciaautoeletrica.domain.interfaces.dataprovider.IPecaDataProvider;
import br.com.gerenciaautoeletrica.domain.usecase.peca.criar.converter.CriarPecaOutputConverter;
import br.com.gerenciaautoeletrica.domain.validation.Validator;
import lombok.Builder;

import java.util.Objects;

@Builder
public class CriarPecaUseCase {

    private final IPecaDataProvider iPecaDataProvider;
    private final CriarPecaOutputConverter criarPecaOutputConverter;

    public CriarPecaOutput executar(CriarPecaInput entrada) {
        validarEntrada(entrada);

        PecaEntity pecaEntity = montarPecaEntity(entrada);

        PecaEntity pecaSalva = criarPeca(pecaEntity);

        return criarPecaOutputConverter.converter(pecaSalva);
    }

    private void validarEntrada(CriarPecaInput entrada) {
        Validator.of(entrada)
                .validate(Objects.nonNull(entrada.getNome()), "O Nome é obrigatório")
                .validate(Objects.nonNull(entrada.getPreco()), "O Preço é obrigatório")
                .validate(Objects.nonNull(entrada.getQuantidade()), "A Quantidade é obrigatório")
                .validate(Objects.nonNull(entrada.getMarca()), "A Marca é obrigatório")
                .get();
    }

    private PecaEntity montarPecaEntity(CriarPecaInput entrada) {
        return PecaEntity.builder()
                .nome(entrada.getNome())
                .preco(entrada.getPreco())
                .descricao(entrada.getDescricao())
                .quantidade(entrada.getQuantidade())
                .marca(entrada.getMarca())
                .build();
    }

    private PecaEntity criarPeca(PecaEntity pecaEntity){
        return iPecaDataProvider.criar(pecaEntity);
    }

}
