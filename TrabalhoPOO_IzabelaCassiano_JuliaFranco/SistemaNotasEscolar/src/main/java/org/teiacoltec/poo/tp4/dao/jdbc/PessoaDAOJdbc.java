package org.teiacoltec.poo.tp4.dao.jdbc;

import org.teiacoltec.poo.tp4.Pessoa;
import org.teiacoltec.poo.tp4.Aluno;
import org.teiacoltec.poo.tp4.Professor;
import org.teiacoltec.poo.tp4.Monitor;
import org.teiacoltec.poo.tp4.dao.PessoaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAOJdbc implements PessoaDAO {
    private final Connection conn;
    public PessoaDAOJdbc(Connection conn) { this.conn = conn; }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT OR REPLACE INTO pessoas(login,nome,cpf,tipo,senha) VALUES(?,?,?,?,?)")) {
            ps.setString(1, pessoa.getLogin());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getCpf());
            ps.setString(4, pessoa.getClass().getSimpleName());
            ps.setString(5, "");
            ps.executeUpdate();
            return pessoa;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public Pessoa atualizar(Pessoa pessoa) { return salvar(pessoa); }

    @Override
    public boolean remover(Pessoa pessoa) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM pessoas WHERE login=?")) {
            ps.setString(1, pessoa.getLogin());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public List<Pessoa> listarTodos() {
        List<Pessoa> list = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT login,nome,cpf,tipo FROM pessoas")) {
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Pessoa p;
                if ("Aluno".equals(tipo)) p = new Aluno(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), "");
                else if ("Monitor".equals(tipo)) p = new Monitor(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), "");
                else p = new Professor(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), "");
                list.add(p);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    @Override
    public Pessoa buscarPorLogin(String login) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT login,nome,cpf,tipo FROM pessoas WHERE login=?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    if ("Aluno".equals(tipo)) return new Aluno(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), "");
                    if ("Monitor".equals(tipo)) return new Monitor(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), "");
                    return new Professor(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), "");
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
}