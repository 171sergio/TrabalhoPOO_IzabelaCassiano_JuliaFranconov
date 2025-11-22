package org.teiacoltec.poo.tp4.dao;

import org.teiacoltec.poo.tp4.Nota;
import org.teiacoltec.poo.tp4.Aluno;
import org.teiacoltec.poo.tp4.Atividade;

import java.util.List;

/**
 * DAO para notas.
 */
public interface NotaDAO {
    Nota salvar(Nota nota);
    Nota atualizar(Nota nota);
    boolean remover(Nota nota);
    List<Nota> listarTodos();
    List<Nota> listarPorAluno(Aluno aluno);
    Nota buscarPorAlunoEAtividade(Aluno aluno, Atividade atividade);
}