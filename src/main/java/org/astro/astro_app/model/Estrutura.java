package org.astro.astro_app.model;

public class Estrutura {
    //
    private int idEstrutura;
    private long cnpj;

    private String descricao;

    public Estrutura(int idEstrutura, long cnpj, String descricao) {
        this.idEstrutura = idEstrutura;
        this.cnpj = cnpj;
        this.descricao = descricao;
    }

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
