package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/empresa";
    private static final String USER = "devuser";
    private static final String PASSWORD = "123456";

    public static Connection conectar() throws ClassNotFoundException {
        Connection con = null;
        try {
            // Carregar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer a conexão com o banco de dados
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            if (con != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.out.println("Falha ao estabelecer conexão.");
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao conectar com o banco: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Erro ao conectar com o banco de dados", ex); // Propaga a exceção para tratamento externo
        }
        return con;
    }
}
