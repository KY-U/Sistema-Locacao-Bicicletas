package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.Administrador;
import br.ufscar.dc.dsw.dao.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDAO extends Conexao {

    public void cadastrarAdmin(Administrador admin) throws SQLException {
        String sql = "INSERT INTO Administradores (email, senha) VALUES (?, ?)";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setString(1, admin.getEmail());
            stmt.setString(2, admin.getSenha());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Administrador getAdmin(String email) throws SQLException {
        Administrador admin = null;
        String sql = "SELECT * FROM Administradores WHERE email = ?";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Administrador();
                admin.setEmail(rs.getString("email"));
                admin.setSenha(rs.getString("senha"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propague a exceção para tratamento posterior
        }

        return admin;
    }

    public List<Administrador> listaAdmin() throws SQLException {
        List<Administrador> admins = new ArrayList<>();
        String sql = "SELECT * FROM Administradores";
        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setEmail(rs.getString("email"));
                admin.setSenha(rs.getString("senha"));

                admins.add(admin);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public void updateAdmin (Administrador admin) throws SQLException {
        String sql = "UPDATE Administradores SET senha = ? WHERE email = ?";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setString(1, admin.getSenha());
            stmt.setString(2, admin.getEmail());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdmin (Administrador admin) throws SQLException {
        String sql = "DELETE FROM Administradores WHERE email = ?";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setString(1, admin.getEmail());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}