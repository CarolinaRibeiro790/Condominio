package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProprietarioDAO {

    private Connection con;

    public ProprietarioDAO() throws SQLException {
        this.con = ConexaoBancoSingleton.getInstance().getConnection();
    }

    public int obterUltimoCodigo() throws SQLException {
        String sql = "SELECT MAX(codigoProprietario) FROM Proprietario";

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public int cadastrarProprietario(Proprietario proprietario) throws SQLException, IOException {
        String sql = "INSERT INTO Proprietario (codigoProprietario, nome, dataNascimento, rg, cpf, codEndereco) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setInt(1, proprietario.getCodigo());
            comando.setString(2, proprietario.getNome());
            comando.setString(3, proprietario.getDataNascimento());
            comando.setString(4, proprietario.getRg());
            comando.setString(5, proprietario.getCpf());
            comando.setInt(6, proprietario.getCodigoEndereco());
            comando.executeUpdate();
            return proprietario.getCodigo();

        } catch (SQLException ex) {
            throw new SQLException("Erro ao inserir proprietário ", ex);
        }
    }

    public boolean existeProprietario(int codigoProprietario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM proprietario WHERE codigoProprietario = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, codigoProprietario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean editarProprietario(Proprietario proprietario) throws SQLException, ParseException {
        String sql = "UPDATE Proprietario SET nome = ?, dataNascimento = ?, rg = ?, cpf = ?, codEndereco = ? WHERE codigoProprietario = ? ";

        try (PreparedStatement comando = con.prepareStatement(sql)) {

            // editar a data antes de enviar para o backend
            String dataTexto = proprietario.getDataNascimento(); // Formato DD/MM/YYYY
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = sdf.parse(dataTexto);
            java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());

            comando.setString(1, proprietario.getNome());
            comando.setDate(2, dataSQL);
            comando.setString(3, proprietario.getRg());
            comando.setString(4, proprietario.getCpf());
            comando.setInt(5, proprietario.getCodigoEndereco());
            comando.setInt(6, proprietario.getCodigo());
            int alterou = comando.executeUpdate();

            return alterou > 0;

        } catch (SQLException ex) {
            throw new SQLException("Erro ao inserir proprietário ", ex);
        }
    }

    //Método para buscar proprietario por código
    public Proprietario pesquisarProprietario(int codigoPesquisa) throws SQLException {

        String sql = "SELECT * FROM Proprietario WHERE codigoProprietario = ? ";

        Proprietario proprietario = null;

        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setInt(1, codigoPesquisa);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                proprietario = new Proprietario();
                proprietario.setCodigo(resultado.getInt("codigoProprietario"));
                proprietario.setNome(resultado.getString("nome"));
                proprietario.setDataNascimento(resultado.getString("dataNascimento"));
                proprietario.setRg(resultado.getString("rg"));
                proprietario.setCpf(resultado.getString("cpf"));
                proprietario.setCodigoEndereco(resultado.getInt("codEndereco"));
            }

        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar proprietario", ex);
        }
        return proprietario;
    }

    public boolean deletarProprietario(int codigoProprietario) throws SQLException {
        String sql = "DELETE FROM Proprietario WHERE codigoProprietario = ?";

        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setInt(1, codigoProprietario);

            int excluido = comando.executeUpdate();
            return excluido > 0;

        } catch (SQLException ex) {
            throw new SQLException("Erro ao deletar Proprietario", ex);
        }
    }
}
