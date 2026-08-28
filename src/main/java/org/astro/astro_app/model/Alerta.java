package org.astro.astro_app.model;

//Classe model Alerta
public class Alerta {
    //Declaração de variáveis

    //Atributos da classe
    private int codigo;
    private int dtLimite;
    private String descricao;
    private int idEmpresa;

    //Metodo construtor da classe
    public Alerta(int codigo, int dtLimite, String descricao, int idEmpresa) {
        this.codigo = codigo;
        this.dtLimite = dtLimite;
        this.descricao = descricao;
        this.idEmpresa = idEmpresa;
    }
    //Métodos Getters
    public int getCodigo() {
        return codigo;
    }

    public int getDtLimite() {
        return dtLimite;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }
}