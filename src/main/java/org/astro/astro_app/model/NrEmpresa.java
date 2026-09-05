package org.astro.astro_app.model;

import java.sql.Date;

//Classe model NrEmpresa
public class NrEmpresa {
    //Declaração de variáveis

    //Atributos da classe
    private int idNrEmpresa;
    private int idNrDescricao;
    private int numero;

    private String titulo;
    private String status;

    private Date dtRealização;

    //Metodo construtor da classe
    public NrEmpresa(int idNrEmpresa, int idNrDescricao, int numero, String titulo, String status, Date dtRealização) {
        this.idNrEmpresa = idNrEmpresa;
        this.idNrDescricao = idNrDescricao;
        this.numero = numero;
        this.titulo = titulo;
        this.status = status;
        this.dtRealização = dtRealização;
    }

    //Metodos Getters
    public int getIdNremp() {
        return idNrEmpresa;
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