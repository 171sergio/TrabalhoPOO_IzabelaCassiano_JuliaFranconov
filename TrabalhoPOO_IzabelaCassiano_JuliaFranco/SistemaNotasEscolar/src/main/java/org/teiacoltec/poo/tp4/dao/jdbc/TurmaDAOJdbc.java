package org.teiacoltec.poo.tp4.dao.jdbc;

import org.teiacoltec.poo.tp4.*;
import org.teiacoltec.poo.tp4.dao.TurmaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAOJdbc implements TurmaDAO {
    private final Connection conn;
    public TurmaDAOJdbc(Connection conn) { this.conn = conn; }

    @Override
    public Turma salvar(Turma turma) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT OR REPLACE INTO turmas(id,nome,descricao,inicio,fim) VALUES(?,?,?,?,?)")) {
            ps.setInt(1, turma.getId());
            ps.setString(2, turma.getNome());
            ps.setString(3, turma.getDescricao());
            ps.setLong(4, turma.getInicio() != null ? turma.getInicio().getTime() : 0);
            ps.setLong(5, turma.getFim() != null ? turma.getFim().getTime() : 0);
            ps.executeUpdate();
            return turma;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public Turma atualizar(Turma turma) { return salvar(turma); }

    @Override
    public boolean remover(Turma turma) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM turmas WHERE id=?")) {
            ps.setInt(1, turma.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public List<Turma> listarTodos() {
        List<Turma> list = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT id,nome,descricao,inicio,fim FROM turmas")) {
            while (rs.next()) {
                Turma t = new Turma(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
                        rs.getLong("inicio") == 0 ? new java.util.Date() : new java.util.Date(rs.getLong("inicio")),
                        rs.getLong("fim") == 0 ? new java.util.Date() : new java.util.Date(rs.getLong("fim")));
                list.add(t);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    @Override
    public Turma buscarPorId(int id) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT id,nome,descricao,inicio,fim FROM turmas WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Turma(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
                            rs.getLong("inicio") == 0 ? new java.util.Date() : new java.util.Date(rs.getLong("inicio")),
                            rs.getLong("fim") == 0 ? new java.util.Date() : new java.util.Date(rs.getLong("fim")));
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    @Override
    public void adicionarParticipante(Turma turma, Pessoa pessoa) throws PessoaJaParticipanteException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT OR IGNORE INTO turma_participantes(turma_id,pessoa_login) VALUES(?,?)")) {
            ps.setInt(1, turma.getId());
            ps.setString(2, pessoa.getLogin());
            ps.executeUpdate();
            turma.adicionarParticipante(pessoa);
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void removerParticipante(Turma turma, Pessoa pessoa) throws PessoaNaoEncontradaException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM turma_participantes WHERE turma_id=? AND pessoa_login=?")) {
            ps.setInt(1, turma.getId());
            ps.setString(2, pessoa.getLogin());
            ps.executeUpdate();
            turma.removerParticipante(pessoa);
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}