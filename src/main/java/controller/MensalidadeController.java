package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.function.Predicate;
import model.CondominioMensalidade;
import model.CondominioMensalidadeDAO;

public class MensalidadeController {

    private static int ultimoCodigo = 0;

    public CondominioMensalidadeDAO mensalidadeDAO;

    public MensalidadeController() throws SQLException {
        try {
            this.mensalidadeDAO = new CondominioMensalidadeDAO();
            this.ultimoCodigo = mensalidadeDAO.obterUltimoCodigo();
        } catch (SQLException ex) {
            throw new SQLException("Erro ", ex);
        }
    }

    public int gerarNovoCodigo() {
        ultimoCodigo++; // Incrementa o código
        return ultimoCodigo;
    }

    public boolean cadastrarMensalidadeCondominio(CondominioMensalidade mensalidade) throws SQLException, ParseException {
        // Expressão lambda para validar todos os campos obrigatórios
        Predicate<CondominioMensalidade> validaMensalidade = m
                -> m.getDataVencimento() != null & !m.getDataVencimento().isEmpty()
                && m.getDataPagamento() != null & !m.getDataPagamento().isEmpty()
                && m.getMes() != null & !m.getMes().isEmpty()
                && m.getStatusCondominio() != null & !m.getStatusCondominio().isEmpty();

        if (!validaMensalidade.test(mensalidade)) {
            return false;
        }

        // Gera e atribui código
        mensalidade.setCodigoMensalidade(gerarNovoCodigo());

        try {
            mensalidadeDAO.cadastrarMensalidadeCondominio(mensalidade);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ", ex);
        }

    }

    // Método para editar morador (usando lambda para validação)
    public boolean editarMensalidade(CondominioMensalidade mensalidade) throws SQLException, ParseException {
        // Expressão lambda para validar se o morador tem todos os campos obrigatórios
       /* Predicate<CondominioMensalidade> validaResidencia = r
                -> r.getMes() != null && r.getMes().isEmpty()
                && r.getDataVencimento() != null && !r.getDataVencimento().isEmpty()
                && r.getDataPagamento() != null && !r.getDataPagamento().isEmpty()
                && r.getMes() != null && !r.getMes().isEmpty();

        if (!validaResidencia.test(mensalidade)) {
            return false;
        }
        */
        try {
            mensalidadeDAO.editarMensalidade(mensalidade);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao editar mensalidade", ex);
        }
    }

    public CondominioMensalidade pesquisarMensalidade(int codigoPesquisa) {
        try {
            return mensalidadeDAO.pesquisarMensalidade(codigoPesquisa);
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar: " + ex.getMessage());
        }
        return null;
    }
}
