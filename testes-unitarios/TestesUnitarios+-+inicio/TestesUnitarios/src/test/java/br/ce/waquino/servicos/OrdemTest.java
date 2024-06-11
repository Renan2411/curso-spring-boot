package br.ce.waquino.servicos;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Executa em ordem alfabética
public class OrdemTest {

    public static int contador = 0;

    @Test
    public void inicia(){
        contador++;
    }

    @Test
    public void verifica(){
        assertEquals(1, contador);
    }

//    @Test //Forma possível quando removemos o @Test dos métodos
//    public void testeGeral(){
//        inicia();
//        verifica();
//    }

}
