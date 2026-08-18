package org.astro.astro_app.model;
public class Alerta {
    private int codigo;
    private int dtLimite;
    private String descricao;
    private int idEmpresa;

    public Alerta(int codigo, int dtLimite, String descricao, int idEmpresa) {
        this.codigo = codigo;
        this.dtLimite = dtLimite;
        this.descricao = descricao;
        this.idEmpresa = idEmpresa;
    }

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