package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.Cliente;
import br.ufscar.dc.dsw.conexao.Conexao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    public void cadastrarUsuario(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Clientes (email, senha, cpf, name, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail());
            stmt.setString(2, cliente.getSenha());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getNome());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getGenero());
            stmt.setString(7, cliente.getDataNascimento());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readUsuario(Cliente cliente) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE email = ?";
        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUsuario (Cliente cliente) throws SQLException {
        String sql = "UPDATE Clientes SET senha = ?, cpf = ?, name = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE email = ?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getSenha());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getGenero());
            stmt.setString(6, cliente.getDataNascimento());
            stmt.setString(7, cliente.getEmail());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUsuario (Cliente cliente) throws SQLException {
        String sql = "DELETE FROM Clientes = ?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}