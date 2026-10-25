package org.astro.astro_app.model;

import java.sql.Date;

//Classe model NrEmpresa
public class NrEmpresa {

    //Atributos Inteiros
    private int idNrEmpresa;
    private int idNrDescricao;
    private int numero;

    // Atributos String
    private String titulo;
    private String status;

    // Atributos Date
    private Date dtRealização;

    // Construtor completo para inicializar uma NrEmpresa
    public NrEmpresa(int idNrEmpresa, int idNrDescricao, int numero, String titulo, String status, Date dtRealização) {
        this.idNrEmpresa = idNrEmpresa;
        this.idNrDescricao = idNrDescricao;
        this.numero = numero;
        this.titulo = titulo;
        this.status = status;
        this.dtRealização = dtRealização;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public NrEmpresa(){}

    // Construtor sem o ID
    public NrEmpresa(int idNrDescricao, int numero, String titulo, String status, Date dtRealização) {
        this.idNrDescricao = idNrDescricao;
        this.numero = numero;
        this.titulo = titulo;
        this.status = status;
        this.dtRealização = dtRealização;
    }

    // Getter

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