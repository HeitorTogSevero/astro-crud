package org.astro.astro_app.model;

import java.time.LocalDate;

public class Equipamento {

    private int idEquipamento;
    private long cnpj;

    private LocalDate dtValidade;

    private String nome;
    private String clasificacaoGov;

    public Equipamento(int idEquipamento, long cnpj, LocalDate dtValidade, String nome, String clasificacaoGov) {
        this.idEquipamento = idEquipamento;
        this.cnpj = cnpj;
        this.dtValidade = dtValidade;
        this.nome = nome;
        this.clasificacaoGov = clasificacaoGov;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public long getCnpj() {
        return cnpj;
    }

    public LocalDate getDtValidade() {
        return dtValidade;
    }

    public String getNome() {
        return nome;
    }

    public String getClasificacaoGov() {
        return clasificacaoGov;
    }

}
