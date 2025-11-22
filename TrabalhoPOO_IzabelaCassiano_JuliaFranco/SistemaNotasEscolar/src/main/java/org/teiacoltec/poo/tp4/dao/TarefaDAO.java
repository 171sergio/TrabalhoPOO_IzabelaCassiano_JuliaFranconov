package org.teiacoltec.poo.tp4.dao;

import org.teiacoltec.poo.tp4.Tarefa;
import org.teiacoltec.poo.tp4.Pessoa;

import java.util.List;

/**
 * DAO para tarefas.
 */
public interface TarefaDAO {
    Tarefa salvar(Tarefa tarefa);
    Tarefa atualizar(Tarefa tarefa);
    boolean remover(Tarefa tarefa);
    List<Tarefa> listarTodos();
    List<Tarefa> listarPorResponsavel(Pessoa responsavel);
}