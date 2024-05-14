package io.github.Renan2411.exception;

public class PedidoNaoEncontradoExecption extends RegraNegocioException{
    public PedidoNaoEncontradoExecption() {
        super("Pedido não encontrado");
    }
}
