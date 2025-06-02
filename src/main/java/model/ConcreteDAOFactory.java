package model;

import java.sql.SQLException;

public class ConcreteDAOFactory {

    public static MoradorDAO getMoradorDAO() throws SQLException {
        return new MoradorDAO();
    }

    public static ProprietarioDAO getProprietarioDAO() throws SQLException {
        return new ProprietarioDAO();
    }

    public static ResidenciaEnderecoDAO getResidenciaDAO() throws SQLException {
        return new ResidenciaEnderecoDAO();
    }

    public static CondominioMensalidadeDAO getMensalidadeDAO() throws SQLException {
        return new CondominioMensalidadeDAO();
    }

    public static ProprietarioEnderecoDAO getProprietarioEnderecoDAO() throws SQLException {
        return new ProprietarioEnderecoDAO();
    }
    
    public static MoradorEnderecoDAO getMoradorEnderecoDAO() throws SQLException{
        return new MoradorEnderecoDAO();
    }

}
