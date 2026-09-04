package org.astro.astro_app.model;

import java.sql.Date;

//Classe model Alerta
public class Alerta {
    //Declaração de variáveis

    //Atributos da classe
    private int codigo;
    private int idAlerta;
    private int idEmpresa;

    private String descricao;

    private Date dtLimite;


    //Metodo construtor da classe
    public Alerta(int codigo, int idAlerta, int idEmpresa, String descricao, Date dtLimite) {
        this.codigo = codigo;
        this.idAlerta = idAlerta;
        this.idEmpresa = idEmpresa;
        this.descricao = descricao;
        this.dtLimite = dtLimite;
    }

    //Métodos Getters
    public int getCodigo() {
        return codigo;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDtLimite() {
        return dtLimite;
    }
}