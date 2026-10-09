package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Departamento;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Departamento - CRUD:
public class DepartamentoDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Departamento depto){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO departamento (nome, descricao, grau_risco, atividade, id_empresa) VALUES (?, ?, ?, ?, ?)");

            pstmt.setString(1, depto.getNome());
            pstmt.setString(2, depto.getDescricao());
            pstmt.setString(3, depto.getGrauRisco());
            pstmt.setString(4, depto.getAtividade());
            pstmt.setInt(5, depto.getIdEmpresa());

            if(pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Read | Select - CRUD
    public ArrayList<Departamento> buscar(String sql){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Departamento> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new Departamento(rs.getInt("id_departamento"), rs.getInt("id_empresa"), rs.getString("grau_risco"), rs.getString("nome"), rs.getString("descricao"), rs.getString("atividade")));
            }

            stmt.close();

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do departamento
    public Departamento buscarPorIdDepart(int idDepart){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM departamento WHERE id_departamento = ?");

            pstmt.setInt(1, idDepart);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Departamento(rs.getInt("id_departamento"), rs.getInt("id_empresa"), rs.getString("grau_risco"), rs.getString("nome"), rs.getString("descricao"), rs.getString("atividade"));
            }
            return null;

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Read | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<Departamento> buscarPorIdEmpresa(int idEmpresa){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Departamento> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM departamento WHERE id_empresa = ? ORDER BY id_departamento");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Departamento(rs.getInt("id_departamento"), rs.getInt("id_empresa"), rs.getString("grau_risco"), rs.getString("nome"), rs.getString("descricao"), rs.getString("atividade")));
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Update - CRUD
    public int alterarDepartamento(Departamento depto){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE departamento set nome = ?, descricao = ?, grau_risco = ?, atividade = ?, id_empresa = ? WHERE id_departamento = ?");

            pstmt.setString(1, depto.getNome());
            pstmt.setString(2, depto.getDescricao());
            pstmt.setString(3, depto.getGrauRisco());
            pstmt.setString(4, depto.getAtividade());
            pstmt.setInt(5, depto.getIdEmpresa());
            pstmt.setInt(6, depto.getIdDepartamento());

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Delete | Remove - CRUD
    public int remover(int idDepartamento){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM departamento WHERE id_departamento = ?");

            pstmt.setInt(1, idDepartamento);

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }
}