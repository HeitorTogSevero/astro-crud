package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao; //Import da classe Conexao
import org.astro.astro_app.model.Administrador; //Import da classe Adiministrador

import java.sql.*; //Import das classes java.sql
import java.util.ArrayList; //Import da classe ArrayList

// Classe DAO Administrador - CRUD
public class AdministradorDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserirAdmin(Administrador admin) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO administrador (nome, email, senha) VALUES (?, ?, crypt(?, gen_salt('bf',12)))");

            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getSenha());

            return pstmt.executeUpdate() > 0;

        //Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return false;
        } finally {
            conexao.desconectar(conn); // Desconectando do banco de dados
        }
    }

    // Metodo Read | Select - CRUD
    public ArrayList<Administrador> buscar(String sql) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Administrador> vet = new ArrayList<>();

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                vet.add(new Administrador(rs.getInt("id_admin"), rs.getString("nome"), rs.getString("email"), rs.getString("senha")));
            }

            stmt.close();
        //Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // Desconectando do banco de dados
        }
        return vet;
    }

    // Metodo Read | Select - CRUD, busca por ID
    public Administrador buscaPorIdAdmin(int idAdmin) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
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
            return null;
        //Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn); // Desconectando do banco de dados
        }
    }

    // Metodo Update - CRUD
    public int alterarAdministrador(Administrador admin) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE administrador SET nome = ?, email = ?, senha = crypt(?, gen_salt('bf', 12)) WHERE id_admin = ?");

            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getSenha());
            pstmt.setInt(4, admin.getIdAdmin());

            // Executa o comando ALTERAR e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0) {
                return 1;
            }
            return 0;

        //Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return -1;
        } finally {
            conexao.desconectar(conn); // Desconectando do banco de dados
        }
    }

    // Metodo Delete | Remove - CRUD
    public int remover(int idAdmin) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM administrador WHERE id_admin = ?");

            pstmt.setInt(1, idAdmin);

            // Executa o comando DELETE e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0) {
                return 1;
            }
            return 0;

        //Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return -1;
        } finally {
            conexao.desconectar(conn); // Desconectando do banco de dados
        }
    }

    // Metodo de autenticar o login: compara a senha digitada com o hash salvo
    public Administrador autenticar(String email, String senha) {

        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT id_admin, nome, email FROM administrador WHERE email = ? AND senha = crypt(?, senha)");

            pstmt.setString(1, email);

            pstmt.setString(2, senha);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando AUTENTICAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new Administrador(rs.getInt("id_admin"), rs.getString("nome"), rs.getString("email"), null); // O null serve para não devolver a senha
            }
            return null;

        //Mensagem de erro da conexão
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn); // Desconectando do banco de dados
        }
    }

    // Método para validar se o email está correto
    public boolean emailExiste(String email) {

        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("SELECT 1 FROM administrador WHERE email = ?");

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        //Mensagem de erro da conexão
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return true; // na dúvida, bloqueia o cadastro
        } finally {
            conexao.desconectar(conn); // Desconectando do banco de dados
        }
    }


}