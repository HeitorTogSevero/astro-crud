package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Departamento;

import java.sql.*;
import java.util.ArrayList;

public class DepartamentoDAO {

    //metodo insert
    public boolean inserir(Departamento depto){
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO departamento (nome, descricao, grau_risco, atividade, qtd_funcionario, id_empresa)");

            pstmt.setString(1, depto.getNome());
            pstmt.setString(2, depto.getDescricao());
            pstmt.setString(3, depto.getGrauRisco());
            pstmt.setString(4, depto.getAtividade());
            pstmt.setInt(5, depto.getQtdFuncionario());
            pstmt.setInt(6, depto.getIdEmpresa());

            if(pstmt.executeUpdate() > 0 ){
                return true;
            }
            return false;
        }catch (SQLException sqle){
            System.out.println("O erro foi: " + sqle.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);
        }
    }

    //metodo read
    public ArrayList<Departamento> buscar(){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Departamento> vet= new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM departamento ORDER BY 1");

            while(rs.next()){
                vet.add(new Departamento(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }

            statement.close();

        }catch (SQLException sqle){
            System.out.println("O erro foi: " + sqle.getMessage());
        }finally {
            conexao.desconectar(conn);
        }
        return vet;
    }

    //metodo read - por id
    public ResultSet buscarPorId(int id){

        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet rs = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM departamento WHERE id_departamento = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
        } catch (SQLException sqle) {
            System.out.println("O erro foi: " + sqle.getMessage());
        }finally {
            conexao.desconectar(conn);
            return rs;
        }



    }

    //metodo update
    public int alterarDepartamento(Departamento depto){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE departamento set nome = ?, descricao = ?, grau_risco=?, atividade=?, qtd_funcionario=?,id_empresa=?");

            pstmt.setString(1, depto.getNome());
            pstmt.setString(2, depto.getDescricao());
            pstmt.setString(3, depto.getGrauRisco());
            pstmt.setString(4, depto.getAtividade());
            pstmt.setInt(5, depto.getQtdFuncionario());
            pstmt.setInt(6, depto.getIdEmpresa());

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

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM departamento WHERE id_departamento = ?");

            pstmt.setInt(1, id);

            if (pstmt.executeUpdate() == 0) {
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
}