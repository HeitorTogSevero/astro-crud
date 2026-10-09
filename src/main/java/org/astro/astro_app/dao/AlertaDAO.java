package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Alerta;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Alerta - CRUD:
public class AlertaDAO {

    // Metodo Create | Insert - CRUD:
    public boolean inserir(Alerta a) {

        // Criando a conexao com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO alerta (descricao, codigo, dt_limite, id_empresa) VALUES (?, ?, ?, ?)");

            pstmt.setString(1, a.getDescricao());
            pstmt.setInt(2, a.getCodigo());
            pstmt.setDate(3, a.getDtLimite());
            pstmt.setInt(4, a.getIdEmpresa());

            // Verificacao para saber se o INSERT funcionou:
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            return false;

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return false;
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }

    // Metodo Read | Select - CRUD
    public ArrayList<Alerta> buscar(String sql) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Alerta> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                vet.add(new Alerta(rs.getInt("id_alerta"), rs.getInt("codigo"), rs.getInt("id_empresa"), rs.getString("descricao"), rs.getDate("dt_limite")));
            }

            stmt.close();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do alerta
    public Alerta buscarPorIdAlerta(int idAlerta) {

        // Criando conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM alerta WHERE id_alerta = ?");

            pstmt.setInt(1, idAlerta);

            ResultSet rs = pstmt.executeQuery();

            // le os dados ANTES de fechar a conexao
            if (rs.next()) {
                return new Alerta(rs.getInt("id_alerta"), rs.getInt("codigo"), rs.getInt("id_empresa"), rs.getString("descricao"), rs.getDate("dt_limite"));
            }
            return null;

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }

    // Metodo Read | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<Alerta> buscarPorIdEmpresa(int idEmpresa) {

        // Criando conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Alerta> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM alerta WHERE id_empresa = ? ORDER BY id_alerta");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Alerta(rs.getInt("id_alerta"), rs.getInt("codigo"), rs.getInt("id_empresa"), rs.getString("descricao"), rs.getDate("dt_limite")));
            }

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    //Metodo Update - CRUD
    public int alterarAlerta(Alerta a){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE alerta set codigo = ?, id_empresa = ?, descricao = ?, dt_limite = ? WHERE id_alerta = ?");

            pstmt.setInt(1, a.getCodigo());
            pstmt.setInt(2, a.getIdEmpresa());
            pstmt.setString(3, a.getDescricao());
            pstmt.setDate(4, a.getDtLimite());
            pstmt.setInt(5, a.getIdAlerta());


            if (pstmt.executeUpdate() > 0){
                return 1;
            }

            return 0;

        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }

    }

    // Metodo Delete | Remove - CRUD
    public int remover(int idAlerta) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM alerta WHERE id_alerta = ?");

            pstmt.setInt(1, idAlerta);


            if (pstmt.executeUpdate() == 0) {
                return 0;
            }
            return 1;

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return -1;
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }
}