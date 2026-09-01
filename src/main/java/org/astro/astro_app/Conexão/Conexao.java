package org.astro.astro_app.Conexão;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

// Classe para realizar o JDBC
public class Conexao{

    Dotenv dotenv = Dotenv.load();

    //    Esse metodo conecta o Projeto com o BD
    public Connection conectar() {
        Connection conn = null;


//         Realizando a Conexão:

        try{
            Class.forName("org.postgresql.Driver"); // Driver do JDBC

//          Recebendo os valores do arquivo .env:

            String usuario = dotenv.get("DB_USER");
            String senha = dotenv.get("DB_PASSWORD");
            String url = dotenv.get("DB_URL");

            conn = DriverManager.getConnection(url, usuario, senha);

            System.out.println("DEU BOMM");
        }

        // Mensagem de Erro na Conexão:
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return conn;

    }
    // Metodo para Desconetar o Banco:
    public void desconectar(Connection conn){
        try{
            if (conn != null && !conn.isClosed()) {
                conn.close(); // Fechando a Conexão

                System.out.println("Desconectou");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
