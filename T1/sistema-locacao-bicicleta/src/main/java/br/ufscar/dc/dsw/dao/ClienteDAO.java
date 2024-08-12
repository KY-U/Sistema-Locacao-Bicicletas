package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.Cliente;
//import br.ufscar.dc.dsw.dao.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO extends Conexao{

    public void cadastrarUsuario(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Clientes (email, senha, cpf, nome, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
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

    public Cliente searchUsuario(String email) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE email = ?";
        Cliente cliente = null; // Inicializa o cliente como null

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
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

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Lançar a exceção para que o chamador possa tratar o erro
            throw e;
        }

        return cliente;
    }

    public List<Cliente> listaUsuario() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";
        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
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

    public void updateCliente(String emailOriginal, Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET email = ?, senha = ?, cpf = ?, nome = ?, telefone = ?, sexo = ?, dataNascimento = ? WHERE email = ?";
        try (PreparedStatement statement = this.getConexao().prepareStatement(sql)) {
            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setString(3, cliente.getCpf());
            statement.setString(4, cliente.getNome());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setDate(7, cliente.getDataNascimento());
            statement.setString(8, emailOriginal);
            statement.executeUpdate();
        }
    }

    public void deleteUsuario(String email) throws SQLException {
        String sql = "DELETE FROM Clientes WHERE email = ?";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
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

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
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