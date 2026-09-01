package org.astro.astro_app.model;

import java.sql.Date;

// Classe Model Equipamento
public class Equipamento {
    // Declaração de Variaveis:

    // Atributos da Classe:
    private int idEquipamento;
    private long cnpj;

    private Date dtValidade;

    private String nome;
    private String clasificacaoGov;

    // Metodo do Construtor da classe:
    public Equipamento(int idEquipamento, long cnpj, Date dtValidade, String nome, String clasificacaoGov) {
        this.idEquipamento = idEquipamento;
        this.cnpj = cnpj;
        this.dtValidade = dtValidade;
        this.nome = nome;
        this.clasificacaoGov = clasificacaoGov;
    }

    //Métodos Getters:

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public long getCnpj() {
        return cnpj;
    }

    public Date getDtValidade() {
        return dtValidade;
    }

    public String getNome() {
        return nome;
    }

    public String getClasificacaoGov() {
        return clasificacaoGov;
    }

}
