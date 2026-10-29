package org.astro.astro_app.dao;

// Biblioteca:
import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Equipamento;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Equipamento - CRUD:
public class EquipamentoDAO {

    // Método Create | Insert - CRUD
    public boolean inserir(Equipamento equip){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO equipamento (dt_validade, nome, classificacao_gov) VALUES (?, ?, ?)");

            pstmt.setDate(1, equip.getDtValidade());
            pstmt.setString(2, equip.getNome());
            pstmt.setString(3, equip.getClasificacaoGov());

            // Executa o comando inserir e verifica se alguma linha foi afetada
            if(pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método READ | Select - CRUD
    public ArrayList<Equipamento> buscar(String sql){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        ArrayList<Equipamento> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new Equipamento(rs.getInt("id_equipamento"), rs.getDate("dt_validade"), rs.getString("nome"), rs.getString("classificacao_gov")));
            }

            stmt.close();

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally{
            Conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método READ | Select - CRUD, mas baseado no Id do equipamento
    public Equipamento buscarPorIdEquipamento(int idEquip){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM equipamento WHERE id_equipamento = ?");

            pstmt.setInt(1, idEquip);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new Equipamento(rs.getInt("id_equipamento"), rs.getDate("dt_validade"), rs.getString("nome"), rs.getString("classificacao_gov"));
            }
            return null;

        // Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return null;
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Update - CRUD
    public int alterarEquipamento(Equipamento equip) {

        // Criando a conexão com o Banco de Dados:
        Connection conn = null;

        // Tentando a conexão
        try {
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE equipamento SET dt_validade = ?, nome = ?, classificacao_gov = ? WHERE id_equipamento = ?");

            pstmt.setDate(1, equip.getDtValidade());
            pstmt.setString(2, equip.getNome());
            pstmt.setString(3, equip.getClasificacaoGov());
            pstmt.setInt(4, equip.getIdEquipamento());

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
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método DELETE - CRUD
    public int remover(int idEquipamento){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM equipamento WHERE id_equipamento = ?");

            pstmt.setInt(1, idEquipamento);

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
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}