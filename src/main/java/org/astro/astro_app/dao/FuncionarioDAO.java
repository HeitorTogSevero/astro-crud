package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao; //Import da classe Conexao
import org.astro.astro_app.model.Funcionario; //Import da classe Funcionario

import java.sql.*; //Import da classe java.sql
import java.util.ArrayList; //Import da classe ArrayList

// Classe DAO Funcionario - CRUD:
public class FuncionarioDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Funcionario f){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO funcionario (nome, cargo, cpf, id_empresa, id_departamento, email) VALUES (?, ?, pgp_sym_encrypt(?, 'funcionariosAstro'), ?, ?, ?)");

            pstmt.setString(1, f.getNome());
            pstmt.setString(2, f.getCargo());
            pstmt.setString(3, f.getCpf());
            pstmt.setInt(4, f.getIdEmpresa());
            pstmt.setInt(5, f.getIdDepartamento());
            pstmt.setString(6, f.getEmail());

            // Executa o comando inserir e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        //Mensagem de erro da conexão
        }catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }


    // Metodo Read | Select - CRUD
    public ArrayList<Funcionario> buscar(String sql) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Funcionario> vet = new ArrayList<>();

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                vet.add(new Funcionario(rs.getString("nome"), rs.getString("cargo"), rs.getString("cpf"), rs.getString("email"), rs.getInt("id_empresa"), rs.getInt("id_funcionario"), rs.getInt("id_departamento")));
            }

            stmt.close();

        //Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }


    // Metodo Read | Select - CRUD, mas baseado no Id do funcionario
    public Funcionario buscarPorIdFunc(int idFunc) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT id_funcionario, nome, cargo, email, id_empresa, id_departamento, pgp_sym_decrypt(cpf, 'funcionariosAstro') as cpf FROM funcionario WHERE id_funcionario = ?");

            pstmt.setInt(1, idFunc);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new Funcionario(rs.getString("nome"), rs.getString("cargo"), rs.getString("cpf"), rs.getString("email"), rs.getInt("id_empresa"), rs.getInt("id_funcionario"), rs.getInt("id_departamento"));
            }

            return null;

        //Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }


    // Metodo Read | Select - CRUD, mas baseado no Id da empresa
    public ArrayList<Funcionario> buscarPorIdEmpresa(int idEmpresa) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Funcionario> vet = new ArrayList<>();

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT id_funcionario, nome, cargo, email, id_empresa, id_departamento, pgp_sym_decrypt(cpf, 'funcionariosAstro') as cpf FROM funcionario WHERE id_empresa = ? ORDER BY id_funcionario");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Funcionario(rs.getString("nome"), rs.getString("cargo"), rs.getString("cpf"), rs.getString("email"), rs.getInt("id_empresa"), rs.getInt("id_funcionario"), rs.getInt("id_departamento")));
            }

        //Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());

        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }


    // Metodo Read | Select - CRUD, mas baseado no Id do departamento
    public ArrayList<Funcionario> buscarPorIdDepart(int idDepart) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Funcionario> vet = new ArrayList<>();

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT id_funcionario, nome, cargo, email, id_empresa, id_departamento, pgp_sym_decrypt(cpf, 'funcionariosAstro') AS cpf FROM funcionario WHERE id_departamento = ? ORDER BY id_funcionario");

            pstmt.setInt(1, idDepart);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vet.add(new Funcionario(rs.getString("nome"), rs.getString("cargo"), rs.getString("cpf"), rs.getString("email"), rs.getInt("id_empresa"), rs.getInt("id_funcionario"), rs.getInt("id_departamento")));
            }

        //Mensagem de erro da conexão
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }


    //metodo contar funcionarios
    public int contarFuncionarios(){

        int total = 0;

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn  = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id_funcionario) FROM funcionario");

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando contar e verifica se alguma linha foi afetada
            if(rs.next()){
                total = rs.getInt(1);
            }

        //Mensagem de erro da conexão
        }catch (SQLException e ){
            System.out.println("Erro ao contar funcionarios: " + e.getMessage());
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
        return total;
    }


    // Metodo Update - CRUD
    public int alterarFuncionario(Funcionario f) {

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try {
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE funcionario SET nome = ?, cargo = ?, cpf = pgp_sym_encrypt(?, 'funcionariosAstro'), id_empresa = ?, id_departamento = ? WHERE id_funcionario = ?");

            pstmt.setString(1, f.getNome());
            pstmt.setString(2, f.getCargo());
            pstmt.setString(3, f.getCpf());
            pstmt.setInt(4, f.getIdEmpresa());
            pstmt.setInt(5, f.getIdDepartamento());
            pstmt.setInt(6, f.getIdFuncionario());

            // Executa o comando alterar e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0) {
                return 1;
            }
            return 0;

        //Mensagem de erro da conexão
        }catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }


    // Metodo Delete | Remove - CRUD
    public int remover(int idFuncionario){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        //Tentando a conexão
        try{
            conn = conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM funcionario WHERE id_funcionario = ?");

            pstmt.setInt(1, idFuncionario);

            // Executa o comando DELETE e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        //Mensagem de erro da conexão
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return -1;
        } finally {
            conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}