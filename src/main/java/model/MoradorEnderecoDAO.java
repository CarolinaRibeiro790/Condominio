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
public class MoradorEnderecoDAO {

    private Connection con;

    public MoradorEnderecoDAO() throws SQLException {
        this.con = ConexaoBancoSingleton.getInstance().getConnection();
    }

    public List<MoradorEndereco> listarMoradores() throws SQLException {
        String sql = "SELECT p.codigo, p.nome, p.dataNascimento, p.rg, p.cpf, \n"
                + "e.rua, e.numero, e.bairro, e.cep,\n"
                + "pag.dataPagamento, pag.mes, pag.dataVencimento, pag.statusMensalidade\n"
                + "FROM Morador AS p\n"
                + "LEFT JOIN Endereco e ON p.codEndereco = e.codEndereco\n"
                + "LEFT JOIN CondominioMensalidade pag ON p.codEndereco = pag.codEndereco;";

        List<MoradorEndereco> morador = new ArrayList<>();
        try (PreparedStatement comando = con.prepareStatement(sql)) {
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                MoradorEndereco moradores = new MoradorEndereco();
                moradores.setCodigo(resultado.getInt("codigo"));
                moradores.setNome(resultado.getString("nome"));
                moradores.setDataNascimento(resultado.getString("dataNascimento"));
                moradores.setRg(resultado.getString("rg"));
                moradores.setCpf(resultado.getString("cpf"));
                moradores.setRua(resultado.getString("rua"));
                moradores.setNumero(resultado.getInt("numero"));
                moradores.setBairro(resultado.getString("bairro"));
                moradores.setCep(resultado.getString("cep"));
                moradores.setPagamento(resultado.getString("dataPagamento"));
                moradores.setVencimento(resultado.getString("dataVencimento"));
                moradores.setStatus(resultado.getString("statusMensalidade"));
                moradores.setMes(resultado.getString("mes"));
                morador.add(moradores);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar os dados ", ex);
        }
        return morador;
    }
}
