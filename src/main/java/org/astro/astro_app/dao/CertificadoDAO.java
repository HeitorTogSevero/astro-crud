package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Certificado;

import java.sql.*;
import java.util.ArrayList;


// Classe DAO Certificado - CRUD:
public class CertificadoDAO {

//    Metodo Create | Insert - CRUD
        public boolean inserir(Certificado c){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Certificado (id_funcionario, id_nrfunc, dt_emissao, validade) VALUES (?, ?, ?, ? )");

            pstmt.setInt(1, c.getIdFuncionario());
            pstmt.setInt(2, c.getIdNrFuncionario());
            pstmt.setDate(3, c.getDtEmissao());
            pstmt.setDate(4, c.getDtValidade());

//            Verificação para saber se o INSERT funcionou:
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
                vet.add(new Certificado(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDate(5)));
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
    public ResultSet buscarPorId(int id) {

        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet resultSet = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Certificado WHERE id_certificado = ?");

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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Certificado where id_certificado = ?");

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
}

