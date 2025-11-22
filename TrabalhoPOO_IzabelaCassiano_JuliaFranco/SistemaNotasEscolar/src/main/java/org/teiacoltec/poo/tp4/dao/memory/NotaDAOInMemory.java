package org.teiacoltec.poo.tp4.dao.memory;

import org.teiacoltec.poo.tp4.*;
import org.teiacoltec.poo.tp4.dao.NotaDAO;

import java.util.List;
import java.util.stream.Collectors;

public class NotaDAOInMemory implements NotaDAO {
    private final InMemoryStore store;
    public NotaDAOInMemory(InMemoryStore store) { this.store = store; }
    @Override public Nota salvar(Nota nota) { store.notas.add(nota); return nota; }
    @Override public Nota atualizar(Nota nota) { return nota; }
    @Override public boolean remover(Nota nota) { return store.notas.remove(nota); }
    @Override public List<Nota> listarTodos() { return store.notas.stream().collect(Collectors.toList()); }
    @Override public List<Nota> listarPorAluno(Aluno aluno) { return store.notas.stream().filter(n -> n.getAluno().equals(aluno)).collect(Collectors.toList()); }
    @Override public Nota buscarPorAlunoEAtividade(Aluno aluno, Atividade atividade) { return store.notas.stream().filter(n -> n.getAluno().equals(aluno) && n.getAtividade().equals(atividade)).findFirst().orElse(null); }
}