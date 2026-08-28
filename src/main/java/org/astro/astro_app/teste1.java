package org.astro.astro_app;

import org.astro.astro_app.dao.CertificadoDAO;
import org.astro.astro_app.model.Certificado;

import java.sql.Date;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Me fale seu Id: ");
        int idFuncionario = input.nextInt();

        System.out.print("Me fale seu Id de NR do Funcionario: ");
        int idNrFuncionario = input.nextInt();

        System.out.print("Me fale a data de emissão(yyyy-mm-dd): ");
        String dt_emissao = input.next();

        System.out.print("Me fale a data de validade(yyyy-mm-dd): ");
        String validade = input.next();


        if (CertificadoDAO.inserir(new Certificado(idFuncionario, idNrFuncionario, Date.valueOf(dt_emissao), Date.valueOf(validade))) == true) {
            System.out.println("Os dados foram inseridos com sucesso");
        } else {
            System.out.println("Os dados não foram inseridos no banco");
        }
    }
}