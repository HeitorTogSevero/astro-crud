package org.astro.astro_app.model;

import java.sql.Date;

//Classe model Empresa
public class Empresa {

    //Atributos inteiros
    private int idEmpresa;

    //Atributos String
    private String cnae;
    private String nome;
    private String cep;
    private String cnpj;
    private String rua;
    private String estado;
    private String bairro;
    private String cidade;

    //Atributos Date
    private Date dtRegistro;

    // Construtor completo para inicializar uma Empresa
    public Empresa(int idEmpresa, String cnae, String nome, String cep, String cnpj, String rua, String estado, String bairro, String cidade, Date dtRegistro) {
        this.idEmpresa = idEmpresa;
        this.cnae = cnae;
        this.nome = nome;
        this.cep = cep;
        this.cnpj = cnpj;
        this.rua = rua;
        this.estado = estado;
        this.bairro = bairro;
        this.cidade = cidade;
        this.dtRegistro = dtRegistro;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public Empresa(){

    }

    // Construtor sem o ID
    public Empresa(String cnae, String nome, String cep, String cnpj, String rua, String estado, String bairro, String cidade, Date dtRegistro) {
        this.cnae = cnae;
        this.nome = nome;
        this.cep = cep;
        this.cnpj = cnpj;
        this.rua = rua;
        this.estado = estado;
        this.bairro = bairro;
        this.cidade = cidade;
        this.dtRegistro = dtRegistro;
    }


    // Getters

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getCnae() {
        return cnae;
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

    public Date getDtRegistro() {
        return dtRegistro;
    }
}