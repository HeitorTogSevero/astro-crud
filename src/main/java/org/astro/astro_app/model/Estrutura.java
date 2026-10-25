package org.astro.astro_app.model;

// Classe Model Estrutura:
public class Estrutura {

    // Atributos Inteiros
    private int idEstrutura;
    private int idEmpresa;

    // Atributos String
    private String risco;
    private String descricao;

    // Construtor completo para inicializar uma Estrutura
    public Estrutura(int idEstrutura, int idEmpresa, String risco, String descricao) {
        this.idEstrutura = idEstrutura;
        this.idEmpresa = idEmpresa;
        this.risco = risco;
        this.descricao = descricao;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public Estrutura(){}

    // Construtor sem o ID
    public Estrutura(int idEmpresa, String risco, String descricao) {
        this.idEmpresa = idEmpresa;
        this.risco = risco;
        this.descricao = descricao;
    }

    // Getters

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
