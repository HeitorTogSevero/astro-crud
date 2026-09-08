package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Equipamento;

import java.sql.*;
import java.util.ArrayList;

public class EquipamentoDAO {

//    Metodo CREATE | Insert - CRUD
    public boolean inserir(Equipamento equip){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn =conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Equipamento (dt_validade, nome, classificacao_gov) VALUES (?, ?, ?)");

            pstmt.setDate(1, equip.getDtValidade());
            pstmt.setString(2, equip.getNome());
            pstmt.setString(3, equip.getClasificacaoGov());

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
    public ArrayList<Equipamento> buscar(){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;
        ArrayList<Equipamento> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement pstmt = conn.createStatement();

            ResultSet rs = pstmt.executeQuery("SELECT * FROM Equipamento ORDER BY 1");

            while(rs.next()){
                vet.add(new Equipamento(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4)));
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Equipamento WHERE id_equipamento = ?");

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
    public int alterarEmail(Equipamento equip) {

        // Criando a conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Equipamento SET id_equipamento = ?, dt_valiadde = ?, nome = ?, classificacao_gov = ? WHERE id_equipamento = ?");

            pstmt.setInt(1, equip.getIdEquipamento());
            pstmt.setDate(2, equip.getDtValidade());
            pstmt.setString(3, equip.getNome());
            pstmt.setString(4, equip.getClasificacaoGov());
            pstmt.setInt(5, equip.getIdEquipamento());


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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Equipamento WHERE  id_equipamento = ?");

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
