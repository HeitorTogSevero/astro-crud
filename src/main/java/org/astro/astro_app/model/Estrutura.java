package org.astro.astro_app.model;

// Classe Model Estrutura:
public class Estrutura {
    //Declaração de Variaveis:

    // Atributos da Classe:
    private int idEstrutura;
    private int idEmpresa;

    private String risco;
    private String descricao;

    // Metodo Construtor da classe:|


    public Estrutura(int idEstrutura, int idEmpresa, String risco, String descricao) {
        this.idEstrutura = idEstrutura;
        this.idEmpresa = idEmpresa;
        this.risco = risco;
        this.descricao = descricao;
    }

    // Métodos Getters:

    public int getIdEstrutura() {
        return idEstrutura;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getRisco() {
        return risco;
    }

    public String getDescricao() {
        return descricao;
    }
}
