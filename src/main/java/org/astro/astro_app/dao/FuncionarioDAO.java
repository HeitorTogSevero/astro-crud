package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Funcionario - CRUD:
public class FuncionarioDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Funcionario f){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO funcionario (nome, cargo, cpf, id_empresa, id_departamento, email) VALUES (?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, f.getNome());
            pstmt.setString(2, f.getCargo());
            pstmt.setString(3, f.getCpf());
            pstmt.setInt(4, f.getIdEmpresa());
            pstmt.setInt(5, f.getIdDepartamento());
            pstmt.setString(6, f.getEmail());

            if (pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Read | Select - CRUD
    public ArrayList<Funcionario> buscar(String sql) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Funcionario> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                vet.add(new Funcionario(rs.getString("nome"), rs.getString("cargo"), rs.getString("cpf"), rs.getString("email"), rs.getInt("id_empresa"), rs.getInt("id_funcionario"), rs.getInt("id_departamento")));
            }

            stmt.close();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do funcionario
    public Funcionario buscarPorIdFunc(int idFunc) {

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM funcionario WHERE id_funcionario = ?");

            pstmt.setInt(1, idFunc);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Funcionario(rs.getString("nome"), rs.getString("cargo"), rs.getString("cpf"), rs.getString("email"), rs.getInt("id_empresa"), rs.getInt("id_funcionario"), rs.getInt("id_departamento"));
            }
            return null;

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Read | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<Funcionario> buscarPorIdEmpresa(int idEmpresa) {

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Funcionario> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM funcionario WHERE id_empresa = ? ORDER BY id_funcionario");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Funcionario(rs.getString("nome"), rs.getString("cargo"), rs.getString("cpf"), rs.getString("email"), rs.getInt("id_empresa"), rs.getInt("id_funcionario"), rs.getInt("id_departamento")));
            }

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do departamento
    public ArrayList<Funcionario> buscarPorIdDepart(int idDepart) {

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Funcionario> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM funcionario WHERE id_departamento = ? ORDER BY id_funcionario");

            pstmt.setInt(1, idDepart);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Funcionario(rs.getString("nome"), rs.getString("cargo"), rs.getString("cpf"), rs.getString("email"), rs.getInt("id_empresa"), rs.getInt("id_funcionario"), rs.getInt("id_departamento")));
            }

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    //metodo contar funcionarios

    public int contarFuncionarios(){
        int total = 0;
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn  = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id_funcionario) FROM funcionario");
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                total = rs.getInt(1);
            }
        }catch (SQLException e ){
            System.out.println("Erro ao contar funcionarios: " + e.getMessage());
        }
        return total;
    }
    // Metodo Update - CRUD
    public int alterarFuncionario(Funcionario f){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE funcionario set nome = ?, cargo = ?, cpf = ?, id_empresa = ?, id_departamento = ? WHERE id_funcionario = ?");

            pstmt.setString(1, f.getNome());
            pstmt.setString(2, f.getCargo());
            pstmt.setString(3, f.getCpf());
            pstmt.setInt(4, f.getIdEmpresa());
            pstmt.setInt(5, f.getIdDepartamento());
            pstmt.setInt(6, f.getIdFuncionario());

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Delete | Remove - CRUD
    public int remover(int idFuncionario){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM funcionario WHERE id_funcionario = ?");

            pstmt.setInt(1, idFuncionario);

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }
}