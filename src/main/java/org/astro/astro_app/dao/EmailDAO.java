package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Email;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Email - CRUD:
public class EmailDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Email email){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO email (id_funcionario, email) VALUES (?, ?)");

            pstmt.setInt(1, email.getIdFuncionario());
            pstmt.setString(2, email.getEmail());

            if(pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo READ | Select - CRUD
    public ArrayList<Email> buscar(){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Email> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM email ORDER BY id_email");

            while(rs.next()){
                // ordem do construtor: (idEmail, idFuncionario, email)
                vet.add(new Email(rs.getInt("id_email"), rs.getInt("id_funcionario"), rs.getString("email")));
            }

            stmt.close();

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally{
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo READ | Select - CRUD, mas baseado no Id do email
    public Email buscarPorIdEmail(int idEmail){

        // Criando a Conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM email WHERE id_email = ?");

            pstmt.setInt(1, idEmail);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Email(rs.getInt("id_email"), rs.getInt("id_funcionario"), rs.getString("email"));
            }
            return null;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return null;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo READ | Select - CRUD, mas baseado no Id do funcionario
    public ArrayList<Email> buscarPorIdFunc(int idFunc){

        // Criando a Conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Email> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM email WHERE id_funcionario = ? ORDER BY id_email");

            pstmt.setInt(1, idFunc);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Email(rs.getInt("id_email"), rs.getInt("id_funcionario"), rs.getString("email")));
            }

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Update - CRUD
    public int alterarEmail(Email email) {

        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE email SET id_funcionario = ?, email = ? WHERE id_email = ?");

            pstmt.setInt(1, email.getIdFuncionario());
            pstmt.setString(2, email.getEmail());
            pstmt.setInt(3, email.getIdEmail());

            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo DELETE - CRUD
    public int remover(int idEmail){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM email WHERE id_email = ?");

            pstmt.setInt(1, idEmail);

            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }
}