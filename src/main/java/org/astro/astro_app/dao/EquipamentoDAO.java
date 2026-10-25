package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao; //Import da classe Conexao
import org.astro.astro_app.model.Equipamento; //Import da classe Equipamento

import java.sql.*; //Import da classe java.sql
import java.util.ArrayList; //Import da classe ArrayList

// Classe DAO Equipamento - CRUD:
public class EquipamentoDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Equipamento equip){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

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

        //Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Metodo READ | Select - CRUD
    public ArrayList<Equipamento> buscar(String sql){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Equipamento> vet = new ArrayList<>();

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new Equipamento(rs.getInt("id_equipamento"), rs.getDate("dt_validade"), rs.getString("nome"), rs.getString("classificacao_gov")));
            }

            stmt.close();

        //Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally{
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Metodo READ | Select - CRUD, mas baseado no Id do equipamento
    public Equipamento buscarPorIdEquipamento(int idEquip){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM equipamento WHERE id_equipamento = ?");

            pstmt.setInt(1, idEquip);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new Equipamento(rs.getInt("id_equipamento"), rs.getDate("dt_validade"), rs.getString("nome"), rs.getString("classificacao_gov"));
            }
            return null;

        //Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return null;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Metodo Update - CRUD
    public int alterarEquipamento(Equipamento equip) {

        // Criando a conexão com o Banco de Dados:
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try {
            conn = conexao.conectar();

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

        //Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Metodo DELETE - CRUD
    public int remover(int idEquipamento){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM equipamento WHERE id_equipamento = ?");

            pstmt.setInt(1, idEquipamento);

            // Executa o comando DELETE e verifica se alguma linha foi afetada
            if(pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        //Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}