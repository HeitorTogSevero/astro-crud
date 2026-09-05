package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.NrEmpresa;

import java.sql.*;
import java.util.ArrayList;

public class NrEmpresaDAO {
    //metodo inssert
    public boolean inserir(NrEmpresa nrEmpresa){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO nr_empresa (numero, status, titulo, dt_realizacao, id_nrdescricao) values (?,?,?,?,?)");

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
            System.out.println("Erro no comando sql foi: " + sqle.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);
        }


    }

    //metodo read
    public ArrayList<NrEmpresa> buscar(){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList <NrEmpresa> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM nr_empresa ORDER BY 1");

            while(rs.next()){
                vet.add(new NrEmpresa(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6)));
            }

            statement.close();
        }catch (SQLException sqle){
            System.out.println("O erro foi: " + sqle.getMessage());
        }finally {
            conexao.desconectar(conn);
        }
        return vet;
    }

    //metodo read por id
    public ResultSet buscarPorId(int id){
        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet rs = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_empresa WHERE id_nrempresa = ?");

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
    public int alterarEmpresa(NrEmpresa nrEmpresa){
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE nr_empresa set numero = ? , status = ? , titulo = ? , dt_realizacao = ? , id_nrdescricao = ?");

            pstmt.setInt(1, nrEmpresa.getNumero());
            pstmt.setString(2, nrEmpresa.getStatus());
            pstmt.setString(3, nrEmpresa.getTitulo());
            pstmt.setDate(4, nrEmpresa.getDtRealização());
            pstmt.setInt(5, nrEmpresa.getIdNrDescricao());


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

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM nr_empresa WHERE id_nrempresa = ?");

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
