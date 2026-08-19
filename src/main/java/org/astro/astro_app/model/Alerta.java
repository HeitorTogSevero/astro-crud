package org.astro.astro_app.model;

import java.time.LocalDate;

public class Alerta {
    private int codigo;
    private LocalDate dtLimite;
    private String descricao;
    private int idEmpresa;

    public Alerta(int codigo, LocalDate dtLimite, String descricao, int idEmpresa) {
        this.codigo = codigo;
        this.dtLimite = dtLimite;
        this.descricao = descricao;
        this.idEmpresa = idEmpresa;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDate getDtLimite() {
        return dtLimite;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }
}