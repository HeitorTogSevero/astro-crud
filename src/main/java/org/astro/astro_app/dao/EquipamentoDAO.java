package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Equipamento;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Equipamento - CRUD:
public class EquipamentoDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Equipamento equip){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO equipamento (dt_validade, nome, classificacao_gov) VALUES (?, ?, ?)");

            pstmt.setDate(1, equip.getDtValidade());
            pstmt.setString(2, equip.getNome());
            pstmt.setString(3, equip.getClasificacaoGov());

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
    public ArrayList<Equipamento> buscar(String sql){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Equipamento> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new Equipamento(rs.getInt("id_equipamento"), rs.getDate("dt_validade"), rs.getString("nome"), rs.getString("classificacao_gov")));
            }

            stmt.close();

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally{
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo READ | Select - CRUD, mas baseado no Id do equipamento
    public Equipamento buscarPorIdEquipamento(int idEquip){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM equipamento WHERE id_equipamento = ?");

            pstmt.setInt(1, idEquip);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Equipamento(rs.getInt("id_equipamento"), rs.getDate("dt_validade"), rs.getString("nome"), rs.getString("classificacao_gov"));
            }
            return null;

        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return null;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Update - CRUD
    public int alterarEquipamento(Equipamento equip) {

        // Criando a conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        try {
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE equipamento SET dt_validade = ?, nome = ?, classificacao_gov = ? WHERE id_equipamento = ?");

            pstmt.setDate(1, equip.getDtValidade());
            pstmt.setString(2, equip.getNome());
            pstmt.setString(3, equip.getClasificacaoGov());
            pstmt.setInt(4, equip.getIdEquipamento());

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
    public int remover(int idEquipamento){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM equipamento WHERE id_equipamento = ?");

            pstmt.setInt(1, idEquipamento);

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