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

public class MoradorDAO {

    private Connection con;

    public MoradorDAO() throws SQLException {
        this.con = ConexaoBancoSingleton.getInstance().getConnection();
    }

    public int obterUltimoCodigo() throws SQLException {
        String sql = "SELECT MAX(codigo) FROM Morador";

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public boolean existeMorador(int codigo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Morador WHERE codigo = ?";

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

    public int cadastrarMorador(Morador morador) throws SQLException, IOException {
        String sql = "INSERT INTO Morador (codigo, nome, dataNascimento, rg, cpf, codEndereco) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setInt(1, morador.getCodigo());
            comando.setString(2, morador.getNome());
            java.sql.Date dataSql = java.sql.Date.valueOf(morador.getDataNascimento());
            comando.setDate(3, dataSql);
            comando.setString(4, morador.getRg());
            comando.setString(5, morador.getCpf());
            comando.setInt(6, morador.getCodigoEndereco());
            comando.executeUpdate();
            return morador.getCodigo();

        } catch (SQLException ex) {
            throw new SQLException("Erro ao inserir morador ", ex);
        }
    }

    public boolean editarMorador(Morador morador) throws SQLException, ParseException {
        String sql = "UPDATE Morador SET nome = ?, dataNascimento = ?, rg = ?, cpf = ?, codEndereco = ? WHERE codigo = ? ";

        try (PreparedStatement comando = con.prepareStatement(sql)) {

            // editar a data antes de enviar para o backend
            String dataTexto = morador.getDataNascimento(); 
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = sdf.parse(dataTexto);
            java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());

            
            comando.setString(1, morador.getNome());
            comando.setDate(2, dataSQL); 
            comando.setString(3, morador.getRg());
            comando.setString(4, morador.getCpf());
            comando.setInt(5, morador.getCodigoEndereco());
            comando.setInt(6, morador.getCodigo());
            int alterou = comando.executeUpdate();

            return alterou > 0;

        } catch (SQLException | ParseException ex) {
            throw new SQLException("Erro ao editar morador ", ex);
        }

    }

    //Método para buscar morador por código
    public Morador pesquisarMorador(int codigoPesquisa) throws SQLException {

        String sql = "SELECT * FROM Morador WHERE codigo = ?";
        Morador morador = null;

        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setInt(1, codigoPesquisa);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                morador = new Morador();
                morador.setCodigo(resultado.getInt("codigo"));
                morador.setNome(resultado.getString("nome"));
                morador.setDataNascimento(resultado.getString("dataNascimento"));
                morador.setRg(resultado.getString("rg"));
                morador.setCpf(resultado.getString("cpf"));
                morador.setCodigoEndereco(resultado.getInt("codEndereco"));
            }

        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar morador", ex);
        }
        return morador;
    }

    public boolean deletarMorador(int codigo) throws SQLException {
        String sql = "DELETE FROM Morador WHERE codigo = ?";

        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setInt(1, codigo);

            int excluido = comando.executeUpdate();
            return excluido > 0;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao deletar morador", ex);
        }
    }

}
