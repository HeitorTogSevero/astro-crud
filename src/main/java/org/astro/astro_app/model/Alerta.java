package org.astro.astro_app.model;

import java.sql.Date;

public class Alerta {
    private int idAlerta;
    private int codigo;
    private int idEmpresa;

    private String descricao;

    private Date dtLimite;

    public Alerta(int idAlerta, int codigo, int idEmpresa, String descricao, Date dtLimite) {
        this.idAlerta = idAlerta;
        this.codigo = codigo;
        this.idEmpresa = idEmpresa;
        this.descricao = descricao;
        this.dtLimite = dtLimite;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public int getCodigo() {
        return codigo;
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
