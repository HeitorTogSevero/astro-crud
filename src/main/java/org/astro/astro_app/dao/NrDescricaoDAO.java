package org.astro.astro_app.dao;

// Biblioteca:
import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.NrDescricao;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO NrDescricao - CRUD:
public class NrDescricaoDAO {

    // Método Create | Insert - CRUD
    public boolean inserir(NrDescricao nrDescricao){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO nr_descricao (titulo, descricao, tempo_reciclagem) VALUES (?, ?, ?)");

            pstmt.setString(1, nrDescricao.getTitulo());
            pstmt.setString(2, nrDescricao.getDescricao());
            pstmt.setString(3, nrDescricao.getTempoReciclagem());

            // Executa o comando inserir e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        // Mensagem de erro da conexão
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Read | Select - CRUD
    public ArrayList<NrDescricao> buscar(String sql){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<NrDescricao> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new NrDescricao(rs.getInt("id_nrdescricao"), rs.getString("descricao"), rs.getString("tempo_reciclagem"), rs.getString("titulo")));
            }

            stmt.close();

        // Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método Read | Select - CRUD, mas baseado no Id
    public NrDescricao buscarPorIdNrDescricao(int idNrDescricao){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_descricao WHERE id_nrdescricao = ?");

            pstmt.setInt(1, idNrDescricao);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new NrDescricao(rs.getInt("id_nrdescricao"), rs.getString("descricao"), rs.getString("tempo_reciclagem"), rs.getString("titulo"));
            }
            return null;

        // Mensagem de erro da conexão
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Update - CRUD
    public int alterarNrDescricao(NrDescricao nrDescricao){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE nr_descricao set titulo = ?, descricao = ?, tempo_reciclagem = ? WHERE id_nrdescricao = ?");

            pstmt.setString(1, nrDescricao.getTitulo());
            pstmt.setString(2, nrDescricao.getDescricao());
            pstmt.setString(3, nrDescricao.getTempoReciclagem());
            pstmt.setInt(4, nrDescricao.getIdNrDescricao());

            // Executa o comando alterar e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        // Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Delete | Remove - CRUD
    public int remover(int idNrDescricao){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM nr_descricao WHERE id_nrdescricao = ?");

            pstmt.setInt(1, idNrDescricao);

            // Executa o comando DELETE e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        // Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}