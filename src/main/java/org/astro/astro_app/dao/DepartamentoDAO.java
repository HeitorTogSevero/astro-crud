package org.astro.astro_app.dao;

// Biblioteca:
import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Departamento;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Departamento - CRUD:
public class DepartamentoDAO {

    // Método Create | Insert - CRUD
    public boolean inserir(Departamento depto){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO departamento (nome, descricao, grau_risco, atividade, id_empresa) VALUES (?, ?, ?, ?, ?)");

            pstmt.setString(1, depto.getNome());
            pstmt.setString(2, depto.getDescricao());
            pstmt.setString(3, depto.getGrauRisco());
            pstmt.setString(4, depto.getAtividade());
            pstmt.setInt(5, depto.getIdEmpresa());

            // Executa o comando inserir e verifica se alguma linha foi afetada
            if(pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        // Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return false;
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Read | Select - CRUD
    public ArrayList<Departamento> buscar(String sql){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        ArrayList<Departamento> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new Departamento(rs.getInt("id_departamento"), rs.getInt("id_empresa"), rs.getString("grau_risco"), rs.getString("nome"), rs.getString("descricao"), rs.getString("atividade")));
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

    // Método Read | Select - CRUD, mas baseado no Id do departamento
    public Departamento buscarPorIdDepart(int idDepart){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM departamento WHERE id_departamento = ?");

            pstmt.setInt(1, idDepart);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new Departamento(rs.getInt("id_departamento"), rs.getInt("id_empresa"), rs.getString("grau_risco"), rs.getString("nome"), rs.getString("descricao"), rs.getString("atividade"));
            }
            return null;

        // Mensagem de erro da conexão
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return null;
        } finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Read | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<Departamento> buscarPorIdEmpresa(int idEmpresa){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        ArrayList<Departamento> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM departamento WHERE id_empresa = ? ORDER BY id_departamento");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Departamento(rs.getInt("id_departamento"), rs.getInt("id_empresa"), rs.getString("grau_risco"), rs.getString("nome"), rs.getString("descricao"), rs.getString("atividade")));
            }

        // Mensagem de erro da conexão
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método de contar departamentos, que retorna o total de departamentos cadastrados no banco de dados
    public int contarDepartamentos(){

        int total = 0;

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id_departamento) FROM departamento");
            ResultSet rs = pstmt.executeQuery();

            // Executa o comando contar e verifica se alguma linha foi afetada
            if (rs.next()){
                total = rs.getInt(1);
            }

        // Mensagem de erro da conexão
        }catch (SQLException e){
            System.out.println("erro ao contar os departamentos: " + e.getMessage());
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
        return total;
    }

    // Método Update - CRUD
    public int alterarDepartamento(Departamento depto){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE departamento set nome = ?, descricao = ?, grau_risco = ?, atividade = ?, id_empresa = ? WHERE id_departamento = ?");

            pstmt.setString(1, depto.getNome());
            pstmt.setString(2, depto.getDescricao());
            pstmt.setString(3, depto.getGrauRisco());
            pstmt.setString(4, depto.getAtividade());
            pstmt.setInt(5, depto.getIdEmpresa());
            pstmt.setInt(6, depto.getIdDepartamento());

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
    public int remover(int idDepartamento){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM departamento WHERE id_departamento = ?");

            pstmt.setInt(1, idDepartamento);

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
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}