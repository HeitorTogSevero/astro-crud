package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Departamento_Equipamento;

import javax.management.remote.JMXConnectorServer;
import java.sql.*;
import java.util.ArrayList;

// Classe DAO Departamento_Equipamento - CRUD
public class Departamento_EquipamentoDAO {

//    Metodo Create | Insert - CRUD:
    public boolean inserir(Departamento_Equipamento dpE){

        // Criando a Conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para relizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Departamento_Equipamento");

            pstmt.setInt(1, dpE.getIdDepartamento());
            pstmt.setInt(2, dpE.getIdEquipamento());

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

//    Metodo READ | Select - CRUD
    public ArrayList<Departamento_Equipamento> buscar(){

        // Criando a Conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Departamento_Equipamento> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            Statement pstmt = conn.createStatement();

            ResultSet rs = pstmt.executeQuery("SELECT * FROM Departamento_equipamento ORDER BY 1");

            while(rs.next()){
                vet.add(new Departamento_Equipamento(rs.getInt(1), rs.getInt(2)));
            }

        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
        return vet;
    }

//    Metodo READ | Select - CRUD, mas baseado no Id
    public ResultSet buscarPorId(int id){

        // Criando a Conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ResultSet rs = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Departamento_Equipamento WHERE id_departamento = ?");

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
    public int alterarDepartementoEquipamento(Departamento_Equipamento dpE) {

        // Criando a conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Departamento_equipamento SET id_departamento = ?, id_equipamento = ? WHERE id_departamento = ?");

            pstmt.setInt(1, dpE.getIdDepartamento());
            pstmt.setInt(2, dpE.getIdEquipamento());
            pstmt.setInt(3, dpE.getIdDepartamento());

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
    public int remover(int id){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Departamento_equipamento WHERE id_Departamento = ?");

            pstmt.setInt(1, id);

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



