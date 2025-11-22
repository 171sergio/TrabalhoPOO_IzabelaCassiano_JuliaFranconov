package org.teiacoltec.poo.tp4;

/**
 * Excecao para acesso nao autorizado.
 */
public class AcessoNaoAutorizadoException extends Exception {
    public AcessoNaoAutorizadoException() { super("Acesso nao autorizado para esta operacao."); }
    public AcessoNaoAutorizadoException(String mensagem) { super(mensagem); }
    public AcessoNaoAutorizadoException(String mensagem, Throwable causa) { super(mensagem, causa); }
}