package org.teiacoltec.poo.tp4;

/**
 * Excecao de credenciais invalidas.
 */
public class CredenciaisInvalidasException extends Exception {
    public CredenciaisInvalidasException() { super("Login ou senha invalidos."); }
    public CredenciaisInvalidasException(String mensagem) { super(mensagem); }
    public CredenciaisInvalidasException(String mensagem, Throwable causa) { super(mensagem, causa); }
}