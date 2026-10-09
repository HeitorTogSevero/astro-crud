package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Administrador;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Administrador - CRUD
public class AdministradorDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserirAdmin(Administrador admin) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO administrador (nome, email, senha) VALUES (?, ?, ?)");

            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getSenha());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return false;
        } finally {
            conexao.desconectar(conn); // desconectando do BD
        }
    }

    // Metodo Read | Select - CRUD
    public ArrayList<Administrador> buscar(String sql) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Administrador> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                vet.add(new Administrador(rs.getInt("id_admin"), rs.getString("nome"), rs.getString("email"), rs.getString("senha")));
            }

            stmt.close();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn);
        }
        return vet;
    }

    // Metodo Read | Select - CRUD, busca por ID
    public Administrador buscaPorIdAdmin(int idAdmin) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM administrador WHERE id_admin = ?");

            pstmt.setInt(1, idAdmin);

            ResultSet rs = pstmt.executeQuery();

            // Lê os dados antes de fechar a conexão
            if (rs.next()) {
                return new Administrador(
                        rs.getInt("id_admin"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"));
            }
            return null; // não encontrou

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Update - CRUD
    public int alterarAdministrador(Administrador admin) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE administrador SET nome = ?, email = ?, senha = ? WHERE id_admin = ?");

            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getSenha());
            pstmt.setInt(4, admin.getIdAdmin());

            if (pstmt.executeUpdate() > 0) {
                return 1;
            }
            return 0;

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Delete | Remove - CRUD
    public int remover(int idAdmin) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM administrador WHERE id_admin = ?");

            pstmt.setInt(1, idAdmin);

            if (pstmt.executeUpdate() > 0) {
                return 1;
            }
            return 0;

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }
}