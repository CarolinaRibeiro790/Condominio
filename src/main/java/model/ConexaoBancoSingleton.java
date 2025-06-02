package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoSingleton {

    private static ConexaoBancoSingleton banco;

    private Connection con;

    public ConexaoBancoSingleton() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/Condominio";
        String usr = "root";
        String senha = "1234";
        con = DriverManager.getConnection(url, usr, senha);
    }

    public static ConexaoBancoSingleton getInstance() throws SQLException {
        if (banco == null) {
            banco = new ConexaoBancoSingleton();
        }
        return banco;
    }

    public Connection getConnection() {
        return con;
    }
}
