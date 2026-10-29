package org.astro.astro_app.Conexão;

//Biblioteca:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

// Classe para realizar o JDBC
public class Conexao {

    //Constante responsável por gerenciar as conexões do banco
    private static final HikariDataSource postgresqlPool = criarPool();

    private static HikariDataSource criarPool() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(dotenv.get("DB_URL"));
        config.setUsername(dotenv.get("DB_USER"));
        config.setPassword(dotenv.get("DB_PASSWORD"));
        config.setConnectionTimeout(15000L);
        config.setIdleTimeout(300000L);
        config.setMaxLifetime(600000L);
        config.setMinimumIdle(3);
        config.setMaximumPoolSize(4);
        config.setPoolName("elos-pool");

        // Aumenta a velocidade do pool
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }

    public static Connection conectar() throws SQLException {
        return postgresqlPool.getConnection();
    }

    public static void desconectar(Connection connection) {
        try{
            connection.close();
        } catch (SQLException e) {

        }
    }

}