package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Administrador;

import java.sql.*;
import java.util.ArrayList;

// Metodo Create | Insert - CRUD
public class AdministradorDAO {
    public boolean inserirAdmin(Administrador admin){

        // Criando a Conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO administrador (nome, email, senha) VALUES (?, ?, ?)");

            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getSenha());

            if(pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn); // desconectando do BD
        }
    }

// Metodo Read | Select - CRUD:
    public ArrayList<Administrador> buscar(){

        // Criando a conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Administrador> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery("SELECT * FROM Administrador ORDER BY 1");

            while(rs.next()){
                vet.add(new Administrador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn);
            return vet;
        }
    }

// Metodo Read | Select - CRUD, busca por ID
    public ResultSet buscaPorIdAdmin(int idAdmin){

        // Criando conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ResultSet rs = null;

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM administrador WHERE id_admin = ?");

            pstmt.setInt(1, idAdmin);

        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn);// desconectando do BD
            return rs;
        }
    }

// Metodo Update - CRUD
    public int alterarAdministrador(Administrador admin){

        // Criando Conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE Administrador set nome = ?, email = ?, senha = ? WHERE id_admin = ? ");

            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getSenha());
            pstmt.setInt(4,admin.getIdAdmin());

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

//Metodo Delete | Remove - CRUD
    public int remover(int idAdmin){

        // Criando conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Administrador WHERE id_admin = ?");

            pstmt.setInt(1, idAdmin);

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
}
