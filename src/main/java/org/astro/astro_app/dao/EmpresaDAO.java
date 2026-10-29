package org.astro.astro_app.dao;

// Biblioteca:
import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Empresa;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Empresa - CRUD:
public class EmpresaDAO {

    // Método Create | Insert - CRUD
    public boolean inserir(Empresa empresa){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO empresa (nome, cnae, rua, cep, cidade, bairro, estado, cnpj, dt_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, empresa.getNome());
            pstmt.setString(2, empresa.getCnae());
            pstmt.setString(3, empresa.getRua());
            pstmt.setString(4, empresa.getCep());
            pstmt.setString(5, empresa.getCidade());
            pstmt.setString(6, empresa.getBairro());
            pstmt.setString(7, empresa.getEstado());
            pstmt.setString(8, empresa.getCnpj());
            pstmt.setDate(9, empresa.getDtRegistro());

            // Executa o comando inserir e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        // Mensagem de erro da conexão
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            return false;
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Read | Select - CRUD
    public ArrayList<Empresa> buscar(String sql){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        ArrayList<Empresa> vet = new ArrayList<>();

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new Empresa(rs.getInt("id_empresa"), rs.getString("cnae"), rs.getString("nome"), rs.getString("cep"), rs.getString("cnpj"), rs.getString("rua"), rs.getString("estado"), rs.getString("bairro"), rs.getString("cidade"), rs.getDate("dt_registro")));
            }

            stmt.close();

        // Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }

        return vet;
    }

    // Método Read | Select - CRUD, mas baseado no Id da empresa
    public Empresa buscarPorIdEmpresa(int idEmpresa){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM empresa WHERE id_empresa = ?");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando BUSCAR e verifica se alguma linha foi afetada
            if (rs.next()) {
                return new Empresa(rs.getInt("id_empresa"), rs.getString("cnae"), rs.getString("nome"), rs.getString("cep"), rs.getString("cnpj"), rs.getString("rua"), rs.getString("estado"), rs.getString("bairro"), rs.getString("cidade"), rs.getDate("dt_registro"));
            }
            return null;

        // Mensagem de erro da conexão
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return null;
        } finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método pra ver ultimas 3 empresas cadastradas
    public ArrayList<Empresa> listarUltimasTresEmpresas(){

        ArrayList<Empresa> empresas = new ArrayList<>();

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT id_empresa, cnpj, nome, cnae, bairro, cep, rua, estado, cidade, dt_registro FROM empresa ORDER BY dt_registro DESC LIMIT 3;");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                empresas.add(new Empresa(rs.getInt("id_empresa"), rs.getString("cnae"), rs.getString("nome"), rs.getString("cep"), rs.getString("cnpj"), rs.getString("rua"), rs.getString("estado"), rs.getString("bairro"), rs.getString("cidade"), rs.getDate("dt_registro")
                ));            }

        // Mensagem de erro da conexão
        }catch (SQLException e){
            System.out.println("erro ao listar as ultimas 3 empresas foi: " + e.getMessage());
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
        return empresas;
    }

    // Método de contar empresas, que retorna o total de empresas cadastradas no banco de dados
    public int contarEmpresas(){

        int total = 0;

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id_empresa) FROM empresa;");

            ResultSet rs = pstmt.executeQuery();

            // Executa o comando contar e verifica se alguma linha foi afetada
            if (rs.next()){
                total = rs.getInt(1);
            }

        // Mensagem de erro da conexão
        }catch (SQLException e){
            System.out.println("erro ao contar empresas: " + e.getMessage());
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
        return total;
    }

    // Método Update - CRUD
    public int alterarEmpresa(Empresa empresa){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's
            PreparedStatement pstmt = conn.prepareStatement("UPDATE empresa set cnpj = ?, nome = ?, cnae = ?, bairro = ?, cep = ?, rua = ?, estado = ?, cidade = ?, dt_registro = ? WHERE id_empresa = ?");

            pstmt.setString(1, empresa.getCnpj());
            pstmt.setString(2, empresa.getNome());
            pstmt.setString(3, empresa.getCnae());
            pstmt.setString(4, empresa.getBairro());
            pstmt.setString(5, empresa.getCep());
            pstmt.setString(6, empresa.getRua());
            pstmt.setString(7, empresa.getEstado());
            pstmt.setString(8, empresa.getCidade());
            pstmt.setDate(9, empresa.getDtRegistro());
            pstmt.setInt(10, empresa.getIdEmpresa());

            // Executa o comando alterar e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        // Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }

    // Método Delete | Remove - CRUD
    public int remover(int idEmpresa){

        // Criando a conexão com o Banco de Dados
        Connection conn = null;

        // Tentando a conexão
        try{
            conn = Conexao.conectar();

            // Interface para realizar comandos SQL's:
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM empresa WHERE id_empresa = ?");

            pstmt.setInt(1, idEmpresa);

            // Executa o comando DELETE e verifica se alguma linha foi afetada
            if (pstmt.executeUpdate() > 0){
                return 1;
            }
            return 0;

        // Mensagem de erro da conexão
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            Conexao.desconectar(conn); // Desconectando do Banco
        }
    }
}