package org.teiacoltec.poo.tp4.dao.memory;

import org.teiacoltec.poo.tp4.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Armazena dados em memoria para uso quando JDBC nao estiver disponível.
 */
public class InMemoryStore {
    public final List<Pessoa> pessoas = new ArrayList<>();
    public final List<Turma> turmas = new ArrayList<>();
    public final List<Atividade> atividades = new ArrayList<>();
    public final List<Nota> notas = new ArrayList<>();
    public final List<Tarefa> tarefas = new ArrayList<>();
}