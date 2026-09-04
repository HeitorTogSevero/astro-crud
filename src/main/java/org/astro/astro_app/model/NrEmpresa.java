package org.astro.astro_app.model;

import java.sql.Date;

//Classe model NrEmpresa
public class NrEmpresa {
    //Declaração de variáveis

    //Atributos da classe
    private int idNremp;
    private int idNrDescricao;
    private int numero;

    private String titulo;
    private String status;

    private Date dtRealização;

    //Metodo construtor da classe
    public NrEmpresa(int idNremp, int idNrDescricao, int numero, String titulo, String status, Date dtRealização) {
        this.idNremp = idNremp;
        this.idNrDescricao = idNrDescricao;
        this.numero = numero;
        this.titulo = titulo;
        this.status = status;
        this.dtRealização = dtRealização;
    }

    //Metodos Getters
    public int getIdNremp() {
        return idNremp;
    }

    public int getIdNrDescricao() {
        return idNrDescricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getStatus() {
        return status;
    }

    public int getNumero(){return numero;}

    public Date getDtRealização(){return  dtRealização;}
}