package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Certificado;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Certificado - CRUD:
public class CertificadoDAO {

//    Metodo Create | Insert - CRUD
    public boolean inserir(Certificado c){

        // Criando a conexão com o banco de dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Certificado (id_funcionario, id_nrfuncionario, id_nrempresa, dt_emissao, validade) VALUES (?, ?, ?, ?, ? )");

            pstmt.setInt(1, c.getIdFuncionario());
            pstmt.setInt(2, c.getIdNrFuncionario());
            pstmt.setInt(3, c.getIdNrEmpresa());
            pstmt.setDate(4, c.getDtEmissao());
            pstmt.setDate(5, c.getDtValidade());

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
    public ArrayList<Certificado> buscar() {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Certificado> vet = new ArrayList<>();

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement pstmt = conn.createStatement();

            ResultSet rs = pstmt.executeQuery("SELECT * FROM CERTIFICADO ORDER BY 1");

            while (rs.next()) {
                vet.add(new Certificado(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),rs.getDate(5), rs.getDate(6)));
            }

            pstmt.close();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

//    Metodo Read | Select - CRUD, mas baseado no ID
    public ResultSet buscarPorIdCertificado(int IdCertificado) {

        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet resultSet = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Certificado WHERE id_certificado = ?");

            pstmt.setInt(1, IdCertificado);
            resultSet = pstmt.executeQuery();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());

        } finally {
            conexao.desconectar(conn); // desconectando do Banco
            return resultSet;
        }

    }

//    Metodo Read | Select - CRUD, mas baseado no ID
    public ResultSet buscarPorIdFuncionario(int IdFuncionario) {

        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet resultSet = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Certificado WHERE id_funcionario = ?");

            pstmt.setInt(1, IdFuncionario);
            resultSet = pstmt.executeQuery();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());

        } finally {
            conexao.desconectar(conn); // desconectando do Banco
            return resultSet;
        }

    }

//    Metodo Read | Select - CRUD, mas baseado no ID
    public ResultSet buscarPorIdNrFunc(int IdNrFunc) {

        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet resultSet = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Certificado WHERE id_nrfuncionario = ?");

            pstmt.setInt(1, IdNrFunc);
            resultSet = pstmt.executeQuery();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());

        } finally {
            conexao.desconectar(conn); // desconectando do Banco
            return resultSet;
        }

    }

//    Metodo Read | Select - CRUD, mas baseado no ID
    public ResultSet buscarPorIdEmpresa(int IdEmpresa) {

        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet resultSet = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Certificado WHERE id_nrempresa = ?");

            pstmt.setInt(1, IdEmpresa);
            resultSet = pstmt.executeQuery();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());

        } finally {
            conexao.desconectar(conn); // desconectando do Banco
            return resultSet;
        }

    }


    //    Metodo Update - CRUD
    public int alterarCertificado(Certificado c){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Certificado set id_funcionario = ?, id_nrfuncionario = ?, id_nrempresa = ?, dt_emissao = ?,  validade = ? WHERE id_certificado = ?");

            pstmt.setInt(1, c.getIdFuncionario());
            pstmt.setInt(2, c.getIdNrFuncionario());
            pstmt.setInt(3, c.getIdNrEmpresa());
            pstmt.setDate(4, c.getDtEmissao());
            pstmt.setDate(5, c.getDtValidade());
            pstmt.setInt(6, c.getIdCertificado());

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

//    Metodo Delete | Remove - CRUD
    public int remover(int id){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Certificado where id_certificado = ?");

            pstmt.setInt(1, id);

            if (pstmt.executeUpdate() == 0) {
                return 0;
            }
            return 1;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return -1;
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

    }
 }

