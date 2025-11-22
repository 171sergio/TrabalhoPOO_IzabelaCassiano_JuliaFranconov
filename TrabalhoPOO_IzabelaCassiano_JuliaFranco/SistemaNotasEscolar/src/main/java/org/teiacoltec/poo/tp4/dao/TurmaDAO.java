package org.teiacoltec.poo.tp4.dao;

import org.teiacoltec.poo.tp4.*;

import java.util.List;

/**
 * DAO para turmas.
 */
public interface TurmaDAO {
    Turma salvar(Turma turma);
    Turma atualizar(Turma turma);
    boolean remover(Turma turma);
    List<Turma> listarTodos();
    Turma buscarPorId(int id);
    void adicionarParticipante(Turma turma, Pessoa pessoa) throws PessoaJaParticipanteException;
    void removerParticipante(Turma turma, Pessoa pessoa) throws PessoaNaoEncontradaException;
}