package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Departamento_Equipamento;
import org.astro.astro_app.model.Email;

import java.sql.*;
import java.util.ArrayList;

// Calsse DAO Email - CRUD
public class EmailDAO {

//    Metodo CREATE | Insert - CRUD
    public boolean inserir(Email email){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn =conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Email (id_funcionario, email) VALUES ( ?, ?)");

            pstmt.setInt(1, email.getIdFuncionario());
            pstmt.setString(2, email.getEmail());

            // Verificando se o Insert deu certo:
            if(pstmt.executeUpdate() > 0){
                return true;
            }
            return false;
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);// desconectando do Banco
        }
    }

//    Metofo READ | Select - CRUD
    public ArrayList<Email> buscar(){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;
        ArrayList<Email> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement pstmt = conn.createStatement();

            ResultSet rs = pstmt.executeQuery("SELECT * FROM Email ORDER BY 1");

            while(rs.next()){
                vet.add(new Email(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally{
            conexao.desconectar(conn);// desconectando do Banco
        }
        return vet;
    }

//    Metodo READ | Select - CRUD, mas baseado no ID
public ResultSet buscarPorId(int id){

    // Criando a Conexão com o Banco de Dados
    Conexao conexao = new Conexao();
    Connection conn = null;

    ResultSet rs = null;

    try{
        conn = conexao.conectar();

        // Interface para realizar comandos SQL's
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM email WHERE id_email = ?");

        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();

    }catch(SQLException sqlE){
        System.out.println(sqlE.getMessage());
    }finally {
        conexao.desconectar(conn); // desconectando do Banco
    }
    return rs;
}

    //    Metodo Upadate - CRUD
    public int alterarEmail(Email email) {

        // Criando a conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Email SET id_email = ?, id_Funcionario = ?, email = ? WHERE id_email = ?");

            pstmt.setInt(1, email.getIdEmail());
            pstmt.setInt(2, email.getIdFuncionario());
            pstmt.setString(3, email.getEmail());
            pstmt.setInt(4, email.getIdEmail());


            if(pstmt.executeUpdate() > 0){
                return 0;
            }

            return 1;
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);// desconectando do Banco
        }
    }

    //    Metodo DELETE - CRUD
    public int remover(int id, String email){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Email WHERE  id_email = ? OR email = ?");

            pstmt.setInt(1, id);
            pstmt.setString(2, email);

            if(pstmt.executeUpdate() == 0){
                return 1;
            }
            return 0;
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);// desconectando do Banco
        }
    }
}





