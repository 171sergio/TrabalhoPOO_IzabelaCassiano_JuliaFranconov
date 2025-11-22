package org.teiacoltec.poo.tp4.dao.memory;

import org.teiacoltec.poo.tp4.*;
import org.teiacoltec.poo.tp4.dao.TurmaDAO;

import java.util.List;
import java.util.stream.Collectors;

public class TurmaDAOInMemory implements TurmaDAO {
    private final InMemoryStore store;
    public TurmaDAOInMemory(InMemoryStore store) { this.store = store; }
    @Override public Turma salvar(Turma turma) { store.turmas.add(turma); return turma; }
    @Override public Turma atualizar(Turma turma) { return turma; }
    @Override public boolean remover(Turma turma) { return store.turmas.remove(turma); }
    @Override public List<Turma> listarTodos() { return store.turmas.stream().collect(Collectors.toList()); }
    @Override public Turma buscarPorId(int id) { return store.turmas.stream().filter(t -> t.getId() == id).findFirst().orElse(null); }
    @Override public void adicionarParticipante(Turma turma, Pessoa pessoa) throws PessoaJaParticipanteException { turma.adicionarParticipante(pessoa); }
    @Override public void removerParticipante(Turma turma, Pessoa pessoa) throws PessoaNaoEncontradaException { turma.removerParticipante(pessoa); }
}