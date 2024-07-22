package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.Locacoes;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocacoesDAO {

    public void cadastrarLocacoes(Locacoes locacoes) throws SQLException {
        String sql = "INSERT INTO Locacoes (cpf_cliente, cnpj_locadora, data_horario) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, locacoes.getCpfCliente());
            stmt.setString(2, locacoes.getCnpjLocadora());
            stmt.setDate(3, locacoes.getDataInicio());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchLocacoes(Locacoes locacoes) throws SQLException {
        String sql = "SELECT * FROM Locacoes WHERE cpf_cliente = ? AND cnpj_locadora = ?";
        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, locacoes.getCpfCliente());
            stmt.setString(2, locacoes.getCnpjLocadora());
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            locacoes.setCpfCliente(rs.getString("cpf_cliente"));
            locacoes.setCnpjLocadora(rs.getString("cnpj_locadora"));
            locacoes.setDataInicio(rs.getDate("data_horario"));

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Locacoes> listaLocacoes() throws SQLException {
        List<Locacoes> locacoes = new ArrayList<>();
        String sql = "SELECT * FROM Locacoes";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Locacoes locacao = new Locacoes();
                locacao.setCpfCliente(rs.getString("cpf_cliente"));
                locacao.setCnpjLocadora(rs.getString("cnpj_locadora"));
                locacao.setDataInicio(rs.getDate("data_horario"));

                locacoes.add(locacao);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locacoes;
    }

    public void updateLocacoes (Locacoes Locacoes) throws SQLException {
        String sql = "UPDATE Locacoes SET data_horario = ? WHERE cpf_cliente = ? AND cnpj_locadora = ?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setDate(1, Locacoes.getDataInicio());
            stmt.setString(2, Locacoes.getCpfCliente());
            stmt.setString(3, Locacoes.getCnpjLocadora());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLocacoes (Locacoes Locacoes) throws SQLException {
        String sql = "DELETE FROM Locacoes WHERE cpf_cliente = ? AND cnpj_locadora = ?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, Locacoes.getCpfCliente());
            stmt.setString(1, Locacoes.getCnpjLocadora());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}