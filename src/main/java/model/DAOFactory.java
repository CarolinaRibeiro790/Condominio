package model;

import java.sql.SQLException;

/* Cria objetos responsáveis ​​por persistir no banco de dados.*/
public interface DAOFactory {

    MoradorDAO getMoradorDAO() throws SQLException;

    ProprietarioDAO getProprietarioDAO() throws SQLException;

    ResidenciaEnderecoDAO getResidenciaDAO() throws SQLException;
    
    CondominioMensalidadeDAO getMensalidadeDAO() throws SQLException;
    
    ProprietarioEnderecoDAO getProprietarioEnderecoDAO() throws SQLException;
    
    MoradorEnderecoDAO getMoradorEnderecoDAO() throws SQLException;
}
