package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.Locacoes;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocacoesDAO extends Conexao {

    public void cadastrarLocacoes(Locacoes locacoes) throws SQLException {
        String sql = "INSERT INTO Locacoes (cpf_cliente, cnpj_locadora, data_horario) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setString(1, locacoes.getCpfCliente());
            stmt.setString(2, locacoes.getCnpjLocadora());
            stmt.setTimestamp(3, locacoes.getDataInicio());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchLocacoes(Locacoes locacoes) throws SQLException {
        String sql = "SELECT * FROM Locacoes WHERE cpf_cliente = ? AND cnpj_locadora = ?";
        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setString(1, locacoes.getCpfCliente());
            stmt.setString(2, locacoes.getCnpjLocadora());
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            locacoes.setCpfCliente(rs.getString("cpf_cliente"));
            locacoes.setCnpjLocadora(rs.getString("cnpj_locadora"));
            locacoes.setDataInicio(rs.getTimestamp("data_horario"));

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Locacoes> listaLocacoes() throws SQLException {
        List<Locacoes> locacoes = new ArrayList<>();
        String sql = "SELECT * FROM Locacoes";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Locacoes locacao = new Locacoes();
                locacao.setCpfCliente(rs.getString("cpf_cliente"));
                locacao.setCnpjLocadora(rs.getString("cnpj_locadora"));
                locacao.setDataInicio(rs.getTimestamp("data_horario"));

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

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setTimestamp(1, Locacoes.getDataInicio());
            stmt.setString(2, Locacoes.getCpfCliente());
            stmt.setString(3, Locacoes.getCnpjLocadora());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLocacoes(String cpfCliente, String cnpjLocadora) throws SQLException {
        String sql = "DELETE FROM Locacoes WHERE cpf_cliente = ? AND cnpj_locadora = ?";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cpfCliente);
            stmt.setString(2, cnpjLocadora);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Locacoes getLocacaoById(int id) throws SQLException {
        Locacoes locacao = null;
        String sql = "SELECT * FROM Locacoes WHERE id = ?";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                locacao = new Locacoes();
                locacao.setId(rs.getInt("id"));
                locacao.setCpfCliente(rs.getString("cpf_cliente"));  // Ajuste conforme a estrutura real
                locacao.setCnpjLocadora(rs.getString("cnpj_locadora")); // Ajuste conforme a estrutura real
                locacao.setDataInicio(rs.getTimestamp("data_horario"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propague a exceção para tratamento posterior
        }

        return locacao;
    }

    public void editLocacoes(int id, String cpfCliente, String cnpjLocadora, Timestamp dataHorario) throws SQLException {
        String sql = "UPDATE Locacoes SET cpf_cliente = ?, cnpj_locadora = ?, data_horario = ? WHERE id = ?";

        try (PreparedStatement stmt = this.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cpfCliente);
            stmt.setString(2, cnpjLocadora);
            stmt.setTimestamp(3, dataHorario);
            stmt.setInt(4, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}