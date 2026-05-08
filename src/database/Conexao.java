package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class Conexao {

    public static Connection getConnection() {
        Properties props = new Properties();
        try (InputStream is = Conexao.class.getResourceAsStream("/db.properties")) {
            if (is == null) {
                System.err.println("Arquivo db.properties não encontrado!");
                return null;
            }
            props.load(is);
            
            String url = props.getProperty("db.url"); 
            
            // Força o carregamento do driver que você adicionou na pasta lib
            Class.forName("org.postgresql.Driver");

            // O DriverManager usa o objeto 'props' para extrair 'user' e 'password' automaticamente
            return DriverManager.getConnection(url, props);

        } catch (Exception e) {
            System.err.println("ERRO de Conexão: " + e.getMessage());
            return null;
        }
    }
}