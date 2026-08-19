package org.astro.astro_app.model;

// Classe Model Risco
public class Risco {
    // Declaração de Variaveis:

    // Atributos da Classe
    private String risco;
    private String descricao;

    private int idEstrutura;

    // Método do COnstrutor da classe:
    public Risco(String risco, int idEstrutura, String descricao) {
        this.risco = risco;
        this.idEstrutura = idEstrutura;
        this.descricao = descricao;
    }

    // Métodos Getters:
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
