package org.teiacoltec.poo.tp4;

import java.util.List;
import java.util.Optional;

/**
 * Autenticacao de usuarios.
 */
public class Autenticacao {
    private Pessoa usuarioLogado;

    public Pessoa autenticar(String login, String senha, List<Pessoa> usuarios) throws CredenciaisInvalidasException {
        if (login == null || login.trim().isEmpty()) throw new CredenciaisInvalidasException("Login nao pode ser vazio.");
        if (senha == null || senha.trim().isEmpty()) throw new CredenciaisInvalidasException("Senha nao pode ser vazia.");

        Optional<Pessoa> usuarioEncontrado = usuarios.stream()
                .filter(pessoa -> pessoa.getLogin().equals(login))
                .filter(pessoa -> pessoa.verificarSenha(senha))
                .findFirst();

        if (usuarioEncontrado.isPresent()) {
            this.usuarioLogado = usuarioEncontrado.get();
            return this.usuarioLogado;
        }
        throw new CredenciaisInvalidasException("Login ou senha incorretos.");
    }

    public void logout(Pessoa usuario) {
        if (this.usuarioLogado != null && this.usuarioLogado.equals(usuario)) this.usuarioLogado = null;
    }

    public boolean isUsuarioLogado() { return this.usuarioLogado != null; }
    public Pessoa getUsuarioLogado() { return this.usuarioLogado; }

    public boolean verificarPermissao(String tipoAcao) throws AcessoNaoAutorizadoException {
        if (!isUsuarioLogado()) throw new AcessoNaoAutorizadoException("Nenhum usuario esta logado no sistema.");
        if (usuarioLogado instanceof Professor) return true;
        if (usuarioLogado instanceof Monitor) return tipoAcao.equals("visualizar") || tipoAcao.equals("lancar_nota");
        if (usuarioLogado instanceof Aluno) return tipoAcao.equals("visualizar");
        return false;
    }
}