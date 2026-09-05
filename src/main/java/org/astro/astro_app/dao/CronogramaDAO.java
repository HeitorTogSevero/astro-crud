package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Cronograma;
import org.astro.astro_app.model.Empresa;

import java.sql.*;
import java.util.ArrayList;

public class CronogramaDAO {

    //metodo inssert
    public boolean inserir(Cronograma cronograma){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO cronograma (agendamento, id_empresa, id_funcionario) values (?,?,?)");

            pstmt.setDate(1, cronograma.getAgendamento());
            pstmt.setInt(2, cronograma.getIdEmpresa());
            pstmt.setInt(3, cronograma.getIdFuncionario());

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
    public ArrayList<Cronograma> buscar(){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList <Cronograma> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cronograma ORDER BY 1");

            while(rs.next()){
                vet.add(new Cronograma(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4)));
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

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cronograma WHERE id_cronograma = ?");

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
    public int alterarEmpresa(Cronograma cronograma){
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE cronograma set agendamento = ? , id_empresa = ? , id_funcionario = ? ");

            pstmt.setDate(1, cronograma.getAgendamento());
            pstmt.setInt(2, cronograma.getIdEmpresa());
            pstmt.setInt(3, cronograma.getIdFuncionario());


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

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM cronograma WHERE id_cronograma = ?");

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
