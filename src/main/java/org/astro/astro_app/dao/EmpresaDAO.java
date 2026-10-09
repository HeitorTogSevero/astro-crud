package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Empresa;

import java.sql.*;
import java.util.ArrayList;

// Classe DAO Empresa - CRUD:
public class EmpresaDAO {

    // Metodo Create | Insert - CRUD
    public boolean inserir(Empresa empresa){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

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
    public ArrayList<Empresa> buscar(String sql){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList<Empresa> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                vet.add(new Empresa(rs.getInt("id_empresa"), rs.getString("cnae"), rs.getString("nome"), rs.getString("cep"), rs.getString("cnpj"), rs.getString("rua"), rs.getString("estado"), rs.getString("bairro"), rs.getString("cidade"), rs.getDate("dt_registro")));
            }

            stmt.close();

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }finally {
            conexao.desconectar(conn);
        }

        return vet;
    }

    // Metodo Read | Select - CRUD, mas baseado no Id da empresa
    public Empresa buscarPorIdEmpresa(int idEmpresa){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM empresa WHERE id_empresa = ?");

            pstmt.setInt(1, idEmpresa);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Empresa(rs.getInt("id_empresa"), rs.getString("cnae"), rs.getString("nome"), rs.getString("cep"), rs.getString("cnpj"), rs.getString("rua"), rs.getString("estado"), rs.getString("bairro"), rs.getString("cidade"), rs.getDate("dt_registro"));
            }
            return null;

        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }

    // Metodo Update - CRUD
    public int alterarEmpresa(Empresa empresa){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

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

    // Metodo Delete | Remove - CRUD
    public int remover(int idEmpresa){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM empresa WHERE id_empresa = ?");

            pstmt.setInt(1, idEmpresa);

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