package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.function.Predicate;
import model.ResidenciaEndereco;
import model.ResidenciaEnderecoDAO;

public class ResidenciaController {

    private static int ultimoCodigo = 0;

    private ResidenciaEnderecoDAO enderecoDAO;

    public ResidenciaController() throws SQLException {
        try {
            this.enderecoDAO = new ResidenciaEnderecoDAO();
            this.ultimoCodigo = enderecoDAO.obterUltimoCodigo();
        } catch (SQLException ex) {
            throw new SQLException("Erro ", ex);
        }
    }

    public int gerarNovoCodigo() {
        ultimoCodigo++;
        return ultimoCodigo;
    }

    // Método para cadastrar endereço (usando lambda para validação)
    public boolean cadastrarResidencia(ResidenciaEndereco endereco) throws SQLException, ParseException {

        Predicate<ResidenciaEndereco> validaResidencia = e
                -> e.getBairro() != null && !e.getBairro().isEmpty()
                && e.getRua() != null && !e.getRua().isEmpty()
                && e.getCep() != null && !e.getCep().isEmpty();

        if (!validaResidencia.test(endereco)) {
            return false;
        }

        endereco.setCodigoResidencia(gerarNovoCodigo());

        try {
            enderecoDAO.cadastrarEndereco(endereco);
            return true;
        } catch (SQLException | IOException ex) {
            throw new SQLException("Erro ", ex);
        }
    }

    // Método para editar morador 
    public boolean editarResidencia(ResidenciaEndereco endereco) throws SQLException {
        // Expressão lambda para validar se o morador tem todos os campos obrigatórios
        /* Predicate<ResidenciaEndereco> validaEndereco = e
                -> e.getRua() != null & !e.getRua().isEmpty()
                && e.getBairro() != null & e.getBairro().isEmpty()
                && e.getCep() != null & e.getCep().isEmpty(); 
        if (!validaEndereco.test(endereco)) {
            return false;
        }
         */

        try {
            enderecoDAO.editarResidencia(endereco);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao editar residencia" + ex.getMessage());
        }

    }

    public ResidenciaEndereco pesquisarResidencia(int codigoPesquisa) {
        try {
            return enderecoDAO.pesquisarResidencia(codigoPesquisa);
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar: " + ex.getMessage());
        }
        return null;
    }
}
