package br.ce.waquino.servicos;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void test(){
        Assert.assertTrue(true); //verifica se a expressão é true
        Assert.assertFalse(false); //verifica se a expressão é false

        Assert.assertEquals(1,1); //Verifica se expressão é igual a uma outra. Ela trata os tipor de maneira diferentes a depender do tipo
        Assert.assertEquals(0.51, 0.51, 0.01); //O terceiro parametro para Double e Float indica uma margem de erro para verificar a assertiva

        //O boxing e unboxnig não existe nas Assertivas
        int i = 5;
        Integer j = 5;
        //Assert.assertEquals(i, j); //Vai dar false pois os tipo não batem
        Assert.assertEquals(Integer.valueOf(i), j); //Vai dar false pois os tipo não batem
        Assert.assertEquals(i, (int) j); //Vai dar false pois os tipo não batem

        Assert.assertEquals("bola", "bola");
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("b"));

        Usuario usuario1 = new Usuario("Usuario 1");
        Usuario usuario2 = new Usuario("Usuario 1");
        Usuario usuario3 = null;

        //Falso pois seus ids do objeto são difrentes, já que utiliza o equals da classe Object quando não há uma implementada
        Assert.assertEquals(usuario1, usuario2);

        //Verifica se são a mesma instância
        Assert.assertSame(usuario2, usuario1);

        //Verifica se é nulo
        Assert.assertNull(usuario3);

    }

}
