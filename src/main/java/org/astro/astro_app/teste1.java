package org.astro.astro_app;

import org.astro.astro_app.dao.CertificadoDAO;
import org.astro.astro_app.model.Certificado;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        CertificadoDAO dao = new CertificadoDAO();

////        Teste Insert
//        System.out.print("Me fale seu Id: ");
//        int idFuncionario = input.nextInt();
//
//        System.out.print("Me fale seu Id de NR do Funcionario: ");
//        int idNrFuncionario = input.nextInt();
//
//        System.out.print("Me fale a data de emissão(yyyy-mm-dd): ");
//        String dt_emissao = input.next();
//
//        System.out.print("Me fale a data de validade(yyyy-mm-dd): ");
//        String validade = input.next();
//
//
//        if (dao.inserir(new Certificado(idFuncionario, idNrFuncionario, Date.valueOf(dt_emissao), Date.valueOf(validade))) == true) {
//            System.out.println("Os dados foram inseridos com sucesso");
//        } else {
//            System.out.println("Os dados não foram inseridos no banco");
//      }

//        Teste buscar:
        ArrayList<Certificado> rs = dao.buscar();
        for (int i = 0; i < rs.size(); i++) {
            System.out.printf("\nIdC: %d\t| IdF: %d\t| IdNrF: %d\t| Emissão: %s\t| Valid: %s", rs.get(i).getIdCertificado(), rs.get(i).getIdFuncionario(), rs.get(i).getIdNrFuncionario(), rs.get(i).getDtEmissao(), rs.get(i).getDtValidade());
        }
        System.out.println();
        System.out.println();

////        Teste Buscar por ID:
//        ResultSet rsa = dao.buscarPorId(1);
//        try {
//            if (rsa != null) {
//                while (rsa.next()) {
//        System.out.printf("Id Certi: %d\t| ID fUNC: %d\t| Id Nr_func: %d\t| Emissao: %s\t| Validade: %s\t|", rsa.getInt(1), rsa.getInt(2), rsa.getInt(3), rsa.getDate(4), rsa.getDate(5));
//                }
//            } else {
//                System.out.println("Erro ao mostrar dados");
//            }
//        } catch (SQLException sql) {
//            System.out.println("Erro ao mostrar o select");
//        }

    }
}