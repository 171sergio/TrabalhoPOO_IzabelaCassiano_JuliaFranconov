package org.teiacoltec.poo.tp4.dao.jdbc;

import org.teiacoltec.poo.tp4.*;
import org.teiacoltec.poo.tp4.dao.NotaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaDAOJdbc implements NotaDAO {

    private final Connection conn;

    public NotaDAOJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Nota salvar(Nota nota) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT OR REPLACE INTO notas(aluno_login,atividade_id,valor,data,observacoes) VALUES(?,?,?,?,?)")) {
            ps.setString(1, nota.getAluno().getLogin());
            ps.setInt(2, nota.getAtividade().getId());
            ps.setFloat(3, nota.getValor());
            ps.setLong(4, nota.getDataLancamento() != null ? nota.getDataLancamento().getTime() : System.currentTimeMillis());
            ps.setString(5, nota.getObservacoes() == null ? "" : nota.getObservacoes());
            ps.executeUpdate();
            return nota;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Nota atualizar(Nota nota) {
        return salvar(nota);
    }

    @Override
    public boolean remover(Nota nota) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM notas WHERE aluno_login=? AND atividade_id=?")) {
            ps.setString(1, nota.getAluno().getLogin());
            ps.setInt(2, nota.getAtividade().getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Nota> listarTodos() {
        List<Nota> list = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT aluno_login,atividade_id,valor,data,observacoes FROM notas")) {
            while (rs.next()) {
                Pessoa aluno = new PessoaDAOJdbc(conn).buscarPorLogin(rs.getString("aluno_login"));
                Atividade atividade = new AtividadeDAOJdbc(conn).buscarPorId(rs.getInt("atividade_id"));
                Nota n = new Nota((Aluno) aluno, atividade, rs.getFloat("valor"), rs.getString("observacoes"));
                n.setDataLancamento(new java.util.Date(rs.getLong("data")));
                list.add(n);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Nota> listarPorAluno(Aluno aluno) {
        List<Nota> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT aluno_login,atividade_id,valor,data,observacoes FROM notas WHERE aluno_login=?")) {
            ps.setString(1, aluno.getLogin());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Atividade atividade = new AtividadeDAOJdbc(conn).buscarPorId(rs.getInt("atividade_id"));
                    Nota n = new Nota(aluno, atividade, rs.getFloat("valor"), rs.getString("observacoes"));
                    n.setDataLancamento(new java.util.Date(rs.getLong("data")));
                    list.add(n);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Nota buscarPorAlunoEAtividade(Aluno aluno, Atividade atividade) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT valor,data,observacoes FROM notas WHERE aluno_login=? AND atividade_id=?")) {
            ps.setString(1, aluno.getLogin());
            ps.setInt(2, atividade.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Nota n = new Nota(aluno, atividade, rs.getFloat("valor"), rs.getString("observacoes"));
                    n.setDataLancamento(new java.util.Date(rs.getLong("data")));
                    return n;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
