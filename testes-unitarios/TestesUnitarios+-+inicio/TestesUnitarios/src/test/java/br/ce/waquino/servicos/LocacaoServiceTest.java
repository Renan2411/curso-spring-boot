package br.ce.waquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;

public class LocacaoServiceTest {
    @Test
    public void teste() {
        //Cenário
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Filme filme = new Filme("Teste Unitário", 10, precoLocacaoEsperado);
        Usuario usuario = new Usuario("Renan");
        LocacaoService locacao = new LocacaoService();

        //Ação
        Locacao locacaoSalva = locacao.alugarFilme(usuario, filme);

        //Verificação
        Assert.assertTrue(isMesmaData(locacaoSalva.getDataLocacao(), dataLocacao));
        Assert.assertTrue(isMesmaData(locacaoSalva.getDataRetorno(), DataRetornoEsperada));
        Assert.assertTrue(locacaoSalva.getValor().equals(precoLocacaoEsperado));
    }
}
