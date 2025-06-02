package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CondominioMensalidadeDAO {

    private Connection con;

    public CondominioMensalidadeDAO() throws SQLException {
        this.con = ConexaoBancoSingleton.getInstance().getConnection();
    }

    public int obterUltimoCodigo() throws SQLException {
        String sql = "SELECT MAX(codigoMensalidade) FROM CondominioMensalidade";

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public boolean cadastrarMensalidadeCondominio(CondominioMensalidade mensalidade) throws SQLException {
        String sql = "INSERT INTO CondominioMensalidade (codigoMensalidade, mes, dataVencimento, dataPagamento, statusMensalidade, codigoProprietario, codEndereco) VALUES (?, ?, ?, ?, ?, ?, ?) ";

        try (PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setInt(1, mensalidade.getCodigoMensalidade());
            comando.setString(2, mensalidade.getMes());

            // Converte String (yyyy-MM-dd) para java.sql.Date
            java.sql.Date dataSql = java.sql.Date.valueOf(mensalidade.getDataVencimento());
            comando.setDate(3, dataSql);
            java.sql.Date dataSql1 = java.sql.Date.valueOf(mensalidade.getDataPagamento());
            comando.setDate(4, dataSql1);
            comando.setString(5, mensalidade.getStatusCondominio());
            comando.setInt(6, mensalidade.getCodigoProprietario());
            comando.setInt(7, mensalidade.getCodigoResidencia());
            int cadastrou = comando.executeUpdate();
            return cadastrou > 0;

        } catch (SQLException ex) {
            throw new SQLException("Erro ao inserir mensalidade ", ex);
        }
    }

    public boolean editarMensalidade(CondominioMensalidade mensalidade) throws SQLException, ParseException {
        String sql = "UPDATE CondominioMensalidade SET mes = ?, dataVencimento = ?, dataPagamento = ?, "
                + "statusMensalidade = ?, codigoProprietario = ?, codEndereco = ? WHERE codigoMensalidade = ?";

        try (PreparedStatement comando = con.prepareStatement(sql)) {

            // editar a data antes de enviar para o backend
            String dataTexto = mensalidade.getDataVencimento();
            String dataTexto1 = mensalidade.getDataPagamento();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = sdf.parse(dataTexto);
            dataUtil = sdf.parse(dataTexto1);
            java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());

            comando.setString(1, mensalidade.getMes());
            comando.setDate(2, dataSQL);
            comando.setDate(3, dataSQL);
            comando.setString(4, mensalidade.getStatusCondominio());
            comando.setInt(5, mensalidade.getCodigoProprietario());
            comando.setInt(6, mensalidade.getCodigoResidencia());
            comando.setInt(7, mensalidade.getCodigoMensalidade());

            int alterou = comando.executeUpdate();
            return alterou > 0;

        } catch (SQLException | ParseException ex) {
            throw new SQLException("Erro ao editar mensalidade", ex);
        }
    }

    public CondominioMensalidade pesquisarMensalidade(int codigoPesquisa) throws SQLException {

        String sql = "SELECT * FROM CondominioMensalidade WHERE codigoMensalidade = ?";
        CondominioMensalidade mensalidade = null;

        try (PreparedStatement comando = con.prepareStatement(sql)) {
            comando.setInt(1, codigoPesquisa);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                mensalidade = new CondominioMensalidade();
                mensalidade.setCodigoResidencia(resultado.getInt("codEndereco"));
                mensalidade.setMes(resultado.getString("mes"));
                mensalidade.setDataPagamento(resultado.getString("dataPagamento"));
                mensalidade.setDataVencimento(resultado.getString("dataVencimento"));
                mensalidade.setCodigoProprietario(resultado.getInt("codigoProprietario"));
                mensalidade.setCodigoMensalidade(resultado.getInt("codigoMensalidade"));
                mensalidade.setStatusCondominio(resultado.getString("statusMensalidade"));
            }

        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar mensalidade", ex);
        }
        return mensalidade;
    }
}
