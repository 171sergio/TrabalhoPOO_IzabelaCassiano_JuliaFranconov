package org.teiacoltec.poo.tp4.dao;

import org.teiacoltec.poo.tp4.Pessoa;

import java.util.List;

/**
 * DAO para pessoas.
 */
public interface PessoaDAO {
    Pessoa salvar(Pessoa pessoa);
    Pessoa atualizar(Pessoa pessoa);
    boolean remover(Pessoa pessoa);
    List<Pessoa> listarTodos();
    Pessoa buscarPorLogin(String login);
}