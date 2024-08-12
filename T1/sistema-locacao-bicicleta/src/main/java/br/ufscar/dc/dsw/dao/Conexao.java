package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/sistemalocacao";
    private static final String user = "root";
    private static final String password = "";
    private Connection conn = null;
    
    public Conexao() {
        try {
            // Setup Banco de dados MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Imprime a exceção e encerra o programa se o driver não for encontrado
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    public Connection getConexao() {
        if (conn == null || !isValidConnection(conn)) {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                // Imprime a exceção e encerra o programa se não conseguir obter a conexão
                throw new RuntimeException("Failed to connect to the database.", e);
            }
        }
        return conn;
    }

    private boolean isValidConnection(Connection conn) {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
