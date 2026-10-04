package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.NrEmpresa;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO NrEmpresa - CRUD:
public class NrEmpresaDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(NrEmpresa nrEmpresa){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO nr_empresa (numero, status, titulo, dt_realizacao, id_nrdescricao) VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, nrEmpresa.getNumero());
            pstmt.setString(2, nrEmpresa.getStatus());
            pstmt.setString(3, nrEmpresa.getTitulo());
            pstmt.setDate(4, nrEmpresa.getDtRealização());
            pstmt.setInt(5, nrEmpresa.getIdNrDescricao());

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
    public ArrayList<NrEmpresa> buscar(){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<NrEmpresa> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM nr_empresa ORDER BY id_nrempresa");

            while(rs.next()){
                vet.add(new NrEmpresa(rs.getInt("id_nrempresa"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getString("titulo"), rs.getString("status"), rs.getDate("dt_realizacao")));
            }

            stmt.close();

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id da nr_empresa
    public NrEmpresa buscarPorIdNrEmpresa(int idNrEmpresa){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_empresa WHERE id_nrempresa = ?");

            pstmt.setInt(1, idNrEmpresa);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new NrEmpresa(rs.getInt("id_nrempresa"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getString("titulo"), rs.getString("status"), rs.getDate("dt_realizacao"));
            }
            return null;

        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Read | Select - CRUD, mas baseado no Id da nr_descricao
    public ArrayList<NrEmpresa> buscarPorIdNrDescricao(int idNrDescricao){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<NrEmpresa> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_empresa WHERE id_nrdescricao = ? ORDER BY id_nrempresa");

            pstmt.setInt(1, idNrDescricao);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new NrEmpresa(rs.getInt("id_nrempresa"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getString("titulo"), rs.getString("status"), rs.getDate("dt_realizacao")));
            }

        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        } finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Update - CRUD
    public int alterarNrEmpresa(NrEmpresa nrEmpresa){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE nr_empresa set numero = ?, status = ?, titulo = ?, dt_realizacao = ?, id_nrdescricao = ? WHERE id_nrempresa = ?");

            pstmt.setInt(1, nrEmpresa.getNumero());
            pstmt.setString(2, nrEmpresa.getStatus());
            pstmt.setString(3, nrEmpresa.getTitulo());
            pstmt.setDate(4, nrEmpresa.getDtRealização());
            pstmt.setInt(5, nrEmpresa.getIdNrDescricao());
            pstmt.setInt(6, nrEmpresa.getIdNremp());

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
    public int remover(int idNrEmpresa){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM nr_empresa WHERE id_nrempresa = ?");

            pstmt.setInt(1, idNrEmpresa);

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