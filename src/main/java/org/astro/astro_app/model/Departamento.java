package org.astro.astro_app.model;

//Classe model Departamento
public class Departamento {
    //Declaração de variáveis

   //Atributos da classe
    private int idDepartamento;
    private int idEmpresa;
    private String grauRisco;
    private String nome;
    private String descricao;
    private String atividade;

    //Metodo contrutor da classe
    public Departamento(int idDepartamento, int idEmpresa, String grauRisco, String nome, String descricao, String atividade) {
        this.idDepartamento = idDepartamento;
        this.idEmpresa = idEmpresa;
        this.grauRisco = grauRisco;
        this.nome = nome;
        this.descricao = descricao;
        this.atividade = atividade;
    }

    public Departamento(){}

    public Departamento(int idEmpresa, String grauRisco, String nome, String descricao, String atividade) {
        this.idEmpresa = idEmpresa;
        this.grauRisco = grauRisco;
        this.nome = nome;
        this.descricao = descricao;
        this.atividade = atividade;
    }

    //Metodos Getters

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getGrauRisco() {
        return grauRisco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getAtividade() {
        return atividade;
    }
}