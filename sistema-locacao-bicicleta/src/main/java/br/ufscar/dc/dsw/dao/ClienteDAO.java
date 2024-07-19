package main.java.br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.Cliente;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    public void cadastrarUsuario(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Clientes (email, senha, cpf, nome, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail());
            stmt.setString(2, cliente.getSenha());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getNome());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getSexo());
            stmt.setString(7, cliente.getDataNascimento());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchUsuario(Cliente cliente) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE email = ?";
        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail());
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            cliente.setSenha(rs.getString("senha"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setSexo(rs.getString("sexo"));
            cliente.setDataNascimento(rs.getString("data_nascimento"));

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listaUsuario(Cliente cliente) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE email = ?";
        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail());
            
            stmt.executeQuery();
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
            stmt.setString(5, cliente.getSexo());
            stmt.setString(6, cliente.getDataNascimento());
            stmt.setString(7, cliente.getEmail());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUsuario (Cliente cliente) throws SQLException {
        String sql = "DELETE FROM Clientes WHERE email = ?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}