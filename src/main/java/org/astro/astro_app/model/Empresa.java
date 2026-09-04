package org.astro.astro_app.model;

//Classe model Empresa
public class Empresa {
    //Declaração de variáveis

    //Atributos da classe
    private int idEmpresa;
    private int cnae;
    private int qtdFuncionario;

    private String nome;
    private String cep;
    private String cnpj;
    private String rua;
    private String estado;
    private String bairro;
    private String cidade;

    // Metodo Construtor da classe:
    public Empresa(int idEmpresa, int cnae, int qtdFuncionario, String nome, String cep, String cnpj, String rua, String estado, String bairro, String cidade) {
        this.idEmpresa = idEmpresa;
        this.cnae = cnae;
        this.qtdFuncionario = qtdFuncionario;
        this.nome = nome;
        this.cep = cep;
        this.cnpj = cnpj;
        this.rua = rua;
        this.estado = estado;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    // Metodos Getters:
    public int getIdEmpresa() {
        return idEmpresa;
    }

    public int getCnae() {
        return cnae;
    }

    public int getQtdFuncionario() {
        return qtdFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return cep;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRua() {
        return rua;
    }

    public String getEstado() {
        return estado;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }
}