package org.astro.astro_app.model;


import java.time.LocalDate;

public class Certificado {

    private int idCertificado;
    private int idFuncionario;
    private int idNrFuncionario;

    private LocalDate dtEmissao;
    private LocalDate dtValidade;

    public Certificado(int idCertificado, int idFuncionario, int idNrFuncionario, LocalDate dtEmissao, LocalDate dtValidade) {
        this.idCertificado = idCertificado;
        this.idFuncionario = idFuncionario;
        this.idNrFuncionario = idNrFuncionario;
        this.dtEmissao = dtEmissao;
        this.dtValidade = dtValidade;
    }

    public int getIdCertificado() {
        return idCertificado;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public int getIdNrFuncionario() {
        return idNrFuncionario;
    }

    public LocalDate getDtEmissao() {
        return dtEmissao;
    }

    public LocalDate getDtValidade() {
        return dtValidade;
    }

}
