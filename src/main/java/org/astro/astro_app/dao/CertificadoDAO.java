package org.astro.astro_app.dao;

import org.astro.astro_app.Conexão.Conexao;
import org.astro.astro_app.model.Certificado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


// Classe DAO Certificado - CRUD:
public class CertificadoDAO {

//    Metodo Create ou Insert:
    public static boolean inserir(Certificado c){

        // Criando a conexão com o Banco de Dados
        Conexao conexao = new Conexao();
        Connection conn = null;

        try{
            conn = conexao.conectar();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Certificado (id_funcionario, id_nrfunc, dt_emissao, validade) VALUES (?, ?, ?, ? )");

            pstmt.setInt(1, c.getIdFuncionario());
            pstmt.setInt(2, c.getIdNrFuncionario());
            pstmt.setDate(3, c.getDtEmissao());
            pstmt.setDate(4, c.getDtValidade());

            // Verificação para saber se o INSERT funcionou
            if (pstmt.executeUpdate() > 0){
                return true;
            }
            return false;

        }catch (SQLException sqlE){
            System.out.println("Mensagem de Erro: "+ sqlE.getMessage());
            return false;
        }finally {
            conexao.desconectar(conn); // desconectando a Conexão
        }
    }




}
