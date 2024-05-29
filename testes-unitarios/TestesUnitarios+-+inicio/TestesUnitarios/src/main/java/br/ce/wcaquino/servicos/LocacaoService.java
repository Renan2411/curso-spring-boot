package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class LocacaoService {

    public String vPublica;
    protected String vProtegida;
    private String vPrivada;
    String vDefault;

    public Locacao alugarFilme(Usuario usuario, Filme filme) throws Exception {
        if(filme.getEstoque() == 0){
            throw new Exception("Filme sem estoque");
        }

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