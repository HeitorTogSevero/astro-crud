package org.astro.astro_app.model;

import java.sql.Date;

// Classe Model Equipamento
public class Equipamento {

    // Atributos Inteiros
    private int idEquipamento;

    // Atributos Date
    private Date dtValidade;

    // Atributos String
    private String nome;
    private String clasificacaoGov;

    // Construtor completo para inicializar um Equipamento
    public Equipamento(int idEquipamento, Date dtValidade, String nome, String clasificacaoGov) {
        this.idEquipamento = idEquipamento;
        this.dtValidade = dtValidade;
        this.nome = nome;
        this.clasificacaoGov = clasificacaoGov;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public Equipamento(){}

    // Construtor sem o ID
    public Equipamento(Date dtValidade, String nome, String clasificacaoGov) {
        this.dtValidade = dtValidade;
        this.nome = nome;
        this.clasificacaoGov = clasificacaoGov;
    }

    //Getters

    public int getIdEquipamento() {
        return idEquipamento;
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
