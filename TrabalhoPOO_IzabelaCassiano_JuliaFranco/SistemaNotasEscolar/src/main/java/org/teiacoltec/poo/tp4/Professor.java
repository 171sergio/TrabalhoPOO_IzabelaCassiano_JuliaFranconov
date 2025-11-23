package org.teiacoltec.poo.tp4;

import java.util.Date;

/**
 * Representa um professor.
 */
public class Professor extends Pessoa {
    private String matricula;
    private String formacao;

    public Professor(String cpf, String nome, Date nascimento, String email, String endereco,
            String matricula, String formacao, String login, String senha) {
        super(cpf, nome, nascimento, email, endereco, login, senha);
        this.matricula = matricula;
        this.formacao = formacao;
    }

    public Professor(String cpf, String nome, Date nascimento, String email, String endereco,
            String matricula, String formacao, String login, String senha, boolean senhaJaCriptografada) {
        super(cpf, nome, nascimento, email, endereco, login, senha, senhaJaCriptografada);
        this.matricula = matricula;
        this.formacao = formacao;
    }

    public Professor(String nome, String matricula, String login, String senha) {
        super("000.000.000-00", nome, new Date(), "email@exemplo.com", "Endereço não informado", login, senha);
        this.matricula = matricula;
        this.formacao = "Não informado";
    }

    @Override
    public String obterInformacoes() {
        return super.obterInformacoes() + "\n"
                + "Matricula: " + matricula + "\n"
                + "Formacao: " + formacao + "\n"
                + "Perfil: Professor";
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getFormacao() { return formacao; }
    public void setFormacao(String formacao) { this.formacao = formacao; }

    @Override
    public String toString() { return "Professor: " + getNome() + " (" + matricula + ")"; }
}
