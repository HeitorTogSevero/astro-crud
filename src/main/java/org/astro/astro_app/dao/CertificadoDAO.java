package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Certificado;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Certificado - CRUD:
public class CertificadoDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Certificado c){

        // Criando a conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO certificado (id_funcionario, id_nrfuncionario, id_nrempresa, dt_emissao, validade) VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, c.getIdFuncionario());
            pstmt.setInt(2, c.getIdNrFuncionario());
            pstmt.setInt(3, c.getIdNrEmpresa());
            pstmt.setDate(4, c.getDtEmissao());
            pstmt.setDate(5, c.getDtValidade());

            // Verificacao para saber se o INSERT funcionou:
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

    // Metodo Read | Select - CRUD
    public ArrayList<Certificado> buscar() {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Certificado> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM certificado ORDER BY id_certificado");

            while (rs.next()) {
                vet.add(new Certificado(rs.getInt("id_certificado"), rs.getInt("id_funcionario"), rs.getInt("id_nrfuncionario"), rs.getInt("id_nrempresa"), rs.getDate("dt_emissao"), rs.getDate("validade")));
            }

            stmt.close();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do certificado
    public Certificado buscarPorIdCertificado(int idCertificado) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM certificado WHERE id_certificado = ?");

            pstmt.setInt(1, idCertificado);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Certificado(rs.getInt("id_certificado"), rs.getInt("id_funcionario"), rs.getInt("id_nrfuncionario"), rs.getInt("id_nrempresa"), rs.getDate("dt_emissao"), rs.getDate("validade"));
            }
            return null;

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do funcionario
    public ArrayList<Certificado> buscarPorIdFuncionario(int idFuncionario) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Certificado> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM certificado WHERE id_funcionario = ? ORDER BY id_certificado");

            pstmt.setInt(1, idFuncionario);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Certificado(rs.getInt("id_certificado"), rs.getInt("id_funcionario"), rs.getInt("id_nrfuncionario"), rs.getInt("id_nrempresa"), rs.getDate("dt_emissao"), rs.getDate("validade")));
            }

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do nr_funcionario
    public ArrayList<Certificado> buscarPorIdNrFunc(int idNrFunc) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Certificado> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM certificado WHERE id_nrfuncionario = ? ORDER BY id_certificado");

            pstmt.setInt(1, idNrFunc);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Certificado(rs.getInt("id_certificado"), rs.getInt("id_funcionario"), rs.getInt("id_nrfuncionario"), rs.getInt("id_nrempresa"), rs.getDate("dt_emissao"), rs.getDate("validade")));
            }

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do nr_empresa
    public ArrayList<Certificado> buscarPorIdEmpresa(int idNrEmpresa) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Certificado> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM certificado WHERE id_nrempresa = ? ORDER BY id_certificado");

            pstmt.setInt(1, idNrEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Certificado(rs.getInt("id_certificado"), rs.getInt("id_funcionario"), rs.getInt("id_nrfuncionario"), rs.getInt("id_nrempresa"), rs.getDate("dt_emissao"), rs.getDate("validade")));
            }

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    // Metodo Update - CRUD
    public int alterarCertificado(Certificado c){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE certificado set id_funcionario = ?, id_nrfuncionario = ?, id_nrempresa = ?, dt_emissao = ?, validade = ? WHERE id_certificado = ?");

            pstmt.setInt(1, c.getIdFuncionario());
            pstmt.setInt(2, c.getIdNrFuncionario());
            pstmt.setInt(3, c.getIdNrEmpresa());
            pstmt.setDate(4, c.getDtEmissao());
            pstmt.setDate(5, c.getDtValidade());
            pstmt.setInt(6, c.getIdCertificado());

            if (pstmt.executeUpdate() > 0){
                return 1;
            }

            return 0;

        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }

    // Metodo Delete | Remove - CRUD
    public int remover(int idCertificado){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM certificado WHERE id_certificado = ?");

            pstmt.setInt(1, idCertificado);

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return -1;
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }
}