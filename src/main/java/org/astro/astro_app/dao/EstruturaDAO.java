package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Estrutura;

import java.sql.*;
import java.util.ArrayList;

public class EstruturaDAO {
//    Metodo CREATE | Insert - CRUD
    public boolean inserir(Estrutura est){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn =conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Estrutura (id_empresa, risco, descricao) VALUES (?, ?, ?)");

            pstmt.setInt(1, est.getIdEmpresa());
            pstmt.setString(2, est.getRisco());
            pstmt.setString(3, est.getDescricao());

            // Verificando se o Insert deu certo:
            if(pstmt.executeUpdate() > 0){
                return true;
            }
            return false;
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);// desconectando do Banco
        }
    }

    //    Metofo READ | Select - CRUD
    public ArrayList<Estrutura> buscar(){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;
        ArrayList<Estrutura> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement pstmt = conn.createStatement();

            ResultSet rs = pstmt.executeQuery("SELECT * FROM Email ORDER BY 1");

            while(rs.next()){
                vet.add(new Estrutura(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally{
            conexao.desconectar(conn);// desconectando do Banco
        }
        return vet;
    }

    //    Metodo READ | Select - CRUD, mas baseado no ID
    public ResultSet buscarPorId(int id){

        // Criando a Conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ResultSet rs = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM email WHERE id_email = ?");

            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
        return rs;
    }

    //    Metodo Upadate - CRUD
    public int alterarEmail(Estrutura est) {

        // Criando a conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Estrutura SET id_estrutura = ?, id_empresa = ?, risco = ?, descricao = ? WHERE id_estrutura = ?");

            pstmt.setInt(1, est.getIdEstrutura());
            pstmt.setInt(2, est.getIdEmpresa());
            pstmt.setString(3, est.getRisco());
            pstmt.setString(4, est.getDescricao());
            pstmt.setInt(5, est.getIdEstrutura());


            if(pstmt.executeUpdate() > 0){
                return 0;
            }

            return 1;
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);// desconectando do Banco
        }
    }

    //    Metodo DELETE - CRUD
    public int remover(int id){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Estrutura WHERE  id_estrutura = ?");

            pstmt.setInt(1, id);

            if(pstmt.executeUpdate() == 0){
                return 1;
            }
            return 0;
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);// desconectando do Banco
        }
    }
}
