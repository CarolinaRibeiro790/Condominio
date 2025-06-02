package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class ResidenciaEnderecoDAO {

    private Connection con;

    public ResidenciaEnderecoDAO() throws SQLException {
        this.con = ConexaoBancoSingleton.getInstance().getConnection();
    }

    public int obterUltimoCodigo() throws SQLException {
        String sql = "SELECT MAX(codEndereco) FROM Endereco";

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public boolean existeResidencia(int codigo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Endereco WHERE codEndereco = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public int cadastrarEndereco(ResidenciaEndereco endereco) throws SQLException, IOException, ParseException {
        String sql = "INSERT INTO Endereco (codEndereco, rua, numero, bairro, cep) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setInt(1, endereco.getCodigoResidencia());
            comando.setString(2, endereco.getRua());
            comando.setInt(3, endereco.getNumero());
            comando.setString(4, endereco.getBairro());
            comando.setString(5, endereco.getCep());

            comando.executeUpdate();
            return endereco.getCodigoResidencia();

        } catch (SQLException ex) {
            throw new SQLException("Erro ao inserir endereço ", ex);
        }
    }

    public boolean editarResidencia(ResidenciaEndereco endereco) throws SQLException {
        String sql = "UPDATE Endereco SET rua = ?, numero = ?, bairro = ?, cep = ? WHERE codEndereco = ?";

        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setString(1, endereco.getRua());
            comando.setInt(2, endereco.getNumero());
            comando.setString(3, endereco.getBairro());
            comando.setString(4, endereco.getCep());
            comando.setInt(5, endereco.getCodigoResidencia());
            int alterou = comando.executeUpdate();

            return alterou > 0;

        } catch (SQLException ex) {
            throw new SQLException("Erro ao editar endereco ", ex);
        }
    }

    //Método para buscar endereço por código
    public ResidenciaEndereco pesquisarResidencia(int codigoPesquisa) throws SQLException {

        String sql = "SELECT * FROM Endereco WHERE codEndereco = ?";
        ResidenciaEndereco endereco = null;

        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setInt(1, codigoPesquisa);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                endereco = new ResidenciaEndereco();
                endereco.setCodigoResidencia(resultado.getInt("codEndereco"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setNumero(resultado.getInt("numero"));
            }

        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar endereco", ex);
        }
        return endereco;
    }

}
