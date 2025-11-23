package org.teiacoltec.poo.tp4.dao.jdbc;

import org.teiacoltec.poo.tp4.Atividade;
import org.teiacoltec.poo.tp4.dao.AtividadeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtividadeDAOJdbc implements AtividadeDAO {
    private final Connection conn;
    public AtividadeDAOJdbc(Connection conn) { this.conn = conn; }

    @Override
    public Atividade salvar(Atividade atividade) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT OR REPLACE INTO atividades(id,nome,descricao,inicio,fim,valor) VALUES(?,?,?,?,?,?)")) {
            ps.setInt(1, atividade.getId());
            ps.setString(2, atividade.getNome());
            ps.setString(3, atividade.getDescricao());
            ps.setLong(4, atividade.getInicio() != null ? atividade.getInicio().getTime() : 0);
            ps.setLong(5, atividade.getFim() != null ? atividade.getFim().getTime() : 0);
            ps.setFloat(6, atividade.getValor());
            ps.executeUpdate();
            return atividade;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public Atividade atualizar(Atividade atividade) { return salvar(atividade); }

    @Override
    public boolean remover(Atividade atividade) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM atividades WHERE id=?")) {
            ps.setInt(1, atividade.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public List<Atividade> listarTodos() {
        List<Atividade> list = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT id,nome,descricao,inicio,fim,valor FROM atividades")) {
            while (rs.next()) {
                Atividade a = new Atividade(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getLong("inicio") == 0 ? new java.util.Date() : new java.util.Date(rs.getLong("inicio")),
                        rs.getLong("fim") == 0 ? new java.util.Date() : new java.util.Date(rs.getLong("fim")),
                        rs.getFloat("valor")
                );
                list.add(a);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    @Override
    public Atividade buscarPorId(int id) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT id,nome,descricao,inicio,fim,valor FROM atividades WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Atividade(
                            rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
                            rs.getLong("inicio") == 0 ? new java.util.Date() : new java.util.Date(rs.getLong("inicio")),
                            rs.getLong("fim") == 0 ? new java.util.Date() : new java.util.Date(rs.getLong("fim")),
                            rs.getFloat("valor")
                    );
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }
}
