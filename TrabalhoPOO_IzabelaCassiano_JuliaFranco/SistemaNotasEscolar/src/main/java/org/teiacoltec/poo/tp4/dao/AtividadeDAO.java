package org.teiacoltec.poo.tp4.dao;

import org.teiacoltec.poo.tp4.Atividade;

import java.util.List;

/**
 * DAO para atividades.
 */
public interface AtividadeDAO {
    Atividade salvar(Atividade atividade);
    Atividade atualizar(Atividade atividade);
    boolean remover(Atividade atividade);
    List<Atividade> listarTodos();
    Atividade buscarPorId(int id);
}