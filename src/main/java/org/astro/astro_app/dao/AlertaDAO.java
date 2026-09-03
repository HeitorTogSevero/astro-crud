package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Alerta;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Alerta - CRUD:
public class AlertaDAO {

    // Metodo Create | Insert - CRUD:
    public boolean inserir(Alerta a) {

        // Criando a conexao com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO alerta (dt_limite, descricao, id_empresa) VALUES (?, ?, ?)");


            pstmt.setInt(1, a.getDtLimite());
            pstmt.setString(2, a.getDescricao());
            pstmt.setInt(3, a.getIdEmpresa());

            // Verificacao para saber se o INSERT funcionou:
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            return false;

        } catch (SQLException sqlE) {
            System.out.println("Erro ao inserir Alerta: " + sqlE.getMessage());
            return false;
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }

    // Metodo Read | Select - CRUD
    public ArrayList<Alerta> buscar() {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Alerta> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM alerta ORDER BY codigo");

            while (rs.next()) {
                // Pegando os dados do banco e instanciando um novo Alerta
                vet.add(new Alerta(
                        rs.getInt("codigo"),
                        rs.getInt("dt_limite"),
                        rs.getString("descricao"),
                        rs.getInt("id_empresa")
                ));
            }

            stmt.close();

        } catch (SQLException sqlE) {
            System.out.println("Erro ao buscar alertas: " + sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    // Metodo Read | Select - CRUD
    public Alerta buscarPorId(int codigo) {

        Conexao conexao = new Conexao();
        Connection conn = null;
        Alerta alertaEncontrado = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM alerta WHERE codigo = ?");
            pstmt.setInt(1, codigo);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                alertaEncontrado = new Alerta(
                        rs.getInt("codigo"),
                        rs.getInt("dt_limite"),
                        rs.getString("descricao"),
                        rs.getInt("id_empresa")
                );
            }

        } catch (SQLException sqlE) {
            System.out.println("Erro ao buscar Alerta por ID: " + sqlE.getMessage());

        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return alertaEncontrado;
    }

    // Metodo Delete | Remove - CRUD
    public int remover(int codigo) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM alerta WHERE codigo = ?");

            pstmt.setInt(1, codigo);

            if (pstmt.executeUpdate() == 0) {
                return 0;
            }
            return 1; 

        } catch (SQLException sqle) {
            System.out.println("Erro no comando sql de exclusão: " + sqle.getMessage());
            return -1;
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }




}