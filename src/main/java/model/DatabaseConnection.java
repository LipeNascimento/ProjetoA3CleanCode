package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;

public class DatabaseConnection {
    private static final String DB_PATH = "data/projeto_a3.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    static {
        // Garante que a pasta 'data/' existe
        new File("data").mkdirs();
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexão com SQLite estabelecida em: " + DB_PATH);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com SQLite: " + e.getMessage());
        }
        return conn;
    }
}