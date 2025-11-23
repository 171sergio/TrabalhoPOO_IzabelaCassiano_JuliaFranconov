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
            throw new SQLException("Driver SQLite não encontrado na biblioteca!", e);
        }
        String url = System.getProperty("sistema.notas.jdbc.url", DEFAULT_URL);
        return DriverManager.getConnection(url);
    }
}
