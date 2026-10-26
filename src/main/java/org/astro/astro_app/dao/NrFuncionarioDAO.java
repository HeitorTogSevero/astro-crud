package org.astro.astro_app.dao;

// Biblioteca:
import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.NrFuncionario;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO NrFuncionario - CRUD:
public class NrFuncionarioDAO {

    // Método Create | Insert - CRUD
    public boolean inserir(NrFuncionario nrF){

        // Criando a conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO nr_funcionario (id_nrdescricao, numero, dt_realizacao, titulo, status) VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, nrF.getIdNrDescricao());
            pstmt.setInt(2, nrF.getNumero());
            pstmt.setDate(3, nrF.getDtRealizacao());
            pstmt.setString(4, nrF.getTitulo());
            pstmt.setString(5, nrF.getStatus());

            // Executa o comando inserir e verifica se alguma linha foi afetada
            if(pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método READ | Select - CRUD
    public ArrayList<NrFuncionario> buscar(String sql){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<NrFuncionario> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                // ordem do construtor: idNrFunc, idNrDescricao, numero, dtRealizacao, titulo, status
                vet.add(new NrFuncionario(rs.getInt("id_nrfuncionario"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getDate("dt_realizacao"), rs.getString("titulo"), rs.getString("status")));
            }

            stmt.close();

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally{
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método READ | Select - CRUD, mas baseado no Id do nr_funcionario
    public NrFuncionario buscarPorIdNrFunc(int idNrFunc){

        // Criando a Conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_funcionario WHERE id_nrfuncionario = ?");

            pstmt.setInt(1, idNrFunc);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new NrFuncionario(rs.getInt("id_nrfuncionario"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getDate("dt_realizacao"), rs.getString("titulo"), rs.getString("status"));
            }
            return null;

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return null;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método READ | Select - CRUD, mas baseado no Id da nr_descricao
    public ArrayList<NrFuncionario> buscarPorIdNrDescricao(int idNrDescricao){

        // Criando a Conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<NrFuncionario> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_funcionario WHERE id_nrdescricao = ? ORDER BY id_nrfuncionario");

            pstmt.setInt(1, idNrDescricao);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new NrFuncionario(rs.getInt("id_nrfuncionario"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getDate("dt_realizacao"), rs.getString("titulo"), rs.getString("status")));
            }

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método Update - CRUD
    public int alterarNrFuncionario(NrFuncionario nrf) {

        // Criando a conexão com o banco de dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
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

            // Executa o comando alterar e verifica se alguma linha foi afetada
            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método DELETE - CRUD
    public int remover(int idNrFuncionario){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos sql
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM nr_funcionario WHERE id_nrfuncionario = ?");

            pstmt.setInt(1, idNrFuncionario);

            // Executa o comando DELETE e verifica se alguma linha foi afetada
            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}