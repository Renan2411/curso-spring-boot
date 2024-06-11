package br.ce.wcaquino.exceptions;

public class ListaFilmeVaziaException extends Exception{
    public ListaFilmeVaziaException(){
        super("A lista de filmes não pode ser vazia");
    }
}
