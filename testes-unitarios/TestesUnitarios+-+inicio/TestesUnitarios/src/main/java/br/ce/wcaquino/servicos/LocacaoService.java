package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.ListaFilmeVaziaException;
import br.ce.wcaquino.exceptions.LocadoraException;
import org.junit.Assert;
import org.junit.Test;

public class LocacaoService {

    public String vPublica;
    protected String vProtegida;
    private String vPrivada;
    String vDefault;

    public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {

        if(usuario == null){
            throw new LocadoraException("Usuário vazio");
        }

        if(filme == null){
            throw new LocadoraException("Filme vazio");
        }

       verificaEstoque(filme);

        Locacao locacao = new Locacao();
        locacao.setFilme(filme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filme.getPrecoLocacao());

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }

    public List<Locacao> alugarFilmes(Usuario usuario, List<Filme> filmes) throws LocadoraException, FilmeSemEstoqueException, ListaFilmeVaziaException {
        if(usuario == null){
            throw new LocadoraException("Usuário vazio");
        }

        if (filmes.isEmpty()){
            throw new ListaFilmeVaziaException();
        }
        verificaEstoques(filmes);

        List<Locacao> locacoes = new ArrayList<>();

        for(Filme filme : filmes){
            Locacao locacao = new Locacao();
            locacao.setFilme(filme);
            locacao.setUsuario(usuario);
            locacao.setDataLocacao(new Date());
            locacao.setValor(filme.getPrecoLocacao());

            //Entrega no dia seguinte
            Date dataEntrega = new Date();
            dataEntrega = adicionarDias(dataEntrega, 1);
            locacao.setDataRetorno(dataEntrega);

            //Salvando a locacao...
            //TODO adicionar método para salvar

            locacoes.add(locacao);
        }

        return locacoes;
    }

    public void verificaEstoques(List<Filme> filmes) throws FilmeSemEstoqueException {
        for(Filme filme : filmes){
            verificaEstoque(filme);
        }
    }

    public void verificaEstoque(Filme filme) throws FilmeSemEstoqueException {
        if(filme.getEstoque() == 0){
            throw new FilmeSemEstoqueException(String.format("O filme %s não possui estoque", filme.getNome()));
        }
    }

    //CRIAÇÃO DE UM TESTE MANUAL E SIMPLES
//    public static void main(String[] args) {
//
//
//        //Cenário
//        Double precoLocacaoEsperado = 10.0;
//        Date dataLocacao = new Date();
//        Date DataRetornoEsperada = adicionarDias(dataLocacao, 1);
//
//        Filme filme = new Filme("Teste Unitário", 10, precoLocacaoEsperado);
//        Usuario usuario = new Usuario("Renan");
//        LocacaoService locacao = new LocacaoService();
//
//        //Ação
//        Locacao locacaoSalva = locacao.alugarFilme(usuario, filme);
//
//        //Verificação
//        System.out.println(isMesmaData(locacaoSalva.getDataLocacao(), dataLocacao));
//        System.out.println(isMesmaData(locacaoSalva.getDataRetorno(), DataRetornoEsperada));
//        System.out.println(locacaoSalva.getValor().equals(5));
//
//    }

	//CRIAÇÃO DE UM TESTE COM JUNIT

}