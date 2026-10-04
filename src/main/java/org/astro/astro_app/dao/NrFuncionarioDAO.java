package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.NrFuncionario;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO NrFuncionario - CRUD:
public class NrFuncionarioDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(NrFuncionario nrF){

        // Criando a conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO nr_funcionario (id_nrdescricao, numero, dt_realizacao, titulo, status) VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, nrF.getIdNrDescricao());
            pstmt.setInt(2, nrF.getNumero());
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
            conexao.desconectar(conn); // desconectando do Banco
        }
    }

    // Metodo READ | Select - CRUD
    public ArrayList<NrFuncionario> buscar(){

        // Criando a conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<NrFuncionario> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM nr_funcionario ORDER BY id_nrfuncionario");

            while(rs.next()){
                // ordem do construtor: idNrFunc, idNrDescricao, numero, dtRealizacao, titulo, status
                vet.add(new NrFuncionario(rs.getInt("id_nrfuncionario"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getDate("dt_realizacao"), rs.getString("titulo"), rs.getString("status")));
            }

            stmt.close();

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally{
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    // Metodo READ | Select - CRUD, mas baseado no Id do nr_funcionario
    public NrFuncionario buscarPorIdNrFunc(int idNrFunc){

        // Criando a Conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_funcionario WHERE id_nrfuncionario = ?");

            pstmt.setInt(1, idNrFunc);

            ResultSet rs = pstmt.executeQuery();

            // le os dados ANTES de fechar a conexao
            if (rs.next()) {
                return new NrFuncionario(rs.getInt("id_nrfuncionario"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getDate("dt_realizacao"), rs.getString("titulo"), rs.getString("status"));
            }
            return null;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return null;
        }finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }

    // Metodo READ | Select - CRUD, mas baseado no Id da nr_descricao
    public ArrayList<NrFuncionario> buscarPorIdNrDescricao(int idNrDescricao){

        // Criando a Conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<NrFuncionario> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_funcionario WHERE id_nrdescricao = ? ORDER BY id_nrfuncionario");

            pstmt.setInt(1, idNrDescricao);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new NrFuncionario(rs.getInt("id_nrfuncionario"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getDate("dt_realizacao"), rs.getString("titulo"), rs.getString("status")));
            }

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    // Metodo Update - CRUD
    public int alterarNrFuncionario(NrFuncionario nrf) {

        // Criando a conexão com o banco de dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            PreparedStatement pstmt = conn.prepareStatement("UPDATE nr_funcionario SET id_nrdescricao = ?, numero = ?, dt_realizacao = ?, titulo = ?, status = ? WHERE id_nrfuncionario = ?");

            pstmt.setInt(1, nrf.getIdNrDescricao());
            pstmt.setInt(2, nrf.getNumero());
            pstmt.setDate(3, nrf.getDtRealizacao());
            pstmt.setString(4, nrf.getTitulo());
            pstmt.setString(5, nrf.getStatus());
            pstmt.setInt(6, nrf.getIdNrFunc());

            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }

    // Metodo DELETE - CRUD
    public int remover(int idNrFuncionario){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM nr_funcionario WHERE id_nrfuncionario = ?");

            pstmt.setInt(1, idNrFuncionario);

            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }
}