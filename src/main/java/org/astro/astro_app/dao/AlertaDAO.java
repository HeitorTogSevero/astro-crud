package org.astro.astro_app.dao;

// Biblioteca:
import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Alerta;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Alerta - CRUD:
public class AlertaDAO {

    // Método Create | Insert - CRUD:
    public boolean inserir(Alerta a) {

        // Criando a Conexao com o banco de dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO alerta (descricao, codigo, dt_limite, id_empresa) VALUES (?, ?, ?, ?)");

            pstmt.setString(1, a.getDescricao());
            pstmt.setInt(2, a.getCodigo());
            pstmt.setDate(3, a.getDtLimite());
            pstmt.setInt(4, a.getIdEmpresa());

            // Executa o comando INSERIR e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            return false;

        // Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Read | Select - CRUD
    public ArrayList<Alerta> buscar(String sql) {

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        ArrayList<Alerta> vet = new ArrayList<>();

        // Tentando a conexão
        try {
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                vet.add(new Alerta(rs.getInt("id_alerta"), rs.getInt("codigo"), rs.getInt("id_empresa"), rs.getString("descricao"), rs.getDate("dt_limite")));
            }

            stmt.close();

        // Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método Read | Select - CRUD, mas baseado no Id do alerta
    public Alerta buscarPorIdAlerta(int idAlerta) {

        // Criando conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try {
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM alerta WHERE id_alerta = ?");

            pstmt.setInt(1, idAlerta);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new Alerta(rs.getInt("id_alerta"), rs.getInt("codigo"), rs.getInt("id_empresa"), rs.getString("descricao"), rs.getDate("dt_limite"));
            }
            return null;

        // Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        } finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Read | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<Alerta> buscarPorIdEmpresa(int idEmpresa) {

        // Criando conexão com o Banco de Dados
        Connection conn = null;

        ArrayList<Alerta> vet = new ArrayList<>();

        // Tentando a conexão
        try {
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM alerta WHERE id_empresa = ? ORDER BY id_alerta");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Alerta(rs.getInt("id_alerta"), rs.getInt("codigo"), rs.getInt("id_empresa"), rs.getString("descricao"), rs.getDate("dt_limite")));
            }

        // Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    //Método Update - CRUD
    public int alterarAlerta(Alerta a){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try {
            conn = Conexao.conectar(); // Desconectando do Banco

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE alerta set codigo = ?, id_empresa = ?, descricao = ?, dt_limite = ? WHERE id_alerta = ?");

            pstmt.setInt(1, a.getCodigo());
            pstmt.setInt(2, a.getIdEmpresa());
            pstmt.setString(3, a.getDescricao());
            pstmt.setDate(4, a.getDtLimite());
            pstmt.setInt(5, a.getIdAlerta());


            // Executa o comando ALTERAR e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return 1;
            }

            return 0;

        // Mensagem de erro da conexão
        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }

    }

    // Método Delete | Remove - CRUD
    public int remover(int idAlerta) {

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try {
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM alerta WHERE id_alerta = ?");

            pstmt.setInt(1, idAlerta);

            // Executa o comando DELETE e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() == 0) {
                return 0;
            }
            return 1;

        // Mensagem de erro da conexão
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return -1;
        } finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}