package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.function.Predicate;
import model.Morador;
import model.MoradorDAO;

public class MoradorController {

    private static int ultimoCodigo = 0;
    private MoradorDAO moradorDAO;

    public MoradorController() throws SQLException {
        try {
            this.moradorDAO = new MoradorDAO();
            this.ultimoCodigo = moradorDAO.obterUltimoCodigo();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao inserir morador ", ex);
        }
    }

    public int gerarNovoCodigo() {
        ultimoCodigo++; // Incrementa o código
        return ultimoCodigo;
    }

    // Método para cadastrar o morador (usando lambda para validação)
    public boolean cadastrarMorador(Morador morador) throws SQLException {
        // Expressão lambda para validar se o morador tem todos os campos obrigatórios
        Predicate<Morador> validaProprietario = p
                -> p.getDataNascimento() != null & !p.getDataNascimento().isEmpty()
                && p.getNome() != null & !p.getNome().isEmpty()
                && p.getCpf() != null & !p.getCpf().isEmpty()
                && p.getRg() != null & !p.getRg().isEmpty();

        if (!validaProprietario.test(morador)) {
            return false;
        }

        // Gera e atribui código
        morador.setCodigo(gerarNovoCodigo());

        try {
            moradorDAO.cadastrarMorador(morador);
            return true;
        } catch (SQLException | IOException ex) {
            throw new SQLException("Erro cadastrar morador", ex);
        }
    }

    // Método para editar morador (usando lambda para validação)
    public boolean editarMorador(Morador morador) throws SQLException, ParseException {
        // Expressão lambda para validar se o morador tem todos os campos obrigatórios
        Predicate<Morador> validaMorador = m
                -> m.getNome() != null & !m.getNome().isEmpty()
                && m.getCpf() != null & !m.getCpf().isEmpty()
                && m.getRg() != null & !m.getRg().isEmpty();

        if (!validaMorador.test(morador)) {
            return false;
        }

        try {
            moradorDAO.editarMorador(morador);
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao editar morador", ex);
        }
    }

    public Morador pesquisarMorador(int codigoPesquisa) {
        try {
            return moradorDAO.pesquisarMorador(codigoPesquisa);
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar: " + ex.getMessage());
        }
        return null;
    }

    public boolean deletarMorador(int codigo) throws SQLException {
        try {
            // Verifica se o morador existe antes de deletar
            if (!moradorDAO.existeMorador(codigo)) {
                throw new SQLException("Morador não encontrado com o código: " + codigo);
            }

            return moradorDAO.deletarMorador(codigo);

        } catch (SQLException ex) {
            throw new SQLException("Não foi possível deletar o morador", ex);
        }
    }
}
