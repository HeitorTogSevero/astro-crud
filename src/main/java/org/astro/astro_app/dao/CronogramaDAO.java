package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Cronograma;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Cronograma - CRUD:
public class CronogramaDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Cronograma cronograma){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO cronograma (agendamento, id_empresa, id_funcionario) VALUES (?, ?, ?)");

            pstmt.setDate(1, cronograma.getAgendamento());
            pstmt.setInt(2, cronograma.getIdEmpresa());
            pstmt.setInt(3, cronograma.getIdFuncionario());

            if (pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Read | Select - CRUD
    public ArrayList<Cronograma> buscar(){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Cronograma> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cronograma ORDER BY id_cronograma");

            while(rs.next()){
                vet.add(new Cronograma(rs.getInt("id_funcionario"), rs.getInt("id_empresa"), rs.getInt("id_cronograma"), rs.getDate("agendamento")));
            }

            stmt.close();

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do cronograma
    public Cronograma buscarPorIdCronograma(int idCronograma){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cronograma WHERE id_cronograma = ?");

            pstmt.setInt(1, idCronograma);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Cronograma(rs.getInt("id_funcionario"), rs.getInt("id_empresa"), rs.getInt("id_cronograma"), rs.getDate("agendamento"));
            }
            return null;

        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Read | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<Cronograma> buscarPorIdEmpresa(int idEmpresa){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Cronograma> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cronograma WHERE id_empresa = ? ORDER BY id_cronograma");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Cronograma(rs.getInt("id_funcionario"), rs.getInt("id_empresa"), rs.getInt("id_cronograma"), rs.getDate("agendamento")));
            }

        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        } finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do funcionario
    public ArrayList<Cronograma> buscarPorIdFuncionario(int idFuncionario){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Cronograma> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cronograma WHERE id_funcionario = ? ORDER BY id_cronograma");

            pstmt.setInt(1, idFuncionario);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Cronograma(rs.getInt("id_funcionario"), rs.getInt("id_empresa"), rs.getInt("id_cronograma"), rs.getDate("agendamento")));
            }

        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        } finally {
            conexao.desconectar(conn); // desconectando do Banco
        }

        return vet;
    }

    // Metodo Update - CRUD
    public int alterarCronograma(Cronograma cronograma){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE cronograma set agendamento = ?, id_empresa = ?, id_funcionario = ? WHERE id_cronograma = ?");

            pstmt.setDate(1, cronograma.getAgendamento());
            pstmt.setInt(2, cronograma.getIdEmpresa());
            pstmt.setInt(3, cronograma.getIdFuncionario());
            pstmt.setInt(4, cronograma.getIdCronograma());

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // desconectando do Banco
        }
    }

    // Metodo Delete | Remove - CRUD
    public int remover(int idCronograma){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM cronograma WHERE id_cronograma = ?");

            pstmt.setInt(1, idCronograma);

            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }
    }
}