package org.teiacoltec.poo.tp4.infra;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Inicializa o schema do banco de dados.
 */
public class SchemaInitializer {
    public static void ensureSchema(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS pessoas (login TEXT PRIMARY KEY, nome TEXT, cpf TEXT, tipo TEXT, senha TEXT)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS turmas (id INTEGER PRIMARY KEY, nome TEXT, descricao TEXT, inicio INTEGER, fim INTEGER)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS turma_participantes (turma_id INTEGER, pessoa_login TEXT, PRIMARY KEY (turma_id, pessoa_login))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS atividades (id INTEGER PRIMARY KEY, nome TEXT, descricao TEXT, inicio INTEGER, fim INTEGER, valor REAL)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS notas (aluno_login TEXT, atividade_id INTEGER, valor REAL, data INTEGER, observacoes TEXT, PRIMARY KEY (aluno_login, atividade_id))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS tarefas (id INTEGER PRIMARY KEY, nome TEXT, descricao TEXT, prazo INTEGER, concluida INTEGER, atividade_id INTEGER, responsavel_login TEXT, criacao INTEGER, conclusao INTEGER, observacoes TEXT)");
        }
    }
}