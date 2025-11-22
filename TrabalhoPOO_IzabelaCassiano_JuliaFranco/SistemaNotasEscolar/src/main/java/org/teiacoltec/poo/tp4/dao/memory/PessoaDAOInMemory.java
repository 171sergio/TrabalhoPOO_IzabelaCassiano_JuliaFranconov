package org.teiacoltec.poo.tp4.dao.memory;

import org.teiacoltec.poo.tp4.Pessoa;
import org.teiacoltec.poo.tp4.dao.PessoaDAO;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaDAOInMemory implements PessoaDAO {
    private final InMemoryStore store;
    public PessoaDAOInMemory(InMemoryStore store) { this.store = store; }
    @Override public Pessoa salvar(Pessoa pessoa) { store.pessoas.add(pessoa); return pessoa; }
    @Override public Pessoa atualizar(Pessoa pessoa) { return pessoa; }
    @Override public boolean remover(Pessoa pessoa) { return store.pessoas.remove(pessoa); }
    @Override public List<Pessoa> listarTodos() { return store.pessoas.stream().collect(Collectors.toList()); }
    @Override public Pessoa buscarPorLogin(String login) { return store.pessoas.stream().filter(p -> p.getLogin().equals(login)).findFirst().orElse(null); }
}