package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.Locadora;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocadoraDAO extends Conexao {

    public void cadastrarLocadora(Locadora locadora) throws SQLException {
        String sql = "INSERT INTO Locadoras (email, senha, cnpj, nome, cidade) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
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

    public Locadora searchLocadora(String email) throws SQLException {
        Locadora locadora = null;
        String sql = "SELECT * FROM Locadoras WHERE email = ?";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                locadora = new Locadora();
                locadora.setEmail(rs.getString("email"));
                locadora.setSenha(rs.getString("senha"));
                locadora.setCnpj(rs.getString("cnpj"));
                locadora.setNome(rs.getString("nome"));
                locadora.setCidade(rs.getString("cidade"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locadora;
    }

    public List<Locadora> listaLocadora() throws SQLException {
        List<Locadora> locadoras = new ArrayList<>();
        String sql = "SELECT * FROM Locadoras";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
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

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
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

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setString(1, locadora.getEmail());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Locadora> listLocadorasByCity(String cidade) throws SQLException {
        List<Locadora> listaLocadoras = new ArrayList<>();
        String query = "SELECT * FROM locadoras WHERE cidade = ?";

        try (
             PreparedStatement pstmt = this.getConexao().prepareStatement(query)) {
            pstmt.setString(1, cidade);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String email = rs.getString("email");
                    String senha = rs.getString("senha");
                    String cnpj = rs.getString("cnpj");
                    String nome = rs.getString("nome");
                    String cidadeDb = rs.getString("cidade");
                    Locadora locadora = new Locadora(email, senha, cnpj, nome, cidadeDb);
                    listaLocadoras.add(locadora);
                }
            }
        }
        return listaLocadoras;
    }
}