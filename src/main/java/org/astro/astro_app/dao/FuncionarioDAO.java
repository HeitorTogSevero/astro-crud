package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Certificado;
import org.astro.astro_app.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Certificado - CRUD
public class FuncionarioDAO {

//    Metodo Create | Insert - CRUD
    public boolean inserir(Funcionario f){

        // Criando a conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Funcionario (nome, cargo, cpf, id_emrpesa, id_funcionario, id_departamento) VALUES (?, ?, ?, ?, ?, ? )");

            pstmt.setString(1, f.getNome());
            pstmt.setString(2, f.getCargo());
            pstmt.setString(3, f.getCpf());
            pstmt.setInt(4, f.getIdEmpresa());
            pstmt.setInt(5, f.getIdFuncionario());
            pstmt.setInt(6, f.getIdDepartamento());


//            Verificacao para saber se o INSERT funcionou:
            if (pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }

//    Metodo Read | Select - CRUD
    public ArrayList<Funcionario> buscar() {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Funcionario> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement pstmt = conn.createStatement();

            ResultSet rs = pstmt.executeQuery("SELECT * FROM FUNCIONARIO ORDER BY 1");

            while (rs.next()) {
                vet.add(new Funcionario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getInt(5), rs.getInt(6)));
            }

            pstmt.close();

        } catch (SQLException sqlE) {
            System.out.println("Erro ao inserir um Certificado: "+ sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }


    //    Metodo Read | Select - CRUD, mas baseado no ID
    public ResultSet buscarPorId(int id) {

        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet resultSet = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Funcionario WHERE id_certificado = ?");

            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());

        } finally {
            conexao.desconectar(conn); // desconectando do Banco
            return resultSet;
        }

    }

    //    Metodo Delete | Remove - CRUD
    public int remover(int id){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Funcionario where id_certificado = ?");

            pstmt.setInt(1, id);

            if (pstmt.executeUpdate() == 0) {
                return 0;
            }
            return 1;
        } catch (SQLException sqle) {
            System.out.println("Erro no comando sql");
            return -1;
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

    }

    //    Metodo Update - CRUD
    public int alterarFuncionario(Funcionario f){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Funcionario set nome = ?, cargo = ?, cpf = ?, id_empresa = ?,  id_departamento = ? WHERE id_funcionario = ?");

            pstmt.setString(1, f.getNome());
            pstmt.setString(2, f.getCargo());
            pstmt.setString(3, f.getCpf());
            pstmt.setInt(4, f.getIdEmpresa());
            pstmt.setInt(5, f.getIdDepartamento());
            pstmt.setInt(6, f.getIdFuncionario());


            if (pstmt.executeUpdate() > 0){
                return 0;
            }

            return 1;

        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }

    }
}
