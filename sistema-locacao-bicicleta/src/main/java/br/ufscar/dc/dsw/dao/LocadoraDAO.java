package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.Locadora;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocadoraDAO {

    public void cadastrarLocadora(Locadora locadora) throws SQLException {
        String sql = "INSERT INTO Locadoras (email, senha, cnpj, nome, cidade) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, locadora.getEmail());
            stmt.setString(2, locadora.getSenha());
            stmt.setString(3, locadora.getCnpj());
            stmt.setString(4, locadora.getNome());
            stmt.setString(5, locadora.getCidade());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchLocadora(Locadora locadora) throws SQLException {
        String sql = "SELECT * FROM Locadoras WHERE email = ?";
        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, locadora.getEmail());
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            locadora.setSenha(rs.getString("senha"));
            locadora.setCnpj(rs.getString("cnpj"));
            locadora.setNome(rs.getString("nome"));
            locadora.setCidade(rs.getString("cidade"));

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Locadora> listaLocadora() throws SQLException {
        List<Locadora> locadoras = new ArrayList<>();
        String sql = "SELECT * FROM Locadoras";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Locadora locadora = new Locadora();
                locadora.setEmail(rs.getString("email"));
                locadora.setSenha(rs.getString("senha"));
                locadora.setCnpj(rs.getString("cnpj"));
                locadora.setNome(rs.getString("nome"));
                locadora.setCidade(rs.getString("cidade"));

                locadoras.add(locadora);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locadoras;
    }

    public void updateLocadora (Locadora locadora) throws SQLException {
        String sql = "UPDATE Locadoras SET senha = ?, cnpj = ?, nome = ?, cidade = ? WHERE email = ?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, locadora.getSenha());
            stmt.setString(2, locadora.getCnpj());
            stmt.setString(3, locadora.getNome());
            stmt.setString(4, locadora.getCidade());
            stmt.setString(5, locadora.getEmail());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLocadora (Locadora locadora) throws SQLException {
        String sql = "DELETE FROM Locadoras WHERE email = ?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, locadora.getEmail());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}