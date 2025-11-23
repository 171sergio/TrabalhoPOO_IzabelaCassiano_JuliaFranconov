package org.teiacoltec.poo.tp4.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DEFAULT_URL = "jdbc:sqlite:notas.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("ERRO CRÍTICO: Driver SQLite não encontrado no classpath. Verifique a pasta 'lib'.", e);
        }
        String url = System.getProperty("sistema.notas.jdbc.url", DEFAULT_URL);
        return DriverManager.getConnection(url);
    }
}
