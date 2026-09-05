package org.astro.astro_app.model;

import java.sql.Date;

//Classe model Alerta
public class Alerta {
    //Declaração de variáveis

    //Atributos da classe
    private int codigo;
    private Date dtLimite;
    private String descricao;


    //Metodo construtor da classe
    public Alerta(int codigo, Date dtLimite, String descricao) {
        this.codigo = codigo;
        this.dtLimite = dtLimite;
        this.descricao = descricao;
    }
    //Métodos Getters
    public int getCodigo() {
        return codigo;
    }

    public Date getDtLimite() {
        return dtLimite;
    }

    public String getDescricao() {
        return descricao;
    }

}