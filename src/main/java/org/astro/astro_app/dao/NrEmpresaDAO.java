package org.astro.astro_app.dao;

// Biblioteca:
import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.NrEmpresa;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO NrEmpresa - CRUD:
public class NrEmpresaDAO {

    // Método Create | Insert - CRUD
    public boolean inserir(NrEmpresa nrEmpresa){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO nr_empresa (numero, status, titulo, dt_realizacao, id_nrdescricao) VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, nrEmpresa.getNumero());
            pstmt.setString(2, nrEmpresa.getStatus());
            pstmt.setString(3, nrEmpresa.getTitulo());
            pstmt.setDate(4, nrEmpresa.getDtRealização());
            pstmt.setInt(5, nrEmpresa.getIdNrDescricao());

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
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Read | Select - CRUD
    public ArrayList<NrEmpresa> buscar(String sql){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        ArrayList<NrEmpresa> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new NrEmpresa(rs.getInt("id_nrempresa"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getString("titulo"), rs.getString("status"), rs.getDate("dt_realizacao")));
            }

            stmt.close();

        // Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método Read | Select - CRUD, mas baseado no Id da nr_empresa
    public NrEmpresa buscarPorIdNrEmpresa(int idNrEmpresa){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_empresa WHERE id_nrempresa = ?");

            pstmt.setInt(1, idNrEmpresa);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new NrEmpresa(rs.getInt("id_nrempresa"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getString("titulo"), rs.getString("status"), rs.getDate("dt_realizacao"));
            }
            return null;

        // Mensagem de erro da conexão
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return null;
        } finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Read | Select - CRUD, mas baseado no Id da nr_descricao
    public ArrayList<NrEmpresa> buscarPorIdNrDescricao(int idNrDescricao){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        ArrayList<NrEmpresa> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nr_empresa WHERE id_nrdescricao = ? ORDER BY id_nrempresa");

            pstmt.setInt(1, idNrDescricao);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new NrEmpresa(rs.getInt("id_nrempresa"), rs.getInt("id_nrdescricao"), rs.getInt("numero"), rs.getString("titulo"), rs.getString("status"), rs.getDate("dt_realizacao")));
            }

        // Mensagem de erro da conexão
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        } finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método Update - CRUD
    public int alterarNrEmpresa(NrEmpresa nrEmpresa){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE nr_empresa set numero = ?, status = ?, titulo = ?, dt_realizacao = ?, id_nrdescricao = ? WHERE id_nrempresa = ?");

            pstmt.setInt(1, nrEmpresa.getNumero());
            pstmt.setString(2, nrEmpresa.getStatus());
            pstmt.setString(3, nrEmpresa.getTitulo());
            pstmt.setDate(4, nrEmpresa.getDtRealização());
            pstmt.setInt(5, nrEmpresa.getIdNrDescricao());
            pstmt.setInt(6, nrEmpresa.getIdNremp());

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
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Delete | Remove - CRUD
    public int remover(int idNrEmpresa){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM nr_empresa WHERE id_nrempresa = ?");

            pstmt.setInt(1, idNrEmpresa);

            // Executa o comando delete e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        // Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}