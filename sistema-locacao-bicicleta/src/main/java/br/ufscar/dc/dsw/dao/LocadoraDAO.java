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

}