package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Alerta;
import org.astro.astro_app.model.Certificado;

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
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO alerta (codigo, dt_limite, descricao, id_empresa) VALUES (?, ?, ?, ?)");


            pstmt.setInt(1, a.getCodigo());
            pstmt.setDate(2, a.getDtLimite());
            pstmt.setString(3, a.getDescricao());
            pstmt.setInt(4, a.getIdEmpresa());

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
                vet.add(new Alerta(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDate(5)));
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
    public ResultSet buscarPorId(int codigo) {

        // Criando conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet resultSet = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM alerta WHERE codigo = ?");

            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();


        } catch (SQLException sqlE) {
            System.out.println("Erro ao buscar Alerta por ID: " + sqlE.getMessage());

        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return resultSet;
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

//    Metodo Update - CRUD
    public int alterarCertificado(Alerta a){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Certificado set codigo = ?, id_empresa = ?, descricao = ?, dt_limete = ? WHERE id_alerta = ?");

            pstmt.setInt(1, a.getCodigo());
            pstmt.setInt(2, a.getIdEmpresa());
            pstmt.setString(3, a.getDescricao());
            pstmt.setDate(4, a.getDtLimite());
            pstmt.setInt(5, a.getIdAlerta());


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