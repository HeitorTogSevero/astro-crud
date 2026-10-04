package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Departamento_Equipamento;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Departamento_Equipamento - CRUD
public class Departamento_EquipamentoDAO {

    // Metodo Create | Insert - CRUD:
    public boolean inserir(Departamento_Equipamento dpE){

        // Criando a Conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO depart_equipamento (id_departamento, id_equipamento) VALUES (?, ?)");

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
            conexao.desconectar(conn);
        }
    }

    // Metodo READ | Select - CRUD
    public ArrayList<Departamento_Equipamento> buscar(){

        // Criando a Conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Departamento_Equipamento> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM depart_equipamento ORDER BY id_departamento, id_equipamento");

            while(rs.next()){
                // ordem do construtor: (idDepartamento, idEquipamento)
                vet.add(new Departamento_Equipamento(rs.getInt("id_departamento"), rs.getInt("id_equipamento")));
            }

            stmt.close();

        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo READ | Select - CRUD, mas baseado no Id do departamento
    public ArrayList<Departamento_Equipamento> buscarPorIdDepart(int idDepart){

        // Criando a Conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Departamento_Equipamento> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM depart_equipamento WHERE id_departamento = ? ORDER BY id_equipamento");

            pstmt.setInt(1, idDepart);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                vet.add(new Departamento_Equipamento(rs.getInt("id_departamento"), rs.getInt("id_equipamento")));
            }

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo READ | Select - CRUD, mas baseado no Id do equipamento
    public ArrayList<Departamento_Equipamento> buscarPorIdEquipamento(int idEquipamento){

        // Criando a Conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Departamento_Equipamento> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM depart_equipamento WHERE id_equipamento = ? ORDER BY id_departamento");

            pstmt.setInt(1, idEquipamento);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                vet.add(new Departamento_Equipamento(rs.getInt("id_departamento"), rs.getInt("id_equipamento")));
            }

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Update - CRUD
    public int alterarDepartamentoEquipamento(int idDepartamentoAntigo, int idEquipamentoAntigo, Departamento_Equipamento dpE) {

        // Criando a conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("UPDATE depart_equipamento SET id_departamento = ?, id_equipamento = ? WHERE id_departamento = ? AND id_equipamento = ?");

            pstmt.setInt(1, dpE.getIdDepartamento());
            pstmt.setInt(2, dpE.getIdEquipamento());
            pstmt.setInt(3, idDepartamentoAntigo);
            pstmt.setInt(4, idEquipamentoAntigo);

            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo DELETE - CRUD
    public int remover(int idDepartamento, int idEquipamento){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM depart_equipamento WHERE id_departamento = ? AND id_equipamento = ?");

            pstmt.setInt(1, idDepartamento);
            pstmt.setInt(2, idEquipamento);

            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }
}