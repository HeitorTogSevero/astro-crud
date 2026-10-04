package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Estrutura;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Estrutura - CRUD:
public class EstruturaDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Estrutura est){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO estrutura (id_empresa, risco, descricao) VALUES (?, ?, ?)");

            pstmt.setInt(1, est.getIdEmpresa());
            pstmt.setString(2, est.getRisco());
            pstmt.setString(3, est.getDescricao());

            if(pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo READ | Select - CRUD
    public ArrayList<Estrutura> buscar(){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Estrutura> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM estrutura ORDER BY id_estrutura");

            while(rs.next()){
                vet.add(new Estrutura(rs.getInt("id_estrutura"), rs.getInt("id_empresa"), rs.getString("risco"), rs.getString("descricao")));
            }

            stmt.close();

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally{
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo READ | Select - CRUD, mas baseado no Id da estrutura
    public Estrutura buscarPorIdEstrutura(int idEstrutura){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM estrutura WHERE id_estrutura = ?");

            pstmt.setInt(1, idEstrutura);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Estrutura(rs.getInt("id_estrutura"), rs.getInt("id_empresa"), rs.getString("risco"), rs.getString("descricao"));
            }
            return null;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return null;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo READ | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<Estrutura> buscarPorIdEmpresa(int idEmpresa){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Estrutura> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM estrutura WHERE id_empresa = ? ORDER BY id_estrutura");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Estrutura(rs.getInt("id_estrutura"), rs.getInt("id_empresa"), rs.getString("risco"), rs.getString("descricao")));
            }

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Update - CRUD
    public int alterarEstrutura(Estrutura est) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE estrutura SET id_empresa = ?, risco = ?, descricao = ? WHERE id_estrutura = ?");

            pstmt.setInt(1, est.getIdEmpresa());
            pstmt.setString(2, est.getRisco());
            pstmt.setString(3, est.getDescricao());
            pstmt.setInt(4, est.getIdEstrutura());

            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo DELETE - CRUD
    public int remover(int idEstrutura){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM estrutura WHERE id_estrutura = ?");

            pstmt.setInt(1, idEstrutura);

            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }
}