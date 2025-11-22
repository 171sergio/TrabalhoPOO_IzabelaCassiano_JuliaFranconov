package org.teiacoltec.poo.tp4.dao.memory;

import org.teiacoltec.poo.tp4.Pessoa;
import org.teiacoltec.poo.tp4.Tarefa;
import org.teiacoltec.poo.tp4.dao.TarefaDAO;

import java.util.List;
import java.util.stream.Collectors;

public class TarefaDAOInMemory implements TarefaDAO {
    private final InMemoryStore store;
    public TarefaDAOInMemory(InMemoryStore store) { this.store = store; }
    @Override public Tarefa salvar(Tarefa tarefa) { store.tarefas.add(tarefa); return tarefa; }
    @Override public Tarefa atualizar(Tarefa tarefa) { return tarefa; }
    @Override public boolean remover(Tarefa tarefa) { return store.tarefas.remove(tarefa); }
    @Override public List<Tarefa> listarTodos() { return store.tarefas.stream().collect(Collectors.toList()); }
    @Override public List<Tarefa> listarPorResponsavel(Pessoa responsavel) { return store.tarefas.stream().filter(t -> t.getResponsavel() != null && t.getResponsavel().equals(responsavel)).collect(Collectors.toList()); }
}