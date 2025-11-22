package org.teiacoltec.poo.tp4.dao.memory;

import org.teiacoltec.poo.tp4.Atividade;
import org.teiacoltec.poo.tp4.dao.AtividadeDAO;

import java.util.List;
import java.util.stream.Collectors;

public class AtividadeDAOInMemory implements AtividadeDAO {
    private final InMemoryStore store;
    public AtividadeDAOInMemory(InMemoryStore store) { this.store = store; }
    @Override public Atividade salvar(Atividade atividade) { store.atividades.add(atividade); return atividade; }
    @Override public Atividade atualizar(Atividade atividade) { return atividade; }
    @Override public boolean remover(Atividade atividade) { return store.atividades.remove(atividade); }
    @Override public List<Atividade> listarTodos() { return store.atividades.stream().collect(Collectors.toList()); }
    @Override public Atividade buscarPorId(int id) { return store.atividades.stream().filter(a -> a.getId() == id).findFirst().orElse(null); }
}