package org.astro.astro_app.model;

// Classe Model Estrutura:
public class Estrutura {
    //Declaração de Variaveis:

    // Atributos da Classe:
    private int idEstrutura;
    private long cnpj;

    private String descricao;

    // Metodo Construtor da classe:|
    public Estrutura(int idEstrutura, long cnpj, String descricao) {
        this.idEstrutura = idEstrutura;
        this.cnpj = cnpj;
        this.descricao = descricao;
    }

    // Métodos Getters:

    public int getIdEstrutura() {
        return idEstrutura;
    }

    public long getCnpj() {
        return cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

}
