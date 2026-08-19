package org.astro.astro_app.model;

public class Empresa {
    private int idEmpresa;
    private int cnae;
    private int qtdFuncionario;
    private String rua;
    private int cep;
    private String cidade;
    private String bairro;
    private String estado;
    private long cnpj;

    public Empresa(int idEmpresa, int cnae, int qtdFuncionario, String rua, int cep, String cidade, String bairro, String estado, long cnpj) {
        this.idEmpresa = idEmpresa;
        this.cnae = cnae;
        this.qtdFuncionario = qtdFuncionario;
        this.rua = rua;
        this.cep = cep;
        this.cidade = cidade;
        this.bairro = bairro;
        this.estado = estado;
        this.cnpj = cnpj;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public int getCnae() {
        return cnae;
    }

    public int getQtdFuncionario() {
        return qtdFuncionario;
    }

    public String getRua() {
        return rua;
    }

    public int getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getEstado() {
        return estado;
    }

    public long getCnpj() {
        return cnpj;
    }
}