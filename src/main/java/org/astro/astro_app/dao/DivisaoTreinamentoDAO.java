package org.astro.astro_app.dao;

// Biblioteca:
import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.DivisaoTreinamento;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO DivisaoTreinamento - CRUD:
public class DivisaoTreinamentoDAO {

    // Método Create | Insert - CRUD
    public boolean inserir(DivisaoTreinamento divisaoTreinamento){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO divisao_treinamento (nome, codigo, id_empresa, id_cronograma) VALUES (?, ?, ?, ?)");

            pstmt.setString(1, divisaoTreinamento.getNome());
            pstmt.setInt(2, divisaoTreinamento.getCodigo());
            pstmt.setInt(3, divisaoTreinamento.getIdEmpresa());
            pstmt.setInt(4, divisaoTreinamento.getIdCronograma());

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
    public ArrayList<DivisaoTreinamento> buscar(String sql){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<DivisaoTreinamento> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                // ordem do construtor: (idDivisao, idCronograma, codigo, idEmpresa, nome)
                vet.add(new DivisaoTreinamento(rs.getInt("id_divisao"), rs.getInt("id_cronograma"), rs.getInt("codigo"), rs.getInt("id_empresa"), rs.getString("nome")));
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

    // Método Read | Select - CRUD, mas baseado no Id da divisao
    public DivisaoTreinamento buscarPorIdDiv(int idDivisao){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM divisao_treinamento WHERE id_divisao = ?");

            pstmt.setInt(1, idDivisao);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new DivisaoTreinamento(rs.getInt("id_divisao"), rs.getInt("id_cronograma"), rs.getInt("codigo"), rs.getInt("id_empresa"), rs.getString("nome"));
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

    // Método Read | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<DivisaoTreinamento> buscarPorIdEmpresa(int idEmpresa){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<DivisaoTreinamento> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM divisao_treinamento WHERE id_empresa = ? ORDER BY id_divisao");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new DivisaoTreinamento(rs.getInt("id_divisao"), rs.getInt("id_cronograma"), rs.getInt("codigo"), rs.getInt("id_empresa"), rs.getString("nome")));
            }

        // Mensagem de erro da conexão
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método Read | Select - CRUD, mas baseado no Id do cronograma
    public ArrayList<DivisaoTreinamento> buscarPorIdCronograma(int idCronograma){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<DivisaoTreinamento> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM divisao_treinamento WHERE id_cronograma = ? ORDER BY id_divisao");

            pstmt.setInt(1, idCronograma);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new DivisaoTreinamento(rs.getInt("id_divisao"), rs.getInt("id_cronograma"), rs.getInt("codigo"), rs.getInt("id_empresa"), rs.getString("nome")));
            }

        // Mensagem de erro da conexão
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método Update - CRUD
    public int alterarDivisaoTreinamento(DivisaoTreinamento divisaoTreinamento){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE divisao_treinamento set nome = ?, codigo = ?, id_empresa = ?, id_cronograma = ? WHERE id_divisao = ?");

            pstmt.setString(1, divisaoTreinamento.getNome());
            pstmt.setInt(2, divisaoTreinamento.getCodigo());
            pstmt.setInt(3, divisaoTreinamento.getIdEmpresa());
            pstmt.setInt(4, divisaoTreinamento.getIdCronograma());
            pstmt.setInt(5, divisaoTreinamento.getIdDivisao());

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
    public int remover(int idDivisao){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM divisao_treinamento WHERE id_divisao = ?");

            pstmt.setInt(1, idDivisao);

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