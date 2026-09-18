package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Empresa;

import java.sql.*;
import java.util.ArrayList;

public class EmpresaDAO {
    //metodo inssert
    public boolean inserir(Empresa empresa){

        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO empresa (nome, cnae, qt_funcionario, rua, cep, cidade, bairro, estado, cnpj) values (?,?,?,?,?,?,?,?,?)");

            pstmt.setString(1, empresa.getNome());
            pstmt.setInt(2, empresa.getCnae());
            pstmt.setInt(3, empresa.getQtdFuncionario());
            pstmt.setString(4, empresa.getRua());
            pstmt.setString(5, empresa.getCep());
            pstmt.setString(6, empresa.getCidade());
            pstmt.setString(7, empresa.getBairro());
            pstmt.setString(8, empresa.getEstado());
            pstmt.setString(9, empresa.getCnpj());

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

    //metodo read
    public ArrayList<Empresa> buscar(){

        Conexao conexao = new Conexao();
        Connection conn = null;

        ArrayList <Empresa> vet = new ArrayList<>();

        try{
            conn = conexao.conectar();

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM empresa ORDER BY 1");

            while(rs.next()){
                vet.add(new Empresa(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),  rs.getString(6),  rs.getString(7),  rs.getString(8),  rs.getString(9),  rs.getString(10)));
            }

            statement.close();
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }finally {
            conexao.desconectar(conn);
        }
        return vet;
    }

    //metodo read por id
    public ResultSet buscarPorIdEmpresa(int IdEmpresa){
        Conexao conexao = new Conexao();
        Connection conn = null;
        ResultSet rs = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM empresa WHERE id_empresa = ?");

            pstmt.setInt(1, IdEmpresa);
            rs = pstmt.executeQuery();
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        } finally {
            conexao.desconectar(conn);
            return rs;
        }
    }

    //metodo update
    public int alterarEmpresa(Empresa empresa){
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE empresa set cnpj = ? , nome = ? , qtd_funcionario = ? , cnae = ? , bairro = ? , cep = ? , rua = ? , estado = ? , cidade = ?");

            pstmt.setString(1, empresa.getCnpj());
            pstmt.setString(2, empresa.getNome());
            pstmt.setInt(3, empresa.getQtdFuncionario());
            pstmt.setInt(4, empresa.getCnae());
            pstmt.setString(5, empresa.getBairro());
            pstmt.setString(6, empresa.getCep());
            pstmt.setString(7, empresa.getRua());
            pstmt.setString(8, empresa.getEstado());
            pstmt.setString(9, empresa.getCidade());


            if (pstmt.executeUpdate() > 0){
                return 0;
            }
            return 1;

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return -1;
        }finally {
            conexao.desconectar(conn);
        }


    }

    //metodo delete
    public int remover(int id){
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM empresa WHERE id_empresa = ?");

            pstmt.setInt(1, id);

            if (pstmt.executeUpdate() == 0) {
                return 0;
            }
            return 1;
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
            return  -1;
        }finally {
            conexao.desconectar(conn);
        }

    }
}
