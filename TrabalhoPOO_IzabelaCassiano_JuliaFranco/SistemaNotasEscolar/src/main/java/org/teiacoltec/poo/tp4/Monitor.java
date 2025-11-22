package org.teiacoltec.poo.tp4;

import java.util.Date;

/**
 * Representa um monitor.
 */
public class Monitor extends Pessoa {
    private String matricula;
    private String curso;

    public Monitor(String cpf, String nome, Date nascimento, String email, String endereco,
            String matricula, String curso, String login, String senha) {
        super(cpf, nome, nascimento, email, endereco, login, senha);
        this.matricula = matricula;
        this.curso = curso;
    }

    public Monitor(String cpf, String nome, Date nascimento, String email, String endereco,
            String matricula, String curso, String login, String senha, boolean senhaJaCriptografada) {
        super(cpf, nome, nascimento, email, endereco, login, senha, senhaJaCriptografada);
        this.matricula = matricula;
        this.curso = curso;
    }

    public Monitor(String nome, String matricula, String login, String senha) {
        super("000.000.000-00", nome, new Date(), "email@exemplo.com", "Endereço não informado", login, senha);
        this.matricula = matricula;
        this.curso = "Não informado";
    }

    @Override
    public String obterInformacoes() {
        return super.obterInformacoes() + "\n"
                + "Matricula: " + matricula + "\n"
                + "Curso: " + curso + "\n"
                + "Perfil: Monitor";
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    @Override
    public String toString() { return "Monitor: " + getNome() + " (" + matricula + ")"; }
}