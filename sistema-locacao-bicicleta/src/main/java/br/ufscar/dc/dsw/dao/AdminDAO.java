package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.Administrador;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDAO {

    public void cadastrarAdmin(Administrador admin) throws SQLException {
        String sql = "INSERT INTO Administradores (email, senha) VALUES (?, ?)";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, admin.getEmail());
            stmt.setString(2, admin.getSenha());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}