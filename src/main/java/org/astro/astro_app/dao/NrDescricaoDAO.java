package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.NrDescricao;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO NrDescricao - CRUD:
public class NrDescricaoDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(NrDescricao nrDescricao){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO nr_descricao (titulo, descricao, tempo_reciclagem) VALUES (?, ?, ?)");

            pstmt.setString(1, nrDescricao.getTitulo());
            pstmt.setString(2, nrDescricao.getDescricao());
            pstmt.setString(3, nrDescricao.getTempoReciclagem());

            if (pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Read | Select - CRUD
    public ArrayList<NrDescricao> buscar(){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<NrDescricao> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM nr_descricao ORDER BY id_nrdescricao");

            while(rs.next()){
                vet.add(new NrDescricao(rs.getInt("id_nrdescricao"), rs.getString("descricao"), rs.getString("tempo_reciclagem"), rs.getString("titulo")));
            }

            stmt.close();

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id
    public NrDescricao buscarPorIdNrDescricao(int idNrDescricao){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_descricao WHERE id_nrdescricao = ?");

            pstmt.setInt(1, idNrDescricao);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new NrDescricao(rs.getInt("id_nrdescricao"), rs.getString("descricao"), rs.getString("tempo_reciclagem"), rs.getString("titulo"));
            }
            return null;

        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Update - CRUD
    public int alterarNrDescricao(NrDescricao nrDescricao){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE nr_descricao set titulo = ?, descricao = ?, tempo_reciclagem = ? WHERE id_nrdescricao = ?");

            pstmt.setString(1, nrDescricao.getTitulo());
            pstmt.setString(2, nrDescricao.getDescricao());
            pstmt.setString(3, nrDescricao.getTempoReciclagem());
            pstmt.setInt(4, nrDescricao.getIdNrDescricao());

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Delete | Remove - CRUD
    public int remover(int idNrDescricao){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM nr_descricao WHERE id_nrdescricao = ?");

            pstmt.setInt(1, idNrDescricao);

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }
}