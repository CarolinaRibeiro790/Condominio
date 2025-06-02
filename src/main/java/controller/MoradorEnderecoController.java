package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.MoradorEndereco;
import model.MoradorEnderecoDAO;

/**
 *
 * @author carol
 */
public class MoradorEnderecoController {

    private MoradorEnderecoDAO moradorEnderecoDAO;

    public MoradorEnderecoController() throws SQLException {
        try {
            this.moradorEnderecoDAO = new MoradorEnderecoDAO();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao editar ", ex);
        }
    }

    public List<MoradorEndereco> listarTodosMoradores() throws SQLException {
        if (moradorEnderecoDAO == null) {
            throw new SQLException("O DAO não foi inicializado corretamente");
        }
        return moradorEnderecoDAO.listarMoradores();
    }

    // Método para formatar os dados para exibição em tabela
    public Object[][] getMoradoresParaTabela() throws SQLException, ParseException {
        try {
            List<MoradorEndereco> moradores = listarTodosMoradores();
            if (moradores == null || moradores.isEmpty()) {
                return new Object[0][0];
            }

            Object[][] dados = new Object[moradores.size()][13];
            SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoBR = new SimpleDateFormat("dd/MM/yyyy");

            for (int i = 0; i < moradores.size(); i++) {
                MoradorEndereco p = moradores.get(i);

                //Convertendo as datas
                Date data = formatoSQL.parse(p.getDataNascimento());
                String dataNascimento = formatoBR.format(data);

                if (p.getPagamento() != null && !p.getPagamento().isEmpty()) {
                    Date data1 = formatoSQL.parse(p.getPagamento());
                    String dataPagamento = formatoBR.format(data1);
                    dados[i][9] = dataPagamento;
                }

                if (p.getVencimento() != null && !p.getVencimento().isEmpty()) {
                    Date data2 = formatoSQL.parse(p.getVencimento());
                    String dataVencimento = formatoBR.format(data2);
                    dados[i][10] = dataVencimento;
                }

                dados[i][0] = p.getCodigo();
                dados[i][1] = p.getNome();
                dados[i][2] = dataNascimento;
                dados[i][3] = p.getRg();
                dados[i][4] = p.getCpf();
                dados[i][5] = p.getRua();
                dados[i][6] = p.getNumero();
                dados[i][7] = p.getBairro();
                dados[i][8] = p.getCep();
                dados[i][11] = p.getStatus();
                dados[i][12] = p.getMes();
            }

            return dados;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao exibir ", ex);

        }
    }

    // Método para obter os nomes das colunas 
    public String[] getNomesColunas() {
        return new String[]{
            "Código", "Nome", "Data Nasc.", "RG", "CPF",
            "Rua", "Número", "Bairro", "CEP",
            "Pagamento", "Vencimento", "Status", "Mês"
        };
    }
}
