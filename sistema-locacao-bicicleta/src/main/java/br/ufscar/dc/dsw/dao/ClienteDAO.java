package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.Cliente;
import br.ufscar.dc.dsw.dao.Conexao;
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
            stmt.setDate(7, cliente.getDataNascimento());

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
            cliente.setDataNascimento(rs.getDate("data_nascimento"));

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listaUsuario() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";
        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setDataNascimento(rs.getDate("data_nascimento"));

                clientes.add(cliente);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void updateUsuario (Cliente cliente) throws SQLException {
        String sql = "UPDATE Clientes SET senha = ?, cpf = ?, nome = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE email = ?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getSenha());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getSexo());
            stmt.setDate(6, cliente.getDataNascimento());
            stmt.setString(7, cliente.getEmail());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUsuario(String email) throws SQLException {
        String sql = "DELETE FROM Clientes WHERE email = ?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate(); // Use executeUpdate para operações de escrita
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propaga a exceção para que possa ser tratada pelo chamador
        }
    }

    public Cliente getCliente(String email) throws SQLException {
        Cliente cliente = null;
        String sql = "SELECT * FROM Clientes WHERE email = ?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setDataNascimento(rs.getDate("data_nascimento"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propague a exceção para tratamento posterior
        }

        return cliente;
    }
}