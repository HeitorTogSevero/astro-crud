package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao; //Import da classe Conexao
import org.astro.astro_app.model.Estrutura; //Import da classe Estrutara

import java.sql.*; //Import da classe java.sql
import java.util.ArrayList; //Import da classe ArrayList

// Classe DAO Estrutura - CRUD:
public class EstruturaDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Estrutura est){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO estrutura (id_empresa, risco, descricao) VALUES (?, ?, ?)");

            pstmt.setInt(1, est.getIdEmpresa());
            pstmt.setString(2, est.getRisco());
            pstmt.setString(3, est.getDescricao());

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
    public ArrayList<Estrutura> buscar(String sql){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Estrutura> vet = new ArrayList<>();

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new Estrutura(rs.getInt("id_estrutura"), rs.getInt("id_empresa"), rs.getString("risco"), rs.getString("descricao")));
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

    // Metodo READ | Select - CRUD, mas baseado no Id da estrutura
    public Estrutura buscarPorIdEstrutura(int idEstrutura){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM estrutura WHERE id_estrutura = ?");

            pstmt.setInt(1, idEstrutura);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new Estrutura(rs.getInt("id_estrutura"), rs.getInt("id_empresa"), rs.getString("risco"), rs.getString("descricao"));
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

    // Metodo READ | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<Estrutura> buscarPorIdEmpresa(int idEmpresa){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Estrutura> vet = new ArrayList<>();

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM estrutura WHERE id_empresa = ? ORDER BY id_estrutura");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Estrutura(rs.getInt("id_estrutura"), rs.getInt("id_empresa"), rs.getString("risco"), rs.getString("descricao")));
            }

        //Mensagem de erro da conexão
        }catch(SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Metodo Update - CRUD
    public int alterarEstrutura(Estrutura est) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE estrutura SET id_empresa = ?, risco = ?, descricao = ? WHERE id_estrutura = ?");

            pstmt.setInt(1, est.getIdEmpresa());
            pstmt.setString(2, est.getRisco());
            pstmt.setString(3, est.getDescricao());
            pstmt.setInt(4, est.getIdEstrutura());

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
    public int remover(int idEstrutura){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM estrutura WHERE id_estrutura = ?");

            pstmt.setInt(1, idEstrutura);

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