package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.DivisaoTreinamento;
import org.astro.astro_app.model.Empresa;

import java.sql.*;
import java.util.ArrayList;

public class DivisaoTreinamentoDAO {
    //metodo insert
    public boolean inserir(DivisaoTreinamento divisaoTreinamento){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO divisao_treinamento (nome, codigo, qtd_funcionario, id_empresa, id_cronograma) values (?,?,?,?,?)");

            pstmt.setString(1, divisaoTreinamento.getNome());
            pstmt.setInt(2, divisaoTreinamento.getCodigo());
            pstmt.setInt(3, divisaoTreinamento.getQtdFuncionario());
            pstmt.setInt(4, divisaoTreinamento.getIdEmpresa());
            pstmt.setInt(5, divisaoTreinamento.getIdCronograma());


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
    public ArrayList<DivisaoTreinamento> buscar(){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList <DivisaoTreinamento> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM divisao_treinamento ORDER BY 1");

            while(rs.next()){
                vet.add(new DivisaoTreinamento(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6)));
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

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM divisao_treinamento WHERE id_divisao = ?");

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
    public int alterarEmpresa(DivisaoTreinamento divisaoTreinamento){
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE divisao_treinamento set nome = ? , codigo = ? , qtd_funcionario = ? , id_empresa = ? , id_cronograma = ? ");

            pstmt.setString(1, divisaoTreinamento.getNome());
            pstmt.setInt(2, divisaoTreinamento.getCodigo());
            pstmt.setInt(3, divisaoTreinamento.getQtdFuncionario());
            pstmt.setInt(4, divisaoTreinamento.getIdEmpresa());
            pstmt.setInt(5, divisaoTreinamento.getIdCronograma());

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

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM divisao_treinamento WHERE id_divisao = ?");

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
