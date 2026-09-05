package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.NrDescricao;

import java.sql.*;
import java.util.ArrayList;

public class NrDescricaoDAO {

    //metodo inserir
    public boolean inserir(NrDescricao nrDescricao){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO nr_descricao (titulo, descricao, tempo_reciclagem) values (?,?,?)");

            pstmt.setString(1, nrDescricao.getTitulo());
            pstmt.setString(2, nrDescricao.getDescricao());
            pstmt.setString(3, nrDescricao.getTempoReciclagem());

            if (pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        }catch(SQLException sqle){
            System.out.println("Erro no comando sql foi: " + sqle.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);
        }


    }

    //metodo read
    public ArrayList<NrDescricao> buscar(){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList <NrDescricao> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM nr_descricao ORDER BY 1");

            while(rs.next()){
                vet.add(new NrDescricao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

            statement.close();
        }catch (SQLException sqle){
            System.out.println("O erro foi: " + sqle.getMessage());
        }finally {
            conexao.desconectar(conn);
        }
        return vet;
    }


    //metodo read - com id
    public ResultSet buscarPorId(int id){
        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet rs = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_descricao WHERE id_nrdescricao = ?");

            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

        } catch (SQLException sqle){
            System.out.println("O erro foi: " + sqle.getMessage());
        } finally {
            conexao.desconectar(conn);
            return rs;
        }



    }


    //metodo update
    public int alterarEmpresa(NrDescricao nrDescricao){
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE nr_descricao set titulo = ? , descricao = ? , tempo_reciclagem = ?");

            pstmt.setString(1, nrDescricao.getTitulo());
            pstmt.setString(2, nrDescricao.getDescricao());
            pstmt.setString(3, nrDescricao.getTempoReciclagem());


            if (pstmt.executeUpdate() > 0){
                return 0;
            }
            return 1;

        }catch (SQLException sqle){
            System.out.println("O erro foi: " + sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }


    }


    //metodo delete
    public int remover(int id){
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM nr_descricao WHERE id_nrdescricao = ?");

            pstmt.setInt(1, id);

            if (pstmt.executeUpdate() == 0) {
                return 0;
            }
            return 1;
        }catch (SQLException sqle){
            System.out.println("O erro foi: " + sqle.getMessage());
            return  -1;
        }finally {
            conexao.desconectar(conn);
        }

    }


}
