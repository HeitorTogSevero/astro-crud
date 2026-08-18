package org.astro.astro_app.model;

public class Departamento {
    private int idDepartamento;
    private int cnpj;
    private String nome;
    private int grauRisco;
    private String atividade;
    private int qtdFuncionario;
    private String descricao;

    public Departamento(int idDepartamento, int cnpj, String nome, int grauRisco, String atividade, int qtdFuncionario, String descricao) {
        this.idDepartamento = idDepartamento;
        this.cnpj = cnpj;
        this.nome = nome;
        this.grauRisco = grauRisco;
        this.atividade = atividade;
        this.qtdFuncionario = qtdFuncionario;
        this.descricao = descricao;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public int getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public int getGrauRisco() {
        return grauRisco;
    }

    public String getAtividade() {
        return atividade;
    }

    public int getQtdFuncionario() {
        return qtdFuncionario;
    }

    public String getDescricao() {
        return descricao;
    }
}