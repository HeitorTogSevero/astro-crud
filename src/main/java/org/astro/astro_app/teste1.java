package org.astro.astro_app;

import org.astro.astro_app.dao.AlertaDAO;
import org.astro.astro_app.dao.CertificadoDAO;
import org.astro.astro_app.model.Alerta;
import org.astro.astro_app.model.Certificado;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        AlertaDAO  dao= new AlertaDAO();
        ArrayList<Alerta> vet = new ArrayList<>();
        vet = dao.buscar();

        dao.remover(1);

        for (int i = 0; i < vet.size(); i++) {
            System.out.printf("codigo: %d \t | dataLimite: %s \t | descricao: %s \t \n",vet.get(i).getCodigo(), vet.get(i).getDtLimite(), vet.get(i).getDescricao());
        }



    }
}