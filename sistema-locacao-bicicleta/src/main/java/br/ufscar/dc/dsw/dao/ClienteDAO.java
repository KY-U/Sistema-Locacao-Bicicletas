package main.java.br.ufscar.dc.dsw.dao;

import main.java.br.ufscar.dc.dsw.model.Cliente;
import main.java.br.ufscar.dc.dsw.conexao.Conexao;

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
            stmt.setString(2, cliente.getPassword());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getName());
            stmt.setString(5, cliente.getPhone());
            stmt.setString(6, cliente.getGender());
            stmt.setString(7, cliente.getBirthDate());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}