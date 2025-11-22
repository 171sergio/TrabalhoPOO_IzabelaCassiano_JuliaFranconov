package org.teiacoltec.poo.tp4.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fabrica de conexoes JDBC. Por padrao usa SQLite em arquivo local.
 */
public class ConnectionFactory {
    private static final String DEFAULT_URL = "jdbc:sqlite:SistemaNotasEscolar/notas.db";

    public static Connection getConnection() throws SQLException {
        String url = System.getProperty("sistema.notas.jdbc.url", DEFAULT_URL);
        return DriverManager.getConnection(url);
    }
}