package org.astro.astro_app.model;

public class Risco {
    private String risco;
    private String descricao;

    private int idEstrutura;


    public Risco(String risco, int idEstrutura, String descricao) {
        this.risco = risco;
        this.idEstrutura = idEstrutura;
        this.descricao = descricao;
    }

    public String getRisco() {
        return risco;
    }

    public int getIdEstrutura() {
        return idEstrutura;
    }

    public String getDescricao() {
        return descricao;
    }

}
