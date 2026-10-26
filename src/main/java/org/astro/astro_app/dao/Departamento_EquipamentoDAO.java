package org.astro.astro_app.dao;

// Biblioteca:
import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Departamento_Equipamento;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Departamento_Equipamento - CRUD
public class Departamento_EquipamentoDAO {

    // Método Create | Insert - CRUD:
    public boolean inserir(Departamento_Equipamento dpE){

        // Criando a Conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO depart_equipamento (id_departamento, id_equipamento) VALUES (?, ?)");

            pstmt.setInt(1, dpE.getIdDepartamento());
            pstmt.setInt(2, dpE.getIdEquipamento());

            // Executa o comando INSERIR e verifica se alguma linha foi afetada
            if(pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Metodo READ | Select - CRUD
    public ArrayList<Departamento_Equipamento> buscar(String sql){

        // Criando a Conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Departamento_Equipamento> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                // ordem do construtor: (idDepartamento, idEquipamento)
                vet.add(new Departamento_Equipamento(rs.getInt("id_departamento"), rs.getInt("id_equipamento")));
            }

            stmt.close();

        // Mensagem de erro da conexão
        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método READ | Select - CRUD, mas baseado no Id do departamento
    public ArrayList<Departamento_Equipamento> buscarPorIdDepart(int idDepart){

        // Criando a Conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Departamento_Equipamento> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM depart_equipamento WHERE id_departamento = ? ORDER BY id_equipamento");

            pstmt.setInt(1, idDepart);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                vet.add(new Departamento_Equipamento(rs.getInt("id_departamento"), rs.getInt("id_equipamento")));
            }

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método READ | Select - CRUD, mas baseado no Id do equipamento
    public ArrayList<Departamento_Equipamento> buscarPorIdEquipamento(int idEquipamento){

        // Criando a Conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Departamento_Equipamento> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM depart_equipamento WHERE id_equipamento = ? ORDER BY id_departamento");

            pstmt.setInt(1, idEquipamento);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                vet.add(new Departamento_Equipamento(rs.getInt("id_departamento"), rs.getInt("id_equipamento")));
            }

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método Update - CRUD
    public int alterarDepartamentoEquipamento(int idDepartamentoAntigo, int idEquipamentoAntigo, Departamento_Equipamento dpE) {

        // Criando a conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

    // Tentando a conexão
        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("UPDATE depart_equipamento SET id_departamento = ?, id_equipamento = ? WHERE id_departamento = ? AND id_equipamento = ?");

            pstmt.setInt(1, dpE.getIdDepartamento());
            pstmt.setInt(2, dpE.getIdEquipamento());
            pstmt.setInt(3, idDepartamentoAntigo);
            pstmt.setInt(4, idEquipamentoAntigo);

            // Executa o comando alterar e verifica se alguma linha foi afetada
            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método DELETE - CRUD
    public int remover(int idDepartamento, int idEquipamento){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM depart_equipamento WHERE id_departamento = ? AND id_equipamento = ?");

            pstmt.setInt(1, idDepartamento);
            pstmt.setInt(2, idEquipamento);

            // Executa o comando DELETE e verifica se alguma linha foi afetada
            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}