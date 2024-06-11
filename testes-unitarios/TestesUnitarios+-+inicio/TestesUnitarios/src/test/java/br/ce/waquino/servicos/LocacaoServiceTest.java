package br.ce.waquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.ListaFilmeVaziaException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;

public class LocacaoServiceTest {

    private LocacaoService locacao;
    private static int count = 0;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

//    @Before //Executa algumas ações antes da execução de um teste
//    public void setUp() {
//        System.out.println("Before");
//        count++;
//        locacao = new LocacaoService();
//    }
//
//    @After //Executa algumas ações depois da execução de um tetes
//    public void setDown() {
//        System.out.println("After");
//        count++;
//    }
//
//    @BeforeClass //Executa algumas ações antes da execução de um teste
//    public static void setUpClass() {
//        System.out.println(count);
//        System.out.println("BEFORE CLASS");
//    }
//
//    @AfterClass //Executa algumas ações depois da execução de um tetes
//    public static void setDownClass() {
//        System.out.println(count);
//        System.out.println("AFTER CLASS");
//    }

    @Test
    public void teste() throws Exception {
        //Cenário
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Filme filme = new Filme("Teste Unitário", 10, precoLocacaoEsperado);
        Usuario usuario = new Usuario("Renan");

        //Ação
        Locacao locacaoSalva = locacao.alugarFilme(usuario, filme);

        //Verificação com Assertivas
        Assert.assertTrue(isMesmaData(locacaoSalva.getDataLocacao(), dataLocacao));
        Assert.assertTrue(isMesmaData(locacaoSalva.getDataRetorno(), DataRetornoEsperada));
        Assert.assertTrue(locacaoSalva.getValor().equals(precoLocacaoEsperado));

        System.out.println("Teste");

//        Assert.assertThat(locacaoSalva.getValor(), CoreMatchers.is(0.5));

        //Verificação com Error Collectors
//        error.checkThat(isMesmaData(locacaoSalva.getDataLocacao(), dataLocacao), CoreMatchers.is(false));
//        error.checkThat(isMesmaData(locacaoSalva.getDataRetorno(), DataRetornoEsperada), CoreMatchers.is(true));
//        error.checkThat(locacaoSalva.getValor(), CoreMatchers.is(CoreMatchers.equalTo(0.7)));
    }

    @Test(expected = Exception.class)
    public void deveLancarExcecaoListaFilmeVaziaException() throws FilmeSemEstoqueException, LocadoraException, ListaFilmeVaziaException {
        try {
            List<Filme> filmes = new ArrayList<>();
            locacao.alugarFilmes(montarUsuario(), filmes);
        } catch (ListaFilmeVaziaException e) {
            Assert.assertEquals("A lista de filmes não pode ser vazia", e.getMessage());
        }
    }

    //Lançando Exception Genérica
    @Test(expected = Exception.class)
    public void testeExceptionFilmeSemEstoqueElegante() throws Exception {
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Filme filme = new Filme("Teste Unitário", 0, precoLocacaoEsperado);
        Usuario usuario = new Usuario("Renan");

        Locacao locacaoSalva = locacao.alugarFilme(usuario, filme);
    }

    //Lanaçando exception específica. Isso garante que a exceção ta vindo apenas por um único motivo
    @Test(expected = FilmeSemEstoqueException.class)
    public void testeExceptionFilmeSemEstoqueEleganteEspecifico() throws FilmeSemEstoqueException, LocadoraException {
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Filme filme = new Filme("Teste Unitário", 0, precoLocacaoEsperado);
        Usuario usuario = new Usuario("Renan");
        Locacao locacaoSalva = locacao.alugarFilme(usuario, filme);
    }

    @Test
    public void locacaoSemUsuario() {
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Filme filme = new Filme("Teste Unitário", 0, precoLocacaoEsperado);
        try {
            Locacao locacaoSalva = locacao.alugarFilme(null, filme);
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuário vazio"));
        }
    }

    @Test
    public void locacaoSemFilme() {
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Usuario usuario = new Usuario("Renan");
        try {
            Locacao locacaoSalva = locacao.alugarFilme(usuario, null);
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme vazio"));
        }
    }

    @Test
    public void testeExceptionFilmeSemEstoqueRobusta() {
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Filme filme = new Filme("Teste Unitário", 0, precoLocacaoEsperado);
        Usuario usuario = new Usuario("Renan");
        try {
            Locacao locacaoSalva = locacao.alugarFilme(usuario, filme);
            Assert.fail("Deveria lançar exceção");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme sem estoque"));
        }
    }

    @Test
    public void testeExceptionFilmeVazio() throws LocadoraException, FilmeSemEstoqueException {
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Usuario usuario = new Usuario("Renan");
        //Deve se colocar antes da ação
        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        Locacao locacaoSalva = locacao.alugarFilme(usuario, null);
    }

    @Test
    public void testeExceptionFilmeSemEstoqueRecente() throws Exception {
        Double precoLocacaoEsperado = 10.0;
        Date dataLocacao = new Date();
        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);

        Filme filme = new Filme("Teste Unitário", 0, precoLocacaoEsperado);
        Usuario usuario = new Usuario("Renan");
        //Deve se colocar antes da ação
        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque");

        Locacao locacaoSalva = locacao.alugarFilme(usuario, filme);
    }

    public Filme montarFilme() {
        return new Filme("Teste Unitário", 10, 10.0);
    }

    public Usuario montarUsuario() {
        return new Usuario("Usuario Teste");
    }
}
