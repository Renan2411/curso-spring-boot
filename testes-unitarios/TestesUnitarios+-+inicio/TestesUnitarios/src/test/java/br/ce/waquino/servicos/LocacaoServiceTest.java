package br.ce.waquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;

public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void teste() throws Exception {
        //Cenário
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Filme filme = new Filme("Teste Unitário", 10, precoLocacaoEsperado);
        Usuario usuario = new Usuario("Renan");
        LocacaoService locacao = new LocacaoService();

        //Ação
        Locacao locacaoSalva = locacao.alugarFilme(usuario, filme);

        //Verificação com Assertivas
        Assert.assertTrue(isMesmaData(locacaoSalva.getDataLocacao(), dataLocacao));
        Assert.assertTrue(isMesmaData(locacaoSalva.getDataRetorno(), DataRetornoEsperada));
        Assert.assertTrue(locacaoSalva.getValor().equals(precoLocacaoEsperado));

//        Assert.assertThat(locacaoSalva.getValor(), CoreMatchers.is(0.5));

        //Verificação com Error Collectors
//        error.checkThat(isMesmaData(locacaoSalva.getDataLocacao(), dataLocacao), CoreMatchers.is(false));
//        error.checkThat(isMesmaData(locacaoSalva.getDataRetorno(), DataRetornoEsperada), CoreMatchers.is(true));
//        error.checkThat(locacaoSalva.getValor(), CoreMatchers.is(CoreMatchers.equalTo(0.7)));
    }

    @Test(expected = Exception.class)
    public void testeExceptionFilmeSemEstoqueElegante() throws Exception {
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Filme filme = new Filme("Teste Unitário", 0, precoLocacaoEsperado);
        Usuario usuario = new Usuario("Renan");
        LocacaoService locacao = new LocacaoService();

        Locacao locacaoSalva = locacao.alugarFilme(usuario, filme);
    }

    @Test
    public void testeExceptionFilmeSemEstoqueRobusta() {
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Filme filme = new Filme("Teste Unitário", 0, precoLocacaoEsperado);
        Usuario usuario = new Usuario("Renan");
        LocacaoService locacao = new LocacaoService();

        try {
            Locacao locacaoSalva = locacao.alugarFilme(usuario, filme);
            Assert.fail("Deveria lançar exceção");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme sem estoque"));
        }

    }
}
