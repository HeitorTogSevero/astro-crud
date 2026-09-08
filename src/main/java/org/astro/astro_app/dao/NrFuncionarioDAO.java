package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Email;
import org.astro.astro_app.model.NrFuncionario;

import java.sql.*;
import java.util.ArrayList;

public class NrFuncionarioDAO {
//    Metodo CREATE | Insert - CRUD
    public boolean inserir(NrFuncionario nrF){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn =conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Nr_funcionario (id_nrdescricao, numero, dt_realizacao, titulo, status) VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, nrF.getIdNrDescricao());
            pstmt.setInt(2, nrF.getIdNrDescricao());
            pstmt.setDate(3, nrF.getDtRealizacao());
            pstmt.setString(4, nrF.getTitulo());
            pstmt.setString(5, nrF.getStatus());

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
    public ArrayList<NrFuncionario> buscar(){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;
        ArrayList<NrFuncionario> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement pstmt = conn.createStatement();

            ResultSet rs = pstmt.executeQuery("SELECT * FROM Nr_funcionario ORDER BY 1");

            while(rs.next()){
                vet.add(new NrFuncionario(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5), rs.getString(6)));
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Nr_Funcionario WHERE id_nrfuncionario = ?");

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
    public int alterarEmail(NrFuncionario nrf) {

        // Criando a conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Nr_funcionario SET id_nrfuncionario = ?, id_nrdescricao, numero, dt_realizacao, titulo, status WHERE id_nrfuncionario = ?");

            pstmt.setInt(1, nrf.getIdNrFunc());
            pstmt.setInt(2, nrf.getIdNrDescricao());
            pstmt.setInt(3, nrf.getNumero());
            pstmt.setDate(4, nrf.getDtRealizacao());
            pstmt.setString(5, nrf.getTitulo());
            pstmt.setString(4, nrf.getStatus());


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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Nr_funcionario WHERE  id_nrfuncionario = ?");

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
