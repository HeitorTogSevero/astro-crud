package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao; //Import da classe Conexao
import org.astro.astro_app.model.Cronograma; //Import da classe Cronograma

import java.sql.*; //Import das classes java.sql
import java.util.ArrayList; //Import da classe ArrayList

// Classe DAO Cronograma - CRUD:
public class CronogramaDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Cronograma cronograma){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO cronograma (agendamento, id_empresa, id_funcionario) VALUES (?, ?, ?)");

            pstmt.setDate(1, cronograma.getAgendamento());
            pstmt.setInt(2, cronograma.getIdEmpresa());
            pstmt.setInt(3, cronograma.getIdFuncionario());

            // Executa o comando inserir e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        //Mensagem de erro da conexão
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Metodo Read | Select - CRUD
    public ArrayList<Cronograma> buscar(String sql){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Cronograma> vet = new ArrayList<>();

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new Cronograma(rs.getInt("id_funcionario"), rs.getInt("id_empresa"), rs.getInt("id_cronograma"), rs.getDate("agendamento")));
            }

            stmt.close();

        //Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do cronograma
    public Cronograma buscarPorIdCronograma(int idCronograma){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cronograma WHERE id_cronograma = ?");

            pstmt.setInt(1, idCronograma);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new Cronograma(rs.getInt("id_funcionario"), rs.getInt("id_empresa"), rs.getInt("id_cronograma"), rs.getDate("agendamento"));
            }
            return null;

        //Mensagem de erro da conexão
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Metodo Read | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<Cronograma> buscarPorIdEmpresa(int idEmpresa){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Cronograma> vet = new ArrayList<>();

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cronograma WHERE id_empresa = ? ORDER BY id_cronograma");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Cronograma(rs.getInt("id_funcionario"), rs.getInt("id_empresa"), rs.getInt("id_cronograma"), rs.getDate("agendamento")));
            }

        //Mensagem de erro da conexão
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id do funcionario
    public ArrayList<Cronograma> buscarPorIdFuncionario(int idFuncionario){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Cronograma> vet = new ArrayList<>();

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cronograma WHERE id_funcionario = ? ORDER BY id_cronograma");

            pstmt.setInt(1, idFuncionario);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Cronograma(rs.getInt("id_funcionario"), rs.getInt("id_empresa"), rs.getInt("id_cronograma"), rs.getDate("agendamento")));
            }

        //Mensagem de erro da conexão
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Metodo Update - CRUD
    public int alterarCronograma(Cronograma cronograma){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE cronograma set agendamento = ?, id_empresa = ?, id_funcionario = ? WHERE id_cronograma = ?");

            pstmt.setDate(1, cronograma.getAgendamento());
            pstmt.setInt(2, cronograma.getIdEmpresa());
            pstmt.setInt(3, cronograma.getIdFuncionario());
            pstmt.setInt(4, cronograma.getIdCronograma());

            // Executa o comando alterar e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        //Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Metodo Delete | Remove - CRUD
    public int remover(int idCronograma){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM cronograma WHERE id_cronograma = ?");

            pstmt.setInt(1, idCronograma);

            // Executa o comando DELETE e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        //Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}