package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.function.Predicate;
import model.Proprietario;
import model.ProprietarioDAO;

public class ProprietarioController {

    private static int ultimoCodigo = 0;

    private ProprietarioDAO proprietarioDAO;

    public ProprietarioController() throws SQLException {

        try {
            this.proprietarioDAO = new ProprietarioDAO();
            this.ultimoCodigo = proprietarioDAO.obterUltimoCodigo();
        } catch (SQLException ex) {
            throw new SQLException("Erro", ex);
        }
    }

    public int gerarNovoCodigo() {
        ultimoCodigo++; // Incrementa o código
        return ultimoCodigo;
    }

    // Método para cadastrar proprietário (usando lambda para validação)
    public boolean cadastrarProprietario(Proprietario proprietario) throws SQLException {
        // Expressão lambda para validar se o proprietário tem todos os campos obrigatórios
        Predicate<Proprietario> validaProprietario = p
                -> p.getDataNascimento() != null & !p.getDataNascimento().isEmpty()
                && p.getNome() != null & !p.getNome().isEmpty()
                && p.getCpf() != null & !p.getCpf().isEmpty()
                && p.getRg() != null & !p.getRg().isEmpty();

        if (!validaProprietario.test(proprietario)) {
            return false;
        }

        // Gera e atribui código
        proprietario.setCodigo(gerarNovoCodigo());

        try {
            proprietarioDAO.cadastrarProprietario(proprietario);
            return true;
        } catch (SQLException | IOException ex) {
            throw new SQLException("Erro ao cadastrar proprietario ", ex);
        }

    }

    // Método para cadastrar proprietário (usando lambda para validação)
    public boolean editarProprietario(Proprietario proprietario) throws SQLException, ParseException {
        // Expressão lambda para validar se o proprietário tem todos os campos obrigatórios
        Predicate<Proprietario> validaProprietario = p
                -> p.getDataNascimento() != null & !p.getDataNascimento().isEmpty()
                && p.getNome() != null & !p.getNome().isEmpty()
                && p.getCpf() != null & !p.getCpf().isEmpty()
                && p.getRg() != null & !p.getRg().isEmpty();

        if (!validaProprietario.test(proprietario)) {
            return false;
        }

        try {
            proprietarioDAO.editarProprietario(proprietario);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao editar proprietario ", ex);
        }
    }

    public Proprietario pesquisarProprietario(int codigoPesquisa) {
        try {
            return proprietarioDAO.pesquisarProprietario(codigoPesquisa);
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar: " + ex.getMessage());
        }
        return null;
    }

    public boolean deletarProprietario(int codigoProprietario) throws SQLException {
        try {
            // Verifica se o morador existe antes de deletar
            if (!proprietarioDAO.existeProprietario(codigoProprietario)) {
                throw new SQLException("Morador não encontrado com o código: " + codigoProprietario);
            }

            return proprietarioDAO.deletarProprietario(codigoProprietario);

        } catch (SQLException ex) {
            throw new SQLException("Não foi possível deletar o morador", ex);
        }
    }
}
