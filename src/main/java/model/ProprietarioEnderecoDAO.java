package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carol
 */
public class ProprietarioEnderecoDAO {

    private Connection con;

    public ProprietarioEnderecoDAO() throws SQLException {
        this.con = ConexaoBancoSingleton.getInstance().getConnection();
    }

    public List<ProprietarioEndereco> listarProprietarios() throws SQLException {
        String sql = "SELECT p.codigoProprietario, p.nome, p.dataNascimento, p.rg, p.cpf, p.codEndereco,\n"
                    + "e.rua, e.numero, e.bairro, e.cep, \n"
                    + "pag.dataPagamento, pag.mes, pag.dataVencimento, pag.statusMensalidade\n"
                    + "FROM Proprietario p\n"
                    + "LEFT JOIN Endereco e ON p.codEndereco = e.codEndereco\n"
                    + "LEFT JOIN CondominioMensalidade pag ON p.codEndereco = pag.codEndereco";

        List<ProprietarioEndereco> proprietarios = new ArrayList<>();
        try (PreparedStatement comando = con.prepareStatement(sql)) {
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                ProprietarioEndereco proprietario = new ProprietarioEndereco();
                proprietario.setCodigo(resultado.getInt("codigoProprietario"));
                proprietario.setNome(resultado.getString("nome"));
                proprietario.setDataNascimento(resultado.getString("dataNascimento"));
                proprietario.setRg(resultado.getString("rg"));
                proprietario.setCpf(resultado.getString("cpf"));
                proprietario.setRua(resultado.getString("rua"));
                proprietario.setNumero(resultado.getInt("numero"));
                proprietario.setBairro(resultado.getString("bairro"));
                proprietario.setCep(resultado.getString("cep"));
                proprietario.setPagamento(resultado.getString("dataPagamento"));
                proprietario.setVencimento(resultado.getString("dataVencimento"));
                proprietario.setStatus(resultado.getString("statusMensalidade"));
                proprietario.setMes(resultado.getString("mes"));
                proprietarios.add(proprietario);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar dados", ex);
        }
        return proprietarios;
    }
}
