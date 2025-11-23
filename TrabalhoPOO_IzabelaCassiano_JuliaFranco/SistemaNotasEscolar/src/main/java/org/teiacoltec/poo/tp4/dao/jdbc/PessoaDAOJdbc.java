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
            ps.setString(5, pessoa.getSenhaCriptografada());
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
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT login,nome,cpf,tipo,senha FROM pessoas")) {
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Pessoa p;
                switch (tipo) {
                    case "Aluno":
                        p = new Aluno(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), rs.getString("senha"), true);
                        break;
                    case "Monitor":
                        p = new Monitor(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), rs.getString("senha"), true);
                        break;
                    default:
                        p = new Professor(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), rs.getString("senha"), true);
                        break;
                }
                list.add(p);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    @Override
    public Pessoa buscarPorLogin(String login) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT login,nome,cpf,tipo,senha FROM pessoas WHERE login=?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    switch (tipo) {
                        case "Aluno":
                            return new Aluno(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), rs.getString("senha"), true);
                        case "Monitor":
                            return new Monitor(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), rs.getString("senha"), true);
                        default:
                            return new Professor(rs.getString("cpf"), rs.getString("nome"), new java.util.Date(), "", "", "", "", rs.getString("login"), rs.getString("senha"), true);
                    }
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
}
